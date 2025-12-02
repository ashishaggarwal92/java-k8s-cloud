package com.home.reactive.learning.controller;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;

@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String elasticHost;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

    @Value("${elasticsearch.ca-cert}")
    private Resource caCert;

    @Bean
    public ElasticsearchClient elasticsearchClient() throws Exception {

        // Load CA cert into truststore
        KeyStore trustStore = KeyStore.getInstance("pkcs12");
        trustStore.load(null, null);

        try (InputStream is = caCert.getInputStream()) {
            trustStore.setCertificateEntry("ca",
                    CertificateFactory.getInstance("X.509").generateCertificate(is));
        }

        // Create SSLContext
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(trustStore, null)
                .build();

        // Credentials provider
        final BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        // Build RestClient (low-level client)
       /* RestClientBuilder builder = RestClient.builder(HttpHost.create(elasticHost))
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credsProvider)
                );*/

        RestClientBuilder builder = RestClient.builder(HttpHost.create(elasticHost))
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credsProvider)
                        .setSSLHostnameVerifier((hostname, session) -> true)
                );

        RestClient restClient = builder.build();

        // High-level ELASTICSEARCH JAVA CLIENT
        RestClientTransport transport =
                new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }
}
