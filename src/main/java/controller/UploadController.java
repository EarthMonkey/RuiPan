package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

//import serviceImpl.FileServiceImpl;

@Controller
public class UploadController {

	@RequestMapping(value = "/one", method = RequestMethod.GET)
	public String index() {
		return "Test_oneUpload";
	}

	@RequestMapping("/oneUpload")
	@ResponseBody
	public String oneUpload(@RequestParam("oneFile") MultipartFile oneFile, HttpServletRequest request){

		System.out.println("hhhhhhhhh");
		// TODO: 2016/7/26 取id ，用来创建文件名，返回id用来存储
		String uploadUrl=request.getSession().getServletContext().getRealPath("/");

		String filename = oneFile.getOriginalFilename();



		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		System.out.println("文件上传到: " + uploadUrl + filename);
		
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

		return "SUCCESS";
	}



	@RequestMapping("/moreUpload")
	public String moreUpload(HttpServletRequest request){


		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
		
		String uploadUrl = request.getSession().getServletContext().getRealPath("/")
				+ "more_file/";
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
//		List<String> fileList = new ArrayList<String>();
		
		for (MultipartFile file :  files.values()) {
			File targetFile = new File(uploadUrl + file.getOriginalFilename());
			if (!targetFile.exists()) {
				try {
					targetFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					file.transferTo(targetFile);
//					fileList.add("http://localhost:8082/upload/" + file.getOriginalFilename());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
//		request.setAttribute("files", fileList);
		return null;
	}
	
}
