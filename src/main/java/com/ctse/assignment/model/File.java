package com.ctse.assignment.model;
import com.ctse.assignment.config.GenerateId;
import javax.persistence.*;

@Entity
@Table( name = "file", uniqueConstraints=@UniqueConstraint(columnNames = "file_id"))
public class File {

    // Attributes for the file model class.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String file_id = GenerateId.generateId();

    @Column (name = "name")
    private String name;

    @Column (name = "created_date")
    private String create_date;

    @Column (name = "file_size")
    private String file_size;

    @Column (name = "file_url")
    private String file_url;

    // Getters and Setters for the attributes.

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
}
