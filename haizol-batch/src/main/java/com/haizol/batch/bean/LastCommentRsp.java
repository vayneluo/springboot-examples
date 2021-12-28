package com.haizol.batch.bean;

import lombok.Data;

import java.util.Date;

/**
 * @classname: LastCommentRsp
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2020/9/7 16:08
 */
@Data
public class LastCommentRsp {

    private Long compId;

    private Date lastComment;
}
