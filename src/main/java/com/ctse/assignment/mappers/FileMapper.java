package com.ctse.assignment.mappers;
import com.ctse.assignment.model.File;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class FileMapper implements RowMapper<File> {
    @Override
    public File mapRow(ResultSet resultSet, int i) throws SQLException {
        File file = new File();
        file.setFile_id(resultSet.getString("file_id"));
        file.setName(resultSet.getString("name"));
        file.setDate(resultSet.getString("created_date"));
        file.setFile_size(resultSet.getString("file_size"));
        file.setFile_url(resultSet.getString("file_url"));
        return file;
    }
}
