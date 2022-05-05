package com.ctse.assignment.repository;

import com.ctse.assignment.model.File;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface FileRepository {
    String saveFileData(@RequestBody File file);
    File getFile(String file_id);
    List<File> getFiles();
    String getFileUrl(String file_id);
}
