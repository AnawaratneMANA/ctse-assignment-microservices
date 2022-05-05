package com.ctse.assignment.controller;
import com.ctse.assignment.model.File;
import com.ctse.assignment.repository.impl.FileRepositoryImpl;
import com.ctse.assignment.service.BlobFileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private final FileRepositoryImpl fileRepository;

    private final BlobFileUploadService blobFileUploadService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file){
        log.info("Filename: " + file.getOriginalFilename());
        log.info("Size: " + file.getSize());
        log.info("Content Type : " + file.getContentType());
        String destinationFilename = "./uploads/" + file.getOriginalFilename() + UUID.randomUUID();
        // Cache the file locally.
        String response;
        int meta_response;
        try {
            Files.copy(
                    file.getInputStream(),
                    Path.of(destinationFilename),
                    StandardCopyOption.REPLACE_EXISTING
            );

            // Upload the file to the server.
            response = blobFileUploadService.storeFile(file.getOriginalFilename(),file.getInputStream(), file.getSize());

            //Creeate a new file instance for the meta.
            File filemeta = new File();

            //Add the meta information to the file object.
            double file_size = file.getSize()/1024;
            filemeta.setFile_size(String.valueOf(file_size) + "kb");
            filemeta.setName(file.getOriginalFilename());
            filemeta.setFile_url("https://ctsestorageaccount.blob.core.windows.net/filecontainer/" + file.getOriginalFilename());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            filemeta.setDate(dtf.format(now));

            meta_response = fileRepository.saveFileData(filemeta);
            if (meta_response == 1){
                return new ResponseEntity<File>(filemeta, HttpStatus.OK);
            } else if (meta_response == 0){
                return new ResponseEntity<>("Error inserting the record!", HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("There's IO exception while inserting!", HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/health")
    public String health_check(){
        return "Web server is running";
    }

}
