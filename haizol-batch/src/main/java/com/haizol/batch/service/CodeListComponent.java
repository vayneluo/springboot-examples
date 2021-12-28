/*
package com.haizol.batch.service;

import com.haizol.common.web.common.vo.DataDictVo;
import com.haizol.common.web.system.DataDictCacheService;
import com.haizol.common.web.system.DataDictVOService;
import com.haizol.common.web.system.i18n.I18NResources;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

*/
/**
 * @Description 数据字典组件
 * @Author Jasonyu
 * @Param
 * @Date 19:56 2019/6/5
 * @return
 *//*

@Component
public class CodeListComponent {

    @Autowired
    private DataDictVOService dataDictVOService;

    @Autowired
    private DataDictCacheService dataDictCacheService;

    @Autowired
    I18NResources i18NResources;


    */
/**
     * 获取某个数据字典的值
     * @param type
     * @param id
     * @return
     *//*

    public DataDictVo getDataDictValue(String type, Integer id) {
        List<DataDictVo> dict = dataDictVOService.getDataDictVO(type);
        for (DataDictVo dataDictItem : dict) {
            Integer dataId = Integer.valueOf(dataDictItem.getId());
            if (dataId.equals(id)){
                return dataDictItem;
            }
        }
        return null;
    }



    */
/**
     * 根据code 得到国际化后的值 -peter code为空 返回Null 2020年1月13日
     *//*

    public String getI18nStr(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        String i18nStr = i18NResources.getHaizolMessage(code);
        if (StringUtils.isBlank(i18nStr)) {
            return null;
        }
        return i18nStr;
    }
}
*/
