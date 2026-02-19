package com.example.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.FilesEntity;
import com.example.demo.model.ResponseMessageDto;
import com.example.demo.repository.FilesRepo;
import com.example.demo.utility.Constants;

@RestController
public class FilesController {

	@Autowired
	FilesRepo filesRepo;

	@PostMapping("/uploadFile")
	public ResponseEntity<ResponseMessageDto> createFile(@RequestParam MultipartFile file) throws IOException {
		FilesEntity files = new FilesEntity();
		files.setFileName(file.getOriginalFilename());
		files.setFileType(file.getContentType());
		files.setData(file.getBytes());
		filesRepo.save(files);
		return ResponseEntity.ok(new ResponseMessageDto(HttpsURLConnection.HTTP_CREATED, 
				Constants.SUCCESS,"files insert succesfully",
				file.getOriginalFilename()));
	}
	
	
	@PostMapping("/uploadMutlipleFiles")
	public ResponseEntity<Stream<Object>> createMultipleFile(@RequestParam MultipartFile[] filess) throws IOException {
		
		 Stream<Object> map = Arrays.stream(filess).map(s->{
			try {
				return this.createFile(s);
			}catch (Exception e) {
				return "files upload failed" +e.getLocalizedMessage();
			}
		});
		return ResponseEntity.ok(map);
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

}
