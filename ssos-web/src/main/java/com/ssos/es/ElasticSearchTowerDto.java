package com.ssos.es;

import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.GeoPointField;
//import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;

/**
 * @ClassName: ElasticSearchTowerDto
 * @Description: dto
 * @Author: xwl
 * @Date: 2019/10/31 12:00 下午
 * @Vsersion: 1.0
 */
@Data
//@Document(indexName = "business", type = "towers")
public class ElasticSearchTowerDto implements Serializable {
//    @Id
//    private Long id;
//    @Field
//    private String organizationId;
//    @GeoPointField
//    private GeoPoint location;
}
