package com.ctse.assignment.service;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.ctse.assignment.util.AzureBlobProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlobFileUploadService {

    private final AzureBlobProperties azureBlobProperties;

    // Establishing the connection to the container.
    private BlobContainerClient containerClient() {
        BlobContainerClient serviceClient = new BlobContainerClientBuilder()
                .connectionString(azureBlobProperties.getConnectionstring())
                .buildClient();
        BlobContainerClient container = serviceClient.getServiceClient().getBlobContainerClient(azureBlobProperties.getContainer());
        return container;
    }

    // Upload files to the container.
    public String storeFile(String filename, InputStream content, long length){
        log.info("Azure store file BEGIN {}", filename);
        BlobClient client = containerClient().getBlobClient(filename);
        if(client.exists()){
            log.warn("The file is already located in azure");
        }
        else {
            client.upload(content, length);
        }

        log.info("Azure store file END");
        return "File uploaded to the Azure storage";
    }

    // Delete files from azure blob storage.
    public boolean deleteFiles(String filename){
        log.info("Azure store file BEGIN {}", filename);
        BlobClient client = containerClient().getBlobClient(filename);
        if(client.exists()){
            log.warn("The file is already located in azure");
            client.delete();
            return true;
        }
        else {
            return false;
        }
    }

}
