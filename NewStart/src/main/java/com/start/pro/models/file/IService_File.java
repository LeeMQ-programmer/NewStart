package com.start.pro.models.file;

<<<<<<< HEAD
import java.util.List;
=======
import org.springframework.web.multipart.MultipartHttpServletRequest;
>>>>>>> branch 'master' of https://github.com/LeeMQ-programmer/NewStart.git

import com.start.pro.dto.DTO_File;

public interface IService_File {

	public  boolean insertFile(DTO_File dto);
	
	public List<DTO_File> searchFile(DTO_File dto);
	
	public boolean delFile(DTO_File dto);
	
	public DTO_File getDown(String seq);
	
}
