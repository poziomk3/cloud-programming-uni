package com.poziomk3.notification_service.config;

import io.minio.MinioClient;
import io.minio.credentials.Credentials;
import io.minio.credentials.Provider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperties props) {
        return MinioClient.builder()
                .endpoint(props.getUrl())
                .credentialsProvider(new Provider() {
                    @Override
                    public Credentials fetch() {
                        return new Credentials(
                                props.getAccessKey(),
                                props.getSecretKey(),
                                props.getSessionToken(),
                                null
                        );
                    }
                })
                .build();
    }
}
