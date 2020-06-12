package A.B.C;

import com.start.pro.dto.DTO_File;
import com.start.pro.models.file.IService_File;
import com.start.pro.models.file.Service_FileImpl;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IService_File service = new Service_FileImpl();
		
		DTO_File dto = new DTO_File("1", "1", "1", "파일 이름", "파일진짜이름", "주소","date", ".jpg", "21","N");
		System.out.println(service.insertFile(dto));
	}

}
