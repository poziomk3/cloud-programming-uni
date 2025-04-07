package com.poziomk3.notification_service.infrastructure.minio;

import com.poziomk3.notification_service.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.BucketExistsArgs;
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
        String bucket = minioProperties.getBucketName();

        try {
            // ✅ Check if bucket exists, but do NOT create it if missing (AWS S3 limitation)
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!exists) {
                System.err.println("⚠️ S3 bucket '" + bucket + "' does not exist. You must create it manually in AWS.");
                return; // ⛔ Don't continue if the bucket isn't found
            }

            uploadIfNotExists("welcome.html");
            uploadIfNotExists("role-assigned.html");

        } catch (Exception e) {
            System.err.println("❌ Failed to seed templates: " + e.getMessage());
            e.printStackTrace(); // Optional: more detailed logs
            // 👇 Do NOT crash the app on MinIO/S3 failure
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
            System.out.println("✅ Uploaded template: " + templateName);
        } catch (Exception e) {
            System.err.println("⚠️ Failed to upload template: " + templateName + " → " + e.getMessage());
        }
    }
}
