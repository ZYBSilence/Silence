package cn.graduation.bbs.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: 重新注入RestHighLevelClient
 * @author: zyb
 * @date: 2023/2/20 16:42
 */
@Configuration
public class RestClientConfig {

    @Value("${elasticsearch1.hostname}")
    private String hostname;

    @Value("${elasticsearch1.port}")
    private Integer port;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        // 如果是集群，可添加多个HttpHost
        return new RestHighLevelClient(RestClient.builder(new HttpHost(hostname, port, "http")));
    }
//
//    @Bean
//    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
//        // 如果是集群，可添加多个HttpHost
//        Settings settings = Settings.builder().put("cluster.name", "docker-cluster").build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new TransportAddress(InetAddress.getByName(hostname), 9300));
//        return new ElasticsearchTemplate((Client) elasticsearchClient());
//    }
}
