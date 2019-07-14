package xin.trooper.storage.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import xin.trooper.storage.domain.Test;
import xin.trooper.storage.service.TestService;
import xin.trooper.storage.utils.StorageUtils;

public class TestAction extends ActionSupport implements ModelDriven<Test> {

	private Test test = new Test();
	@Override
	public Test getModel() {
		return test;
	}

	
	private TestService testService;
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public String save() {
		System.out.println("Action中的save方法执行了");
		testService.save(test);
		return "test";
	}
	
	
	


	/**
	 * 
	 * 文件上传提供的三个属性：
	 */
	private String uploadFileName;     //文件名称
	private File upload; 	           //上传文件
	private String  uploadContextType; //文件类型
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	
	
	
	public String upload() throws IOException {
		System.out.println(uploadFileName);
		System.out.println(upload);
		System.out.println(uploadContextType);
		if(upload != null) {
			//文件上传
			//设置文件上传路径
			String path = "D:/upload";
			//一个目录下存放的相同文件名：随机文件名
			String uuidFileName = StorageUtils.getUuidFileName(uploadFileName);
			//一个目录下存放的文件过多，目录分离
			String realPath = StorageUtils.getPath(uuidFileName);
			//创建目录：
			String url = path + realPath;
			File file = new File(path+realPath);
			if(file.exists()) {
				file.mkdirs();
			}
			//文件上传
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
		}
		return null;
	}
	
	
	
	//下载时保存的文件的名字
	private String fileName;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	//下载的文件名字,页面封装过来的
	private String file_id;
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
	//读取文件的方法
	public InputStream getInputStream() throws UnsupportedEncodingException, FileNotFoundException {
		System.out.println("getInputStream() throws UnsupportedEncodingException, FileNotFoundException");
		String fileName = getFileNameById(file_id);
		System.out.println("fileName" + fileName);
		this.fileName = fileName;
		this.fileName = URLEncoder.encode(fileName,"UTF-8");
		String path = "/upload/" + fileName;
		System.out.println(path);
		File file= new File("D:/upload/撒旦.jpg");
		InputStream in = new FileInputStream(file);
		return in;
		//return ServletActionContext.getServletContext().getResourceAsStream("D:\\upload/1.jpg");
	}
	
	public String download() {
		//判断是否登录等等
		if(true) {
			return "success";
		}
		return null;
	}
	
	
	//模拟从数据库中查找文件名
	private String getFileNameById(String fileId) {
		Map<String,String> data = new HashMap<String,String>();
		data.put("1000","大苏打.jpg");
		return data.get(fileId);
		
		
	}
	
	
	
}
