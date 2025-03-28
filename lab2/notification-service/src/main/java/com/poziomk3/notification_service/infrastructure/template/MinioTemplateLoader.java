package com.poziomk3.notification_service.infrastructure.template;

import com.poziomk3.notification_service.config.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.GetObjectArgs;
import io.minio.errors.ErrorResponseException;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioTemplateLoader {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties; // Inject the configuration properties

    public String loadTemplate(String path) {
        try {
            String bucketName = minioProperties.getBucketName();  // Fetch bucket name from config

            // Check if the bucket exists
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())){
                log.error("The bucket {} does not exist", bucketName);
                throw new RuntimeException("The specified bucket does not exist");
            }

            // Load the template from MinIO
            try (InputStream is = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)  // Use the bucket name from config
                            .object(path)
                            .build())) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (ErrorResponseException e) {
            // Log specific MinIO errors like object not found
            log.error("Error while fetching the object from MinIO: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load template from MinIO", e);
        } catch (Exception e) {
            log.error("Failed to load template from MinIO: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load template from MinIO", e);
        }
    }
}
