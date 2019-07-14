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

	//Spring注入StorageService
	private StorageService storageService;
	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}
	
	
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓   文件上传  ↓↓↓↓↓↓↓↓↓↓↓↓ ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	/**
	 * 
	 *文件上传
	 * 
	 */
	//文件上传要准备属性和方法
	//文件上传提供的三个属性和需要的set方法
	private String uploadFileName;     //文件名称
	private File upload; 	           //上传文件
	private String uploadContextType; //文件类型
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
	//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑   文件上传   ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	
	
	
	
	
	
	
	
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓   文件下载   ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	/**
	 * 
	 * 
	 * 文件下载
	 */
	//下载时保存框中文件的名字，赋值给struts.xml中的fileName
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	//下载的虚拟文件ID,页面封装过来的
	private String virfile_id;
	public String getVirfile_id() {
		return virfile_id;
	}
	public void setVirfile_id(String virfile_id) {
		this.virfile_id = virfile_id;
	}
	//读取文件的方法
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
	//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑   文件下载   ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 *查询登录用户的所有存储文件
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
	 * 文件重命名
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
	 *虚拟文件删除（文件回收站记录添加）
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
	 * 文件回收站查询
	 */
	public String recycleBinView() {
		System.out.println("==StorageAction.recycleBinView()");
		return storageService.findAllRecycleBin();
		
	}
	

	
	/**
	 * 
	 * 回收站文件删除(真正删除)
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
	 *回收站-恢复删除的文件 
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
	 * 文件移动
	 */
//	public String fileRemove() {
//		VirtualFile virtualPath = new VirtualFile();
//		return storageService.fileRemove(realFile,virtualPath);
//		
//	}
	
	
	/**
	 * 
	 * 文件复制
	 */
//	public String fileCopy() {
//		
//		VirtualFile virtualPath = new VirtualFile();
//		return storageService.fileCopy(realFile,virtualPath);
//		
//	}
	
	
	
	
	
	
	
	/**
	 * 
	 * 文件排序
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
	 * 文件分类
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
	
	
	
	//struts2的方法获取页面参数，或者用HttpServletRequest对象也可以
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
	 * 文件搜索
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
	 * 文件分享查询
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
	 * 文件公开分享
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
	 * 文件加密分享
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
	 * 取消文件分享
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
	 * ---分享文件下载预先步骤---
	 * 根据分享ID查询公开或者私密
	 * 根据分享方法不同跳转到不同的分享下载页面
	 * 如果是公开分享，这个方法可以完成公开分享文件的下载业务逻辑
	 * 如果是私密分享，这个方法只完成私密分享的准备工作，跳转到密码验证页面
	 * 私密分享的下一步处理业务逻辑在下面的方法privateShareDownload()给出
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
	 * 私密分享文件下载
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
	 * 视频预览
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
	 * 图片预览
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
	 * 音频预览
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
	 * 文档预览
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
