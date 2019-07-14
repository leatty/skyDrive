package xin.trooper.storage.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import xin.trooper.storage.domain.Share;
import xin.trooper.storage.dao.impl.StorageDaoImpl;
import xin.trooper.storage.domain.RealFile;
import xin.trooper.storage.domain.RecycleBin;
import xin.trooper.storage.domain.Users;
import xin.trooper.storage.domain.VirtualFile;
import xin.trooper.storage.service.StorageService;
import xin.trooper.storage.utils.StorageUtils;


public class StorageAction  extends ActionSupport{

	//Springע��StorageService
	private StorageService storageService;
	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}
	
	
	//������������������������������������������������������������������������������������������������   �ļ��ϴ�  ������������������������ ��������������������������������������������������������������������������
	/**
	 * 
	 *�ļ��ϴ�
	 * 
	 */
	//�ļ��ϴ�Ҫ׼�����Ժͷ���
	//�ļ��ϴ��ṩ���������Ժ���Ҫ��set����
	private String uploadFileName;     //�ļ�����
	private File upload; 	           //�ϴ��ļ�
	private String uploadContextType; //�ļ�����
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
		System.out.println("==StorageAction.upload()");
		String status = null;
		status = storageService.upload(uploadFileName, upload, uploadContextType);
		System.out.println("[status: " + status + " ]");
		return status;
		
	}
	//������������������������������������������������������������������������������������������������   �ļ��ϴ�   ��������������������������������������������������������������������������������������������������
	
	
	
	
	
	
	
	
	
	//������������������������������������������������������������������������������������������������   �ļ�����   ��������������������������������������������������������������������������������������������������
	/**
	 * 
	 * 
	 * �ļ�����
	 */
	//����ʱ��������ļ������֣���ֵ��struts.xml�е�fileName
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	//���ص������ļ�ID,ҳ���װ������
	private String virfile_id;
	public String getVirfile_id() {
		return virfile_id;
	}
	public void setVirfile_id(String virfile_id) {
		this.virfile_id = virfile_id;
	}
	//��ȡ�ļ��ķ���
	public InputStream getInputStream() throws UnsupportedEncodingException, FileNotFoundException {
		List<String> list = storageService.download(Integer.parseInt(virfile_id));
		String fileName = list.get(0);
		this.fileName = fileName;
		this.fileName = URLEncoder.encode(fileName,"UTF-8");
		System.out.println("this.fileName :" + this.fileName + "fileName : " + fileName);
		String path = list.get(1);
		System.out.println("path : " + path);
		File file= new File(path);
		InputStream inPutStream = new FileInputStream(file);
		return inPutStream;
		//return ServletActionContext.getServletContext().getResourceAsStream("D:\\upload/1.jpg");
	}
	public String download(){
		System.out.println("==StorageAction.download()");
		return "download";
	}
	//������������������������������������������������������������������������������������������������   �ļ�����   ��������������������������������������������������������������������������������������������������
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 *��ѯ��¼�û������д洢�ļ�
	 * 
	 */
	public String userFileView() {
		System.out.println("==StorageAction.userFileView()");
//		HttpSession session = ServletActionContext.getRequest().getSession();
//		Users users = (Users) session.getAttribute("existUser");
//		System.out.println(users);
		return storageService.findALLStorageFile();
		
	}

	
	
	/**
	 * 
	 * �ļ�������
	 */
	public String rename() {
		System.out.println("==StorageAction.rename()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int virfile_id = Integer.parseInt(request.getParameter("virfile_id"));
		String new_virfile_name = request.getParameter("virfile_name");
		System.out.println("virfile_id : " + virfile_id + ", new_virfile_name = : " + new_virfile_name);
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(virfile_id);
		virtualFile.setVirfile_name(new_virfile_name);
		return storageService.rename(virtualFile);
	
	}
	
	
	
	/**
	 * 
	 *�����ļ�ɾ�����ļ�����վ��¼��ӣ�
	 */
	public String virdelete() {
		System.out.println("==StorageAction.virdelete()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		//int user_id = Integer.parseInt(request.getParameter("user_id"));
		int virfile_id = Integer.parseInt(request.getParameter("virfile_id"));
		System.out.println("virfile_id = " + virfile_id);
		return storageService.virdelete(virfile_id);
	}
	
	
	
	/**
	 * 
	 * �ļ�����վ��ѯ
	 */
	public String recycleBinView() {
		System.out.println("==StorageAction.recycleBinView()");
		return storageService.findAllRecycleBin();
		
	}
	

	
	/**
	 * 
	 * ����վ�ļ�ɾ��(����ɾ��)
	 */
	public String delete() {
		System.out.println("==StorageAction.delete()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int garbage_id = Integer.parseInt(request.getParameter("garbage_id"));
		System.out.println("garbage_id : " + garbage_id);
		RecycleBin recycleBin = new RecycleBin();
		recycleBin.setGarbage_id(garbage_id);
		return storageService.delete(recycleBin);
	}

	
	
	
	/**
	 *����վ-�ָ�ɾ�����ļ� 
	 */
	public String recover() {
		System.out.println("==StorageAction.recover()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int garbage_id = Integer.parseInt(request.getParameter("garbage_id"));
		System.out.println("garbage_id : " + garbage_id);
		RecycleBin recycleBin = new RecycleBin();
		recycleBin.setGarbage_id(garbage_id);
		return storageService.recover(recycleBin);
	}

	
	
	

	
	
	/**
	 * 
	 * �ļ��ƶ�
	 */
//	public String fileRemove() {
//		VirtualFile virtualPath = new VirtualFile();
//		return storageService.fileRemove(realFile,virtualPath);
//		
//	}
	
	
	/**
	 * 
	 * �ļ�����
	 */
//	public String fileCopy() {
//		
//		VirtualFile virtualPath = new VirtualFile();
//		return storageService.fileCopy(realFile,virtualPath);
//		
//	}
	
	
	
	
	
	
	
	/**
	 * 
	 * �ļ�����
	 */
	public String sort() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String sortType = request.getParameter("sortType");
		System.out.println("sortType : " + sortType);
		return storageService.sort(sortType);
	}
	
	
	
	
	
	/**
	 * 
	 * �ļ�����
	 */
	public String classify() {
		System.out.println("==StorageAction.classify()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String type = request.getParameter("type");
		System.out.println("type : " + type);
		List<String> typeList = StorageUtils.getClassifyType(type);
		for(String s : typeList) {
			System.out.println(s);
		}
		return storageService.classify(typeList);
		
	}
	
	
	
	//struts2�ķ�����ȡҳ�������������HttpServletRequest����Ҳ����
//	private String search_name;
//	
//	public String getSearch_name() {
//		return search_name;
//	}
//	public void setSearch_name(String search_name) {
//		this.search_name = search_name;
//	}
	/**
	 * 
	 * �ļ�����
	 * 
	 */
	public String search() throws UnsupportedEncodingException {
		System.out.println("==StorageAction.search() throws UnsupportedEncodingException");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String search_name = request.getParameter("search_name");
		search_name = java.net.URLDecoder.decode(search_name,"UTF-8");
		System.out.println("search_name : " + search_name);
		VirtualFile virtualFile = new VirtualFile();
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		virtualFile.setVirfile_name(search_name);
		virtualFile.setUser_id(users.getUser_id());
		return storageService.search(virtualFile);
		
	}
	
	
	
	
	
	/**
	 * 
	 * �ļ������ѯ
	 */
	public String shareView() {
		/*
		 * private int share_id; 
		 * private int user_id;
		 * private int virfile_id; 
		 * private Timestamp share_date; 
		 * private String share_password;
		 */
		System.out.println("==StorageAction.recycleBinView()");
		return storageService.findAllShare();
	}
	
	
	/**
	 * 
	 * �ļ���������
	 */
	public String commonShare() {
		Share share = new Share();
		System.out.println("==StorageAction.commonShare()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int virfile_id = Integer.parseInt(request.getParameter("virfile_id"));
		share.setVirfile_id(virfile_id);
		return storageService.commonShare(share);
		
	}
	
	
	/**
	 * 
	 * �ļ����ܷ���
	 */
	public String privateShare() {
		System.out.println("==StorageAction.privateShare()");
		Share share = new Share();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int virfile_id = Integer.parseInt(request.getParameter("virfile_id"));
		share.setVirfile_id(virfile_id);
		return storageService.privateShare(share);
	}
	
	
	/**
	 * 
	 * ȡ���ļ�����
	 */
	public String shareDelete() {
		System.out.println("==StorageAction.shareDelete()");
		Share share = new Share();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int share_id = Integer.parseInt(request.getParameter("share_id"));
		share.setShare_id(share_id);
		return storageService.shareDelete(share);
	}
	
	
	
	
	
	/**
	 * ---�����ļ�����Ԥ�Ȳ���---
	 * ���ݷ���ID��ѯ��������˽��
	 * ���ݷ�������ͬ��ת����ͬ�ķ�������ҳ��
	 * ����ǹ��������������������ɹ��������ļ�������ҵ���߼�
	 * �����˽�ܷ����������ֻ���˽�ܷ����׼����������ת��������֤ҳ��
	 * ˽�ܷ������һ������ҵ���߼�������ķ���privateShareDownload()����
	 */
	public String shareDownload() {
		System.out.println("==StorageAction.shareDownload()");
		Share share = new Share();
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int share_id = Integer.parseInt(request.getParameter("share_id"));
		share.setShare_id(share_id);
		System.out.println(share);
		return storageService.shareDownload(share);
	}
	
	
	/**
	 * 
	 * ˽�ܷ����ļ�����
	 */
	public String privateShareDownload() {
		System.out.println("==StorageAction.privateShareDownload()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int share_id = Integer.parseInt(request.getParameter("share_id"));
		String share_password = request.getParameter("share_password");
		System.out.println("share_id : " + share_id + " , share_password : " + share_password);
		return storageService.privateShareDownload(share_id,share_password);
	}
	
	
	
	/**
	 * 
	 * ��ƵԤ��
	 */
	public String videoPreview() {
		System.out.println("==StorageAction.videoPreview()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int virfile_id = Integer.parseInt(request.getParameter("virfile_id"));
		System.out.println(virfile_id);
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(virfile_id);
		return storageService.videoPreview(virtualFile);
	}
	
	
	
	/**
	 * 
	 * ͼƬԤ��
	 * @throws IOException 
	 */
	public String imagePreview() throws IOException {
		System.out.println("==StorageAction.videoPreview()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int virfile_id = Integer.parseInt(request.getParameter("virfile_id"));
		System.out.println(virfile_id);
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(virfile_id);
		storageService.imagePreview(virtualFile);
		return null;
	}
	
	
	
	
	/**
	 * 
	 * ��ƵԤ��
	 * @throws IOException 
	 */
	public String musicPreview() throws IOException {
		System.out.println("==StorageAction.musicPreview()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int virfile_id = Integer.parseInt(request.getParameter("virfile_id"));
		System.out.println(virfile_id);
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(virfile_id);
		storageService.musicPreview(virtualFile);
		return null;
	}
	
	/**
	 * 
	 * �ĵ�Ԥ��
	 * @throws IOException 
	 */
	public String documentPreview() throws IOException {
		System.out.println("==StorageAction.documentPreview()");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		int virfile_id = Integer.parseInt(request.getParameter("virfile_id"));
		System.out.println(virfile_id);
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(virfile_id);
		return storageService.documentPreview(virtualFile);
	}
	
}
