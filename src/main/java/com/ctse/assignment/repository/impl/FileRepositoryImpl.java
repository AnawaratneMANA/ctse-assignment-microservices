package com.ctse.assignment.repository.impl;

import com.ctse.assignment.model.File;
import com.ctse.assignment.repository.FileRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepositoryImpl implements FileRepository {
    @Override
    public String saveFileData(File file) {
        return null;
    }

    @Override
    public File getFile(String file_id) {
        return null;
    }

    @Override
    public List<File> getFiles() {
        return null;
    }

    @Override
    public String getFileUrl(String file_id) {
        return null;
    }
}
