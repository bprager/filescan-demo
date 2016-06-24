package ws.prager.filescan.configuration;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@PropertySource(value = "classpath:elasticsearch.properties")
@EnableElasticsearchRepositories(basePackages = "ws.prager.filescan.models")
public class ElasticsearchConfiguration {
    final static Logger logger = LoggerFactory.getLogger(ElasticsearchConfiguration.class);
    @Resource
    private Environment environment;

    @Bean
    public Client client() {
        TransportClient client = new TransportClient();
        TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"),
                Integer.parseInt(environment.getProperty("elasticsearch.port")));
        client.addTransportAddress(address);
        logger.debug("get client on address: " + address.toString());
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        logger.debug("get template");
        return new ElasticsearchTemplate(client());
    }

}
