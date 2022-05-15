package com.ctse.assignment;
import com.ctse.assignment.model.File;
import com.ctse.assignment.repository.impl.FileRepositoryImpl;
import com.ctse.assignment.service.BlobFileUploadService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class FileUploadServiceApplicationTests {

	@Autowired
	private FileRepositoryImpl fileRepository;

	@Autowired
	private BlobFileUploadService blobFileUploadService;

	@Test
	public void testing_file_data_insertion(){
		File file = new File();
		file.setFile_id("1");
		file.setName("sample1");
		file.setFile_size("sample");
		file.setDate("sample");
		file.setFile_url("sample");
		int response = fileRepository.saveFileData(file);
		System.out.println(response);
	}

	@Test
	public void testing_file_delete(){
		boolean response = blobFileUploadService.deleteFiles("database_creds.txt");
		System.out.println(response);
	}

	@Test
	public void testing_file_get_by_id(){
		File file = fileRepository.getFile("8fbafca0-4007-4359-a345-57ee4a9b06e4");
		System.out.println(file.getName());
	}
}
