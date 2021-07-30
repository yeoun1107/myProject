package yunProject.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import yunProject.domain.dto.VisualDto;

public interface VisualService {

	void uploadAndSave(VisualDto dto, MultipartFile file);

	void getList(Model model);

	String uploadByTemp(MultipartFile tempFile);

	void uploadAndSave2(VisualDto dto, MultipartFile file);

	void delete(long no);

}
