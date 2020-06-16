package com.start.pro.models.file;

import java.util.List;

import com.start.pro.dto.DTO_File;

public interface IDao_File {

	
	
	public  boolean insertFile(DTO_File dto);
	
	public List<DTO_File> searchFile(DTO_File dto);
	
	public boolean delFile(DTO_File dto);
	
	public DTO_File getDown(String seq);
	
		
}
