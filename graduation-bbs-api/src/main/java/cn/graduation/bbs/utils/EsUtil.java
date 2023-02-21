package cn.graduation.bbs.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Silence
 */
@Slf4j
@Component
public class EsUtil {

    @Resource
    private RestHighLevelClient client;

    /**
     * 创建索引(默认分片数为5和副本数为1)
     *
     * @param indexName 索引名称
     */
    public void createIndex(String indexName) throws Exception {
        // 定义索引名称
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        // 添加aliases，对比上述结构来理解
        String aliaseStr = "{\"user.aliases\":{}}";
        Map aliases = JSONObject.parseObject(aliaseStr, Map.class);
        // 添加mappings，对比上述结构来理解
        String mappingStr = "{\"properties\":{\"name\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\"}}},\"sex\":{\"type\":\"keyword\"},\"age\":{\"type\":\"integer\"}}}";
        Map mappings = JSONObject.parseObject(mappingStr, Map.class);
        // 添加settings，对比上述结构来理解
        String settingStr = "{\"index\":{\"number_of_shards\":\"9\",\"number_of_replicas\":\"2\"}}";
        Map settings = JSONObject.parseObject(settingStr, Map.class);

        // 添加数据
        request.aliases(aliases);
        request.mapping(mappings);
        request.settings(settings);

        // 发送请求到ES
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        // 处理响应结果
        System.out.println("添加索引是否成功：" + response.isAcknowledged());
        // 关闭ES客户端对象
        client.close();
    }

    public void getIndexInfo(String indexName) throws Exception {
        // 定义索引名称
        GetIndexRequest request = new GetIndexRequest(indexName);
        // 发送请求到ES
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
        // 处理响应结果
        System.out.println("aliases：" + response.getAliases());
        System.out.println("mappings：" + response.getMappings());
        System.out.println("settings：" + response.getSettings());
        // 关闭ES客户端对象
        client.close();
    }

    public void deleteIndex(String indexName) throws Exception {
        // 定义索引名称
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        // 发送请求到ES
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        // 处理响应结果
        System.out.println("删除是否成功：" + response.isAcknowledged());
        // 关闭ES客户端对象
        client.close();
    }


}