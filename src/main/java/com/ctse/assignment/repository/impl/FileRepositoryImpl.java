package com.ctse.assignment.repository.impl;
import com.ctse.assignment.model.File;
import com.ctse.assignment.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FileRepositoryImpl implements FileRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer saveFileData(File file) {
        int response;
        try {
            response = jdbcTemplate.update("INSERT INTO file (file_id, name, created_date, file_size, file_url) VALUES(?,?,?,?,?)",
                    new Object[] {file.getFile_id(), file.getName(), file.getDate(), file.getFile_size(), file.getFile_url()});
        } catch (Exception e){
            System.out.println(e);
            return 0;
        }
        return response;
    }

    @Override
    public File getFile(String file_id) {
        return null;
    }

    @Override
    public List<File> getFiles() {
        return jdbcTemplate.query("SELECT * from file", BeanPropertyRowMapper.newInstance(File.class));
    }

    @Override
    public String getFileUrl(String file_id) {
        return null;
    }
}
