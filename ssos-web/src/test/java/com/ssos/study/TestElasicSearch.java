package com.ssos.study;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestElasicSearch
 * @Description: dto
 * @Author: xwl
 * @Date: 2019/11/4 4:56 下午
 * @Vsersion: 1.0
 */

public class TestElasicSearch {
    public static void main(String[] args) throws Exception {


        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
                        .setMaxRetryTimeoutMillis(5 * 60 * 1000)
                        .setHttpClientConfigCallback((HttpAsyncClientBuilder httpClientBuilder) -> {
                            RequestConfig builder = RequestConfig.custom()
                                    .setConnectTimeout(5 * 60 * 1000)
                                    .setSocketTimeout(5 * 60 * 1000)
                                    .setConnectionRequestTimeout(5 * 60 * 1000)
                                    .build();
                            httpClientBuilder.setDefaultRequestConfig(builder);
                            return httpClientBuilder;
                        })
        );
        selectTowers(restHighLevelClient);

        restHighLevelClient.close();
    }


    public static void selectTowers(RestHighLevelClient restHighLevelClient) throws Exception {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("organization_id", "1000"));
        boolQueryBuilder.filter(QueryBuilders
                .geoBoundingBoxQuery("location")
                .setCorners(new GeoPoint(39.83761549181424, 109.617075477469),
                        new GeoPoint(39.52194280218617, 110.03357112819631)));
        SearchRequest tower = new SearchRequest("tower")
                .source(new SearchSourceBuilder()
                        .query(boolQueryBuilder)
                        .size(10000));
        SearchResponse search = restHighLevelClient.search(tower, RequestOptions.DEFAULT);

        SearchHit[] hits = search.getHits().getHits();
        int length = hits.length;
        System.out.println(length);
        if (length > 0) {
            for (SearchHit s : hits) {
                System.out.println(s.getSourceAsMap());
            }
        } else {
            System.out.println("no no no no no no no ");
        }
    }


    public static void createIndexdata(RestHighLevelClient restHighLevelClient) throws Exception {
        List<Map<String, Object>> maps = testConnection();
        BulkRequest bulkRequest = new BulkRequest();
        maps.forEach(p -> {
            String id = p.get("id") + "";
            p.remove("id");
            bulkRequest.add(new IndexRequest("tower").id(id).source(p));
        });
        bulkRequest.timeout(TimeValue.timeValueMinutes(5));
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }


    public static List<Map<String, Object>> testConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.10.101:3338/ebit_saas_business?" +
                "useSSL=false&rewriteBatchedStatements=true&autoReconnect=true", "root", "123456");


        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select id,line_id,organization_id,latitude,longitude " +
                "from ebit_saas_business.resource_towers");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        while (resultSet.next()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", resultSet.getLong("id"));
            map.put("organization_id", resultSet.getString("organization_id"));
            HashMap<String, Object> hashMap = new HashMap();
            hashMap.put("lat", resultSet.getFloat("latitude"));
            hashMap.put("lon", resultSet.getFloat("longitude"));
            map.put("location", hashMap);
            list.add(map);
        }
        connection.close();
        statement.close();
        resultSet.close();

        return list;
    }
}
