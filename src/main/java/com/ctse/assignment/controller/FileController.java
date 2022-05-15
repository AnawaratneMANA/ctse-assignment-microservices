package com.ctse.assignment.controller;

import com.ctse.assignment.service.BlobFileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private final BlobFileUploadService blobFileUploadService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/upload")
    public String uploadFile(MultipartFile file){
        log.info("Filename: " + file.getOriginalFilename());
        log.info("Size: " + file.getSize());
        log.info("Content Type : " + file.getContentType());
        String destinationFilename = "./uploads/" + file.getOriginalFilename() + UUID.randomUUID();
        // Cache the file locally.
        String response;
        try {
            Files.copy(
                    file.getInputStream(),
                    Path.of(destinationFilename),
                    StandardCopyOption.REPLACE_EXISTING
            );

            // Upload the file to the server.
            response = blobFileUploadService.storeFile(file.getOriginalFilename(),file.getInputStream(), file.getSize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  destinationFilename + "Has been saved!" + " and " + response;
    }


}
