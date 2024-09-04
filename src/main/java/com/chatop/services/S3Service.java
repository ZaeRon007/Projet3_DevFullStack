package com.chatop.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

@Service
public class S3Service {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.region}") 
    String rg;

    private S3Client s3Client;

    public S3Service(   @Value("${aws.accessKeyId}") String accessKeyId,
                        @Value("${aws.secretKey}") String secretKey,
                        @Value("${aws.region}") String region){
        try {
            AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretKey);
            this.s3Client = S3Client.builder()
                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                    .region(Region.of(region))
                    .build();
            
        } catch (Exception e) {
            System.out.printf("ERROR: %s\n",e);
        }
    }

    /**
     * Upload a file to the bucket and return the Url
     * @param filePath the file path
     * @param fileName the file name
     * @return a new Url
     * @throws URISyntaxException
     */
    public String uploadFile(Path filePath, String fileName) throws URISyntaxException {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            s3Client.putObject(putObjectRequest, filePath);

            URI url = new URI("https://" + bucketName + ".s3." +  rg + ".amazonaws.com/" + fileName);
            return url.toString();
        } catch (S3Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    /**
     * Get the Url of the picture contained in the bucket
     * @param imageName image name at bucket
     * @return the image Url
     */
    public String getImageUrl(String imageName) {
        try {
            Path imagePath = Path.of("/resources/static/public/", imageName);
            return uploadFile(imagePath, imageName);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
