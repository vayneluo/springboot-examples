package com.haizol.batch.service;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/**
 * @classname: Test
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/11/18 15:03
 */
public class Test {

    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("D:\\20211118.xlsx");
    }
}
