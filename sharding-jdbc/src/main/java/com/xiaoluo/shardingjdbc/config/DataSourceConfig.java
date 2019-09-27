package com.xiaoluo.shardingjdbc.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @classname: DataSourceConfig
 * @description: 数据源核心配置类
 * @author: Vayne.Luo
 * @date 2019/9/27 14:08
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    Database0Config database0Config;

    @Autowired
    Database1Config database1Config;

    @Autowired
    DatabaseShardingAlgorithm databaseShardingAlgorithm;

    @Autowired
    TableShardingAlgorithm tableShardingAlgorithm;

    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    /**
     * @description: 构建数据源选择配置
     * @author: Vayne.Luo
     * @date: 2019/9/27 14:14
     */
    private DataSource buildDataSource() throws SQLException {
        Map<String,DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(database0Config.getDatabaseName(),database0Config.createDataSource());
        dataSourceMap.put(database1Config.getDatabaseName(),database1Config.createDataSource());
        // 设置默认数据库 ShardingJDBC
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap,database0Config.getDatabaseName());
        // 分表策略
        TableRule tableRule = TableRule.builder("goods")
                .actualTables(Arrays.asList("goods_0","goods_1"))
                .dataSourceRule(dataSourceRule)
                .build();
        // 分库分表策略
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(tableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("goods_id", databaseShardingAlgorithm))
                .tableShardingStrategy(new TableShardingStrategy("goods_type", tableShardingAlgorithm))
                .build();
        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

    @Bean
    public KeyGenerator keyGenerator(){
        return new DefaultKeyGenerator();
    }


}
