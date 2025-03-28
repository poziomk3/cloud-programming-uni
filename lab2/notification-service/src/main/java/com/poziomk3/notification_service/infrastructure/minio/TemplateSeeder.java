package com.poziomk3.notification_service.infrastructure.minio;

import com.poziomk3.notification_service.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class TemplateSeeder {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    private final ResourceLoader resourceLoader;

    @PostConstruct
    public void seedTemplates() {
        try {
            String bucket = minioProperties.getBucketName();  // For example, "my-bucket"

            // Check if the bucket exists
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!exists) {
                // Create the bucket if it does not exist
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }

            // Continue to upload templates or other files
            uploadIfNotExists("welcome.html");
            uploadIfNotExists("role-assigned.html");

        } catch (Exception e) {
            throw new RuntimeException("Failed to seed templates to MinIO", e);
        }
    }


    private void uploadIfNotExists(String templateName) throws Exception {
        String bucket = minioProperties.getBucketName();
        Resource resource = resourceLoader.getResource("classpath:templates/" + templateName);

        try (InputStream is = resource.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(templateName)
                            .stream(is, is.available(), -1)
                            .contentType("text/plain")
                            .build()
            );
        }
    }
}
