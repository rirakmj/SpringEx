package com.myboard.app007;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import com.myboard.dto.FileBoardDTO;
import com.myboard.model.BoardService;

@Controller
public class FileController {
	@Autowired
	private BoardService bservice;
	
	@GetMapping("fileInsert")
	public String fileInsert() {
		return "fileboardInsert";
	}
	
	@PostMapping("fileinsert")
	public String fileInsert(FileBoardDTO fboard, HttpSession session) {
		String uploadFolder = session.getServletContext().getRealPath("/")+"\\resources\\img";
		//System.out.println("uploadFolder  : " + uploadFolder);
		UUID uuid = UUID.randomUUID();
	    MultipartFile f= 	fboard.getUpload();
	    String uploadFileName = "";
	    if(!f.isEmpty()) { //파일업로드
	    	uploadFileName= uuid.toString()+"_"+f.getOriginalFilename();
	        File file = new File(uploadFolder, uploadFileName);
	        
	        try {
				f.transferTo(file); //파일 업로드
				fboard.setFileImage(uploadFileName); //디비에 들어갈 파일 이름
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
	    }
	    bservice.fileInsert(fboard);
		return "redirect:fileList";
				
	}
	@GetMapping("fileList")
	public String fileList(Model model) {
	   List<FileBoardDTO> flists=	 bservice.fileList();
	   model.addAttribute("files", flists);
		return "fileList";
	}
	
	@GetMapping("uploadFile")
	public String uploadForm() {
		return "uploadForm";
	}
	
	@PostMapping("fileAction")
	public String upload(MultipartFile[] uploads, Model model) {
		String uploadFolder = "D:\\___BIG9_JUNG\\STSWORK3\\SpringProject07_Board\\src\\main\\webapp\\resources";
		ArrayList<String> arr = new ArrayList<String>();
		for( MultipartFile  multipartFile : uploads) {
			//파일 이름 중복 피하기 위해 이름 수정
			UUID uuid = UUID.randomUUID();
			String uploadFileName = uuid.toString()+"_"+multipartFile.getOriginalFilename();
			//System.out.println("uploadFileName : " + uploadFileName);
			File saveFile =new File(uploadFolder,uploadFileName);
			try {
				multipartFile.transferTo(saveFile);
				arr.add(multipartFile.getOriginalFilename());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			};
			model.addAttribute("uploadFileName", arr);
			
		}
		
		return "fileResult";
	}

}
