package com.haizol.batch.entity;

import com.haizol.common.web.manager.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @classname: CompLocationForm
 * @description: 公司位置信息
 * @author: Vayne.Luo
 * @date 2020/4/23 11:10
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CompLocationForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long compId;

    private Integer stateId;

    private Integer provinceId;

    private Integer cityId;

    private String site;

    private String cnName;

    private String address;
}
