package com.ctse.assignment;

import com.ctse.assignment.util.AzureBlobProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AzureBlobProperties.class)
public class FileUploadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadServiceApplication.class, args);
	}

}
