package controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import util.SystemLog;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;



@RestController
public class UploadController {

	@RequestMapping(value = "/one", method = RequestMethod.GET)
	public String index() {
		return "Test_oneUpload";
	}

	@RequestMapping("/oneUpload")
	@ResponseBody
	@SystemLog(module = "通用上传管理" ,methods = "图片上传")
	public String oneUpload(@RequestParam("oneFile") MultipartFile oneFile, HttpServletRequest request){


		String uploadUrl=request.getSession().getServletContext().getRealPath("/")+"upload/images/";

		String filename = getUniqueFileName(oneFile.getOriginalFilename());


		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		
		File targetFile = new File(uploadUrl + filename);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			oneFile.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filename;
	}



	@RequestMapping("/moreUpload")
	@ResponseBody
	@SystemLog(module = "通用上传管理" ,methods = "多图片上传")
	public List<String> moreUpload(HttpServletRequest request){


		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
		
		String uploadUrl = request.getSession().getServletContext().getRealPath("/")
				+ "upload/images/";
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		List<String> fileList = new ArrayList<String>();
		
		for (MultipartFile file :  files.values()) {
			String filename=getUniqueFileName(file.getOriginalFilename());
			File targetFile = new File(uploadUrl + filename);
			if (!targetFile.exists()) {
				try {
					targetFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					file.transferTo(targetFile);
					fileList.add(filename);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		return fileList;
	}

	private String getUniqueFileName(String filename){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		int lastIndex=filename.lastIndexOf('.');
		String result=uuid+(lastIndex<0?"":filename.substring(lastIndex));
		return result;
	}

}
