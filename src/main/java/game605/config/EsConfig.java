package game605.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

/**
 * @author Koyou
 * @version 1.0.0
 * @className EsConfig
 * @description elasticSearch 配置
 * @since 2024/6/7 10:23
 */
public class EsConfig extends ElasticsearchConfiguration {


    @Value("${spring.elasticsearch.rest.uris}")
    private String uris;

    @Value("${spring.elasticsearch.rest.socket-timeout}")
    private long socketTimeout;

    @Override

    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(uris)
                .withSocketTimeout(socketTimeout)
                .withConnectTimeout(socketTimeout)
                .build();

    }

}
