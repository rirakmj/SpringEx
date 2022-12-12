package com.example.demo06.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo06.model.FileBoard;
import com.example.demo06.repository.FileRepository;

@Service
public class FileService {
	@Autowired
	private  FileRepository fileRepository;
	public void fileInsert(FileBoard fboard, 
			  String uploadFolder) {
		UUID uuid = UUID.randomUUID();
		MultipartFile f =  fboard.getUpload();
		String uploadFileName = "";
		if(!f.isEmpty()) { // 파일선택됨
			uploadFileName=uuid.toString()+"_"+f.getOriginalFilename();
			File saveFile = new File(uploadFolder,uploadFileName);
	
			try {
				f.transferTo(saveFile); //파일 업로드
				fboard.setFileimage(uploadFileName);
			} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
			} 
		}
		fileRepository.save(fboard);	
	}
	
	public List<FileBoard>list(){
		return fileRepository.findAll();
	}
	
	
	
	

}
