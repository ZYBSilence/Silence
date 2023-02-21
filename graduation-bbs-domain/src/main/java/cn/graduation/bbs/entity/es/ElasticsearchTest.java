//package cn.graduation.bbs.entity.es;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.DateFormat;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.util.Date;
//
///**
// * @description:
// * @author: zyb
// * @date: 2023/2/14 11:37
// */
//@Data
//@Document(indexName = "book", createIndex = true)
//public class ElasticsearchTest {
//    @Id
//    @Field(type = FieldType.Text)
//    private String id;
//    @Field(analyzer = "ik_max_word")
//    private String title;
//    @Field(analyzer = "ik_max_word")
//    private String author;
//    @Field(type = FieldType.Double)
//    private Double price;
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private Date createTime;
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private Date updateTime;
//}
