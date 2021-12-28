package com.haizol.batch.service;

import com.alibaba.fastjson.JSONObject;
import com.haizol.batch.entity.CompLocationForm;
import com.haizol.common.cache.CodeListComponent;
import com.haizol.common.constant.HaizolConstant;
import com.haizol.common.enums.DataDictEnum;
import com.haizol.common.web.common.vo.DataDictVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @classname: TxLocationService
 * @description: 腾讯位置服务
 * @author: Vayne.Luo
 * @date 2020/4/23 09:20
 */
@Service
@Slf4j
public class TxLocationService {

    @Value("${tx.location.key}")
    private String txLocationKey;
    @Autowired
    CodeListComponent codeListComponent;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    RestTemplate txRestTemplate;


    private static final String TX_LOCATION_ANALYSIS_URL = "https://apis.map.qq.com/ws/geocoder/v1";

    private static final String ERROR_MESSAGE = "CID:{0},更新地理位置数据失败,错误信息：{1}，请人工处理";

    @Bean
    public RestTemplate getRestTemplate() {
        return restTemplateBuilder.build();
    }

    /**
     * @description: 同步公司位置坐标数据
     * @param: [id] 公司ID
     * @author: Vayne.Luo
     * @date: 2020/4/23 10:31
     */
    public String syncCompLocationData(CompLocationForm locationForm) {
        // 构建位置信息入参
        buildCompAddress(locationForm);
        String location = queryLocationByTx(locationForm);
        log.info("查询的坐标经纬度:{}",location);
        // 兜底措施，地址查询不到的话，再根据公司名去查询
        if(StringUtils.isBlank(location)){
            locationForm.setAddress(locationForm.getCnName());
            location = queryLocationByTx(locationForm);
        }
        return location;
    }

    /**
     * @description: 查询腾讯API，转换地址为坐标经纬度
     * @author: Vayne.Luo
     * @date: 2020/4/23 15:07
     */ 
    private String queryLocationByTx(CompLocationForm locationForm) {
        if(StringUtils.isBlank(locationForm.getAddress())){
            return "";
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TX_LOCATION_ANALYSIS_URL);
        Map<String,String> param = new HashMap<>();
        param.put("address",locationForm.getAddress());
        param.put("key",txLocationKey);
        param.forEach(builder::queryParam);
        String url = builder.build().encode().toString();
        log.info("腾讯位置服务请求URL:{}",url);
        String result = txRestTemplate.getForObject(url,String.class);
        log.info("腾讯位置服务请求结果：{}",result);
        StringBuilder sb = new StringBuilder();
        try {
            JSONObject resultObj = JSONObject.parseObject(result);
            Integer status = resultObj.getInteger("status");
            // 腾讯位置API请求异常，钉钉消息推送
            if(!status.equals(0)){
                log.info("查询位置无结果，返回状态码：{}",status);
                return "";
            }
            JSONObject locationObj = resultObj.getJSONObject("result");
            JSONObject location = locationObj.getJSONObject("location");
            if(null != location){
                sb.append(location.getString("lng"));
                sb.append(HaizolConstant.STR_COMMA);
                sb.append(location.getString("lat"));
            }
        }catch (Exception e){
            sb  = new StringBuilder();
            log.error("解析腾迅位置数据失败");
        }
        return sb.toString();
    }

    /**
     * @description: 构建公司位置信息
     * @author: Vayne.Luo
     * @date: 2020/4/23 11:51
     */ 
    private void buildCompAddress(CompLocationForm locationForm) {
        //如果接到地址不为空，则不更新坐标地址
        if(StringUtils.isBlank(locationForm.getSite())){
            return;
        }
        // 国家 + 省份 + 城市 + 详细地址
        DataDictVo stateVo = codeListComponent.getDataDictValue(DataDictEnum.DATA_DICT_COUNTRY.getDictKey(), locationForm.getStateId());
        DataDictVo provinceVo = codeListComponent.getDataDictValue(DataDictEnum.DATA_DICT_REGION.getDictKey(), locationForm.getProvinceId());
        DataDictVo cityVo = codeListComponent.getDataDictValue(DataDictEnum.DATA_DICT_CITY.getDictKey(), locationForm.getCityId());
        StringBuilder sb = new StringBuilder();
        if(null != stateVo){
            sb.append(stateVo.getCn());
        }
        if(null != provinceVo){
            sb.append(provinceVo.getCn());
        }
        if(null != cityVo){
            if(null != provinceVo && !cityVo.getCn().equals(provinceVo.getCn())){
                sb.append(cityVo.getCn());
                // 腾讯位置API 必须加上市才有搜索结果
                sb.append("市");
            }
        }
        if(StringUtils.isNotBlank(locationForm.getSite())){
            sb.append(locationForm.getSite());
        }
        log.info("待更新的地址:{}",sb.toString());
        locationForm.setAddress(sb.toString());
    }
}
