package game605.es;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ElasticsearchClientConfig
 * @description es配置
 * @since 2024/6/6 14:27
 */
@Configuration
public class ElasticsearchClientConfig extends ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.rest.uris}")
    String connetionUrl;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(connetionUrl)
                .build();
    }

}
