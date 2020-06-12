package com.start.pro.models.file;

import com.start.pro.dto.DTO_File;

public interface IService_File {

	public  boolean insertFile(DTO_File dto);
	
	public DTO_File searchFile(DTO_File dto);
	
	public boolean delFile(DTO_File dto);
	
	
	
}
