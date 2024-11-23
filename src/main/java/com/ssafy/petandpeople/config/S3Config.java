package com.ssafy.petandpeople.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    private static final Dotenv DOTENV = Dotenv.load();

    private static final String AWS_ACCESS_KEY = DOTENV.get("AWS_ACCESS_KEY");
    private static final String AWS_SECRET_ACCESS_KEY = DOTENV.get("AWS_SECRET_ACCESS_KEY");
    private static final String AWS_BUCKET_NAME = DOTENV.get("AWS_BUCKET_NAME");
    private static final String AWS_REGION = DOTENV.get("AWS_REGION");

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials basicAWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_ACCESS_KEY);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(AWS_REGION).build();
    }

    public static String getBucketName() {
        return AWS_BUCKET_NAME;
    }

}