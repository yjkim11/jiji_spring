package org.jiji.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jiji.domain.BoardAttachVO;
import org.jiji.mapper.BoardAttachMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
@AllArgsConstructor
public class FileCheckTask {

	private BoardAttachMapper attachMapper;
	
//	@Scheduled(cron="0 * * * * *")
	public void checkFiles() throws Exception {
		
		log.warn("File Check Task run.......................");
		log.warn(new Date());
		
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("C:\\project\\99.etc\\practice\\upload\\", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName())).collect(Collectors.toList());
		
		fileList.stream().filter(vo -> vo.isFileType() == true).map(vo -> Paths.get("C:\\project\\99.etc\\practice\\upload\\", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName())).forEach(p -> fileListPaths.add(p));
		
		log.warn("=========================================");
		
		fileListPaths.forEach(p -> log.warn(p));
		
		File targetDir = Paths.get("C:\\project\\99.etc\\practice\\upload\\", getOldFolderYesterDay()).toFile();
		
		File[] removeFils = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		log.warn("------------------------------------------");
		
		for(File file : removeFils) {
			
			log.warn(file.getAbsoluteFile());
			file.delete();
		}
	}
	
	
	private String getOldFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
}
