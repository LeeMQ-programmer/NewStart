package com.start.pro.models.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.start.pro.dto.DTO_File;

@Component
public class FileUtils {
private static final String filePath = "C:\\fileupload"; // 파일이 저장될 위치
	
	public List<Map<String, Object>> parseInsertFileInfo(DTO_File dto_file, 
			MultipartHttpServletRequest mpreq) throws Exception{
		
		/*
			Iterator은 데이터들의 집합체? 에서 컬렉션으로부터 정보를 얻어올 수 있는 인터페이스입니다.
			List나 배열은 순차적으로 데이터의 접근이 가능하지만, Map등의 클래스들은 순차적으로 접근할 수가 없습니다.
			Iterator을 이용하여 Map에 있는 데이터들을 while문을 이용하여 순차적으로 접근합니다.
		*/
		
		Iterator<String> iterator = mpreq.getFileNames();
		
		MultipartFile multipartFile = null;
		String filerealname = null;
		String originalFileExtension = null;
		String filename = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
		
		int file_seq = Integer.parseInt(dto_file.getFile_seq());
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpreq.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				filerealname = multipartFile.getOriginalFilename();
				originalFileExtension = filerealname.substring(filerealname.lastIndexOf("."));
				filename = getRandomString() + originalFileExtension;
				
				file = new File(filePath + filename);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("FILE_SEQ", file_seq);
				listMap.put("FILEREALNAME", filerealname);
				listMap.put("FILENAME", filename);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
