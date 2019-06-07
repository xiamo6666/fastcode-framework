package com.ssos.flowable.modeler.conf;

import liquibase.integration.spring.SpringLiquibase;

import javax.sql.DataSource;


/**
 * @ClassName: LiquibaseConfig
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-04-26 17:34
 * @Vsersion: 1.0
 */
//@Configuration
public class LiquibaseConfig {
   // @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:liquibase/flowable-modeler-app-db-changelog.xml");
        liquibase.setContexts("development,test,production");
        liquibase.setShouldRun(true);
        return liquibase;
    }


}
