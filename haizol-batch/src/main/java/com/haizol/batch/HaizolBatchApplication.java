package com.haizol.batch;

import com.haizol.common.web.manager.boot.EnableManageWeb;
import com.haizol.common.web.manager.system.ManageSpringApplication;

@EnableManageWeb
public class HaizolBatchApplication {

    public static void main(String[] args) {
        ManageSpringApplication.run(HaizolBatchApplication.class, args);
    }

}
