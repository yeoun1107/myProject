package yunProject.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import yunProject.domain.dto.VisualDto;
import yunProject.domain.entity.VisualEntityRepository;

@Service
public class VisualServiceImpl implements VisualService {

	@Autowired
	VisualEntityRepository visualEntityRepository;
	
	@Override
	public void uploadAndSave(VisualDto dto, MultipartFile file) {
		long fileSize=file.getSize();    // MAX SIZE: 2*1024*1024
		if(fileSize> (2*1024*1024)) {
			System.out.println("파일용량제한:2MB");
			return;
		}
		
		//1. file upload
		String fileName=file.getOriginalFilename();
		String fileUrl="/images/visual/";
		
		//E:\spring\workspace\yunProject\src\main\resources\static\images\visual (물리적 주소)
		//E:\spring\workspace\yunProject\bin\main\static\images\visual (실제 등록시 location에 찍히는 주소)
		//문제점 : src에는 안들어가기 때문에 서버 위치가 바뀌면 upload 되어있던게 다 사라질 수 있다
		//src에 생성되면 bin에도 자동으로 생성되는데 bin에만 다이렉트로 생성하면 src에는 생성이 안된다
		//src에만 생성해도 그것도 문제가 있다.. 그럼 어떡함????????
		//어쨌든 프로젝트와 upload 위치는 별도로 나누는게 좋다. 근데 test환경에서는 확인하기가 어렵다는 단점
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl); //resource파일의 경로를 알 수 있는 클래스
		try {
			File location=cpr.getFile();
			//System.out.println("location : "+location);
			file.transferTo(new File(location, fileName));  //업로드하는 문장
			System.out.println("파일업로드 완료 : "+location +fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dto.setFileName(fileName);
		dto.setFileSize(fileSize);
		dto.setFileUrl(fileUrl);
		
		//2. title, link 데이터를 DB에 save
		visualEntityRepository.save(dto.toEntity());
	}

	@Override
	public void getList(Model model) {
		
		model.addAttribute("list", visualEntityRepository.findAll()
										.stream()
										.map(VisualDto::new)
										.collect(Collectors.toList())
										);
		
	}

	@Override
	public String uploadByTemp(MultipartFile tempFile) {
		long fileSize=tempFile.getSize();    // MAX SIZE: 2*1024*1024
		if(fileSize> (2*1024*1024)) {
			System.out.println("파일용량제한:2MB");
			return null;
		}
		
		//1. file upload
		String fileName=tempFile.getOriginalFilename();
		String fileUrl="/images/visual/temp/";
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl); //resource파일의 경로를 알 수 있는 클래스
		try {
			File tempLocation=cpr.getFile();
			
			//기존파일 제거
			System.out.println("----temp폴더-----");
			for(File temp:tempLocation.listFiles() ) {
				if(temp.isFile())temp.delete();
				//System.out.println(temp);
			}
			System.out.println("----------------");
			
			
			tempFile.transferTo(new File(tempLocation, fileName));  //업로드하는 문장
			System.out.println("임시파일업로드 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileUrl+fileName;
	}

	@Override
	public void uploadAndSave2(VisualDto dto, MultipartFile file) {
		//1. file upload
		long fileSize=file.getSize();
		String fileName=file.getOriginalFilename();
		String fileUrl="/images/visual/";
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl);
		
		ClassPathResource tempcpr=new ClassPathResource("static"+fileUrl+"temp/");
		try {
			File tempFolder=tempcpr.getFile();
			File tempFile=new File(tempFolder, fileName);
			if(tempFile.exists()) {
				//파일이동
				tempFile.renameTo(new File(cpr.getFile(), fileName));
				System.out.println("임시파일->visual 경로로 이동");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		dto.setFileName(fileName);
		dto.setFileSize(fileSize);
		dto.setFileUrl(fileUrl);
		
		visualEntityRepository.save(dto.toEntity());
	}

	@Override
	public void delete(long no) {
		visualEntityRepository.deleteById(no);
	}

}
