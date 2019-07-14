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
		System.out.println("Action�е�save����ִ����");
		testService.save(test);
		return "test";
	}
	
	
	


	/**
	 * 
	 * �ļ��ϴ��ṩ���������ԣ�
	 */
	private String uploadFileName;     //�ļ�����
	private File upload; 	           //�ϴ��ļ�
	private String  uploadContextType; //�ļ�����
	
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
			//�ļ��ϴ�
			//�����ļ��ϴ�·��
			String path = "D:/upload";
			//һ��Ŀ¼�´�ŵ���ͬ�ļ���������ļ���
			String uuidFileName = StorageUtils.getUuidFileName(uploadFileName);
			//һ��Ŀ¼�´�ŵ��ļ����࣬Ŀ¼����
			String realPath = StorageUtils.getPath(uuidFileName);
			//����Ŀ¼��
			String url = path + realPath;
			File file = new File(path+realPath);
			if(file.exists()) {
				file.mkdirs();
			}
			//�ļ��ϴ�
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
		}
		return null;
	}
	
	
	
	//����ʱ������ļ�������
	private String fileName;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	//���ص��ļ�����,ҳ���װ������
	private String file_id;
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
	//��ȡ�ļ��ķ���
	public InputStream getInputStream() throws UnsupportedEncodingException, FileNotFoundException {
		System.out.println("getInputStream() throws UnsupportedEncodingException, FileNotFoundException");
		String fileName = getFileNameById(file_id);
		System.out.println("fileName" + fileName);
		this.fileName = fileName;
		this.fileName = URLEncoder.encode(fileName,"UTF-8");
		String path = "/upload/" + fileName;
		System.out.println(path);
		File file= new File("D:/upload/����.jpg");
		InputStream in = new FileInputStream(file);
		return in;
		//return ServletActionContext.getServletContext().getResourceAsStream("D:\\upload/1.jpg");
	}
	
	public String download() {
		//�ж��Ƿ��¼�ȵ�
		if(true) {
			return "success";
		}
		return null;
	}
	
	
	//ģ������ݿ��в����ļ���
	private String getFileNameById(String fileId) {
		Map<String,String> data = new HashMap<String,String>();
		data.put("1000","���մ�.jpg");
		return data.get(fileId);
		
		
	}
	
	
	
}
