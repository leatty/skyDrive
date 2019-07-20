package xin.trooper.storage.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import xin.trooper.storage.dao.StorageDao;
import xin.trooper.storage.domain.Share;
import xin.trooper.storage.domain.RealFile;
import xin.trooper.storage.domain.RecycleBin;
import xin.trooper.storage.domain.Users;
import xin.trooper.storage.domain.VirtualFile;
import xin.trooper.storage.service.StorageService;
import xin.trooper.storage.utils.StorageUtils;


@Transactional
public class StorageServiceImpl implements StorageService {

	
	private StorageDao storageDao;
	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	static Properties properties = new Properties(); 
	static{
		
		InputStream in = StorageServiceImpl.class.getClassLoader().getResourceAsStream("storage.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 文件上传
	 */
	@Override
	public String upload(String uploadFileName,File upload,String  uploadContextType, String md5) throws IOException {
		System.out.println("==StorageServiceImpl.upload(String uploadFileName,File upload,String  uploadContextType)");
		String status = null;
		if(upload != null) {
			System.out.println("uploadFileName: " + uploadFileName + "  md5: " + md5);
			//设置文件上传路径,path在storage.properties中的path键定义  
			String path = properties.getProperty("path");
			System.out.println("path: " + path);
			//一个目录下存放的相同文件名：随机文件名
			//uuidFileName: "uuidFileName:5544c4dd87f347dba2501f7614c79c4c.jpg"
			String uuidFileName = StorageUtils.getUuidFileName(uploadFileName);
			System.out.println("uuidFileName: "+uuidFileName);
			//一个目录下存放的文件过多，目录分离(在上传路径中添加两级目录，文件随机存入不同的目录) dirPath: "/1/6"
			String dirPath = StorageUtils.getPath(uuidFileName);
			//创建目录：  partPath:"D:/upload/1/6"
			String partPath = path + dirPath;
			File mkrfile = new File(partPath);
			if(mkrfile.exists()) {
			}else {
				mkrfile.mkdirs();
			}
			//生成copyFile()方法需要的存储路径
			//storagePath: "D:/upload/1/6/5544c4dd87f347dba2501f7614c79c4c.jpg"
			String storagePath = partPath + "/" + uuidFileName;
			System.out.println("storagePath: " + storagePath);
			//上传文件
			//先判断文件是否在服务端已经存在
			List<RealFile> xlist = storageDao.getRealFileByHash(md5);
			boolean isEmpty = xlist.isEmpty();
			File dictFile = new File(storagePath);
			String fileMD5 = null;
			Long file_size = null;
			if(isEmpty) {
				System.out.println("file no exist!");
				FileUtils.copyFile(upload, dictFile);
				//file_size = FileUtils.sizeOf(dictFile);
				File file = new File(storagePath);
				fileMD5 = StorageUtils.getFileMD5(file);
				file_size = file.length();
				System.out.println("fileMD5: " + fileMD5);
				if(fileMD5.equals(md5)) {
					System.out.println("传输过程正确！");
				}else {
					System.out.println("传输过程出错！");
					dictFile.delete();
				}
			}else {
				System.out.println("file exist!");
				for(RealFile r : xlist) {
					System.out.println(r);
				}
				fileMD5 = md5;
				File file = new File(path + xlist.get(0).getFile_path());
				file_size = file.length();
			}
			
		    //数据库中添加文件记录，virtualfIle表和Realfile表
			//1.获取当前登录用户信息
			HttpSession session = ServletActionContext.getRequest().getSession();
			Users users = (Users) session.getAttribute("loginedUser");
			System.out.println(users);
			
			VirtualFile virtualFile = new VirtualFile();
			virtualFile.setVirfile_name(uploadFileName);
			virtualFile.setVirfile_ext(StorageUtils.getExtName(uploadFileName));
			virtualFile.setVirfile_path("myfile");
			virtualFile.setVirfile_size(file_size);
			virtualFile.setUpload_date(new Timestamp((new java.util.Date()).getTime()));
			virtualFile.setUser_id(users.getUser_id());
			if(isEmpty) {
				RealFile realFile = new RealFile();
				realFile.setFile_name(uuidFileName);
				String file_path = storagePath.replace(path, "");
				realFile.setFile_path(file_path);
				realFile.setFile_hash(fileMD5);
				realFile.setFile_amount(1);
				System.out.println(realFile);
				int file_id = storageDao.saveRealFile(realFile);
				virtualFile.setFile_id(file_id);
				System.out.println(virtualFile);
				storageDao.saveVirtualFile(virtualFile);
			}else {
				RealFile realFile = xlist.get(0);
				int num = realFile.getFile_amount();
				realFile.setFile_amount(num + 1);
				storageDao.updateRealFile(realFile);
				virtualFile.setFile_id(realFile.getFile_id());
				System.out.println(virtualFile);
				storageDao.saveVirtualFile(virtualFile);
			}
			dictFile = null;
			//查询用户文件列表,压入值栈
			this.findALLStorageFile();
			status = "upload_success";
		}else {
			status= "upload_error";
		}
		return status;	
	}

	


	/**
	 * 
	 * 获取用户存储的文件列表,放入值栈
	 */
	@Override
	public String findALLStorageFile() {
		System.out.println("==StorageServiceImpl.findALLStorageFile()");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		System.out.println(users);
		List<VirtualFile> userFileList = storageDao.getALllVirtualFile(users);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("userFileList", userFileList);
		return "virtualfile_view";
	}



	/**  
	    * 从服务器上 删除文件  
	 * @param fileName 文件名  
	 * @return true: 从服务器上删除成功   false:否则失败  
	 */  
	public boolean deleteStorageFile(String storagePath){
		Boolean result = false;
		System.out.println("storagePath = " + storagePath);
	    File file=new File(storagePath);   
	    if(file.exists()){ 
	    	System.out.println("file exist, deleting...");
	    	result =  file.delete();   
	    }else {
	    	System.out.println("not exist");
	    }   
	    return result;   
	}

	/**
	 * 
	 * 文件删除(真实文件删除)
	 */
	@Override
	public String delete(RecycleBin recycleBin) {
		String status = null;
		System.out.println("==StorageServiceImpl.delete(RecycleBin recycleBin)");
		//查看真实文件的引用数
		List<RecycleBin> recyclebinlist = storageDao.getRecycleBinById(recycleBin);
		recycleBin = recyclebinlist.get(0);
		System.out.println(recycleBin);
		RealFile realFile = new RealFile();
		realFile.setFile_id(recycleBin.getFile_id());
		List<RealFile> realfilelist = (List<RealFile>) storageDao.getRealFileById(realFile);
		realFile = realfilelist.get(0);
		System.out.println(realFile);
	    //根据真实文件引用数进行不同操作
		if(realFile.getFile_amount() > 1) {
			//大于1则删除回收站记录，并真实文件引用数减一
			storageDao.deleteRecycleBin(recycleBin);
			realFile.setFile_amount(realFile.getFile_amount() - 1);
			storageDao.updateRealFile(realFile);
			status = this.findAllRecycleBin();
		}else {
			//等于1则删除回收站记录，同时删除真实文件记录和存储的文件
			String storagePath = properties.getProperty("path") + realFile.getFile_path();
			System.out.println("storagePath: " + storagePath);
			if(deleteStorageFile(storagePath)) {
				storageDao.deleteRecycleBin(recycleBin);
				storageDao.deleteRealFile(realFile);
				status = this.findAllRecycleBin();
			}else{
				status = "delete_error";
			}
		}
		return status;
	}





	/**
	 * 
	 * 文件重命名
	 */
	@Override
	public String rename(VirtualFile virtualFile) {
		System.out.println("==StorageServiceImpl.rename(VirtualFile virtualFile)");
		System.out.println(virtualFile);
		List<VirtualFile> list = storageDao.getVirtualFileById(virtualFile);
		VirtualFile newVirFile = new VirtualFile();
		newVirFile = list.get(0);
		newVirFile.setVirfile_id(virtualFile.getVirfile_id());
		newVirFile.setVirfile_name(virtualFile.getVirfile_name());
		System.out.println(newVirFile);
		storageDao.updateVirtualFile(newVirFile);
		this.findALLStorageFile();
		return "rename_success";
	}





	/**
	 * 下载文件时-提供(真实)文件的下载路径和(虚拟)文件的名字
	 */
	@Override
	public List<String> download(int virfile_id) {
		System.out.println("==StorageServiceImpl.download(String virfile_id)");
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(virfile_id);
		System.out.println(virtualFile);
		List<VirtualFile> virlist = storageDao.getVirtualFileById(virtualFile);
		virtualFile = virlist.get(0);
		System.out.println(virtualFile);
		RealFile realFile = new RealFile();
		realFile.setFile_id(virtualFile.getFile_id());
		List<RealFile> reallist = storageDao.getRealFileById(realFile);
		realFile = reallist.get(0);
		System.out.println("file_path : " + realFile.getFile_path());
		List<String> list = new ArrayList<String>();
		list.add(0, virtualFile.getVirfile_name());
		String storagePath = properties.getProperty("path") + realFile.getFile_path();
		list.add(1, storagePath);
		return list;
	}






	@Override
	public String fileCopy(RealFile userFile, VirtualFile virtualPath) {
		return null;
	}





	/**
	 * 文件排序
	 * 就是在查询的时候加个orderby
	 */
	@Override
	public String sort(String sortType) {
		System.out.println("==StorageServiceImpl.sort(String sortType)");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		System.out.println(users);
		List<VirtualFile> userFileList = storageDao.getAllVirtualFileBySortType(users,sortType);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("userFileList", userFileList);
		return "virtualfile_view";
	}





	/**
	 * 分类获取用户的文件列表-按照类型（图片、文档、视频、音乐）
	 */
	@Override
	public String classify(List<String> typeList) {
		System.out.println("==StorageServiceImpl.classify(List<String> list)");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		System.out.println(users);
		List<VirtualFile> userFileList = storageDao.getVirtualFileByExtName(users,typeList);
		for(VirtualFile vf : userFileList) {
			System.out.println(vf);
		}
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("userFileList", userFileList);
		return "classify_success";
	}






	





	/**
	 * 公共分享文件
	 */
	@Override
	public String commonShare(Share share) {
		System.out.println("==StorageServiceImpl.commonShare(Share share)");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		System.out.println(users);
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(share.getVirfile_id());
		List<VirtualFile> list = storageDao.getVirtualFileById(virtualFile);
		virtualFile = list.get(0);
		System.out.println(virtualFile);
		share.setUser_id(users.getUser_id());
		share.setShare_name(virtualFile.getVirfile_name());
		share.setShare_date(new Timestamp((new java.util.Date()).getTime()));
		share.setShare_password("common_share");
		storageDao.saveShare(share);
		this.findALLStorageFile();
		return "share_success";
	}





	/**
	 * 私密分享文件
	 */
	@Override
	public String privateShare(Share share) {
		System.out.println("==StorageServiceImpl.commonShare(Share share)");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		System.out.println(users);
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(share.getVirfile_id());
		List<VirtualFile> list = storageDao.getVirtualFileById(virtualFile);
		virtualFile = list.get(0);
		System.out.println(virtualFile);
		share.setUser_id(users.getUser_id());
		share.setShare_name(virtualFile.getVirfile_name());
		share.setShare_date(new Timestamp((new java.util.Date()).getTime()));
		share.setShare_password(StorageUtils.getRandomPassword());
		storageDao.saveShare(share);
		this.findALLStorageFile();
		return "share_success";
	}





	/**
	 * 获取用户的分享列表,放入值栈
	 */
	@Override
	public String findAllShare() {
		System.out.println("==StorageServiceImpl.findAllShare()");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		System.out.println(users);
		List<Share> shareList = storageDao.getALllShare(users);
		String partURL = properties.getProperty("server_protocol") + "://" + properties.getProperty("server_name") + ":" + properties.getProperty("server_port");
		System.out.println("partURL: " + partURL);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("partURL", partURL);
		valueStack.set("shareList", shareList);
		return "share_view";
	}





	/**
	 * 文件分享删除
	 */
	@Override
	public String shareDelete(Share share) {
		System.out.println("==StorageServiceImpl.shareDelete(Share share)");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		System.out.println(users);
		share.setUser_id(users.getUser_id());
		List<Share> list = storageDao.getShareById(share);
		share = list.get(0);
		storageDao.deleteShare(share);
		this.findAllShare();
		return "sharedelete_success";
	}





	/**
	 * 删除虚拟文件记录并创建回收站记录
	 */
	@Override
	public String virdelete(int virfile_id) {
		System.out.println("==StorageServiceImpl.virdelete(int virfile_id)");
		//删除虚拟文件记录
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(virfile_id);
		List<VirtualFile> list = storageDao.getVirtualFileById(virtualFile);
		virtualFile = list.get(0);
		System.out.println(virtualFile);
		storageDao.deleteVirtualFile(virtualFile);
		//删除分享文件
		Share share = new Share();
		share.setVirfile_id(virtualFile.getVirfile_id());
		System.out.println(share);
		storageDao.deleteShareByVirfileId(share);
		//添加回收站记录
		RecycleBin recycleBin = new RecycleBin();
		recycleBin.setDelete_date(new Timestamp((new java.util.Date()).getTime()));
		System.out.println("time = " + recycleBin.getDelete_date());
		recycleBin.setVirfile_name(virtualFile.getVirfile_name());
		recycleBin.setVirfile_ext(virtualFile.getVirfile_ext());
		recycleBin.setVirfile_path(virtualFile.getVirfile_path());
		recycleBin.setVirfile_size(virtualFile.getVirfile_size());
		recycleBin.setUpload_date(virtualFile.getUpload_date());
		recycleBin.setUser_id(virtualFile.getUser_id());
		recycleBin.setFile_id(virtualFile.getFile_id());
		storageDao.saveRecycleBin(recycleBin);
		//查询用户文件列表,压入值栈
		this.findALLStorageFile();
		return  "virdelete_success";
	}




	/**
	 * 获取用户回收站列表，存入值栈
	 */
	@Override
	public String findAllRecycleBin() {
		System.out.println("==StorageServiceImpl.findAllRecycleBin()");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users) session.getAttribute("loginedUser");
		System.out.println(users);
		List<RecycleBin> recycleBinList = storageDao.getAllRecycleBin(users);
		for(RecycleBin rc : recycleBinList) {
			System.out.println(rc);
		}
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("recycleBinList", recycleBinList);
		return "recyclebin_view";
	}





	/**
	 * 恢复被删除的文件
	 */
	@Override
	public String recover(RecycleBin recycleBin) {
		System.out.println("==StorageServiceImpl.recover(RecycleBin recycleBin)");
		//删除回收站记录
		List<RecycleBin> list = storageDao.getRecycleBinById(recycleBin);
		recycleBin = list.get(0);
		System.out.println(recycleBin);
		storageDao.deleteRecycleBin(recycleBin);
		//添加虚拟文件记录
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_name(recycleBin.getVirfile_name());
		virtualFile.setVirfile_ext(recycleBin.getVirfile_ext());
		virtualFile.setVirfile_path(recycleBin.getVirfile_path());
		virtualFile.setVirfile_size(recycleBin.getVirfile_size());
		virtualFile.setUpload_date(recycleBin.getUpload_date());
		virtualFile.setFile_id(recycleBin.getFile_id());
		virtualFile.setUser_id(recycleBin.getUser_id());
		storageDao.saveVirtualFile(virtualFile);
		this.findAllRecycleBin();
		return "recover_success";
	}





	/**
	 * 公共分享文件下载&部分私密分享下载的逻辑
	 */
	@Override
	public String shareDownload(Share share) {
		System.out.println("==StorageServiceImpl.shareDownload(Share share)");
		String status = null; 
		List<Share> sharelist = storageDao.getShareById(share);
		share = sharelist.get(0);
		System.out.println(share);
		//根据分享类型返回不同的‘status’
		String shareType  = share.getShare_password();
		if(shareType.equals("common_share")) {
			//公共分享
			VirtualFile virtualFile = new VirtualFile();
			virtualFile.setVirfile_id(share.getVirfile_id());
			List<VirtualFile> virlist = storageDao.getVirtualFileById(virtualFile);
			virtualFile = virlist.get(0);
			System.out.println(virtualFile);
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("virfile_id", virtualFile.getVirfile_id());
			valueStack.set("virfile_name", virtualFile.getVirfile_name());
			valueStack.set("virfile_size", virtualFile.getVirfile_size());
			status = "sharedownload";
		}else {
			//私密分享部分逻辑
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("share_id", share.getShare_id());
			status = "previoussharedownload";
		}
		return status;
	}





	/**
	 * 私密分享文件下载
	 */
	@Override
	public String privateShareDownload(int share_id, String share_password) {
		System.out.println("==StorageServiceImpl.privateShareDownload(int share_id, String share_password)");
		String status = null;
		Share share = new Share();
		share.setShare_id(share_id);
		List<Share> list = storageDao.getShareById(share);
		share = list.get(0);
		System.out.println(share);
		String correct_password = share.getShare_password();
		//根据密码是否正确执行不同的逻辑
		if(correct_password.equals(share_password)) {
			//密码正确
			VirtualFile virtualFile = new VirtualFile();
			virtualFile.setVirfile_id(share.getVirfile_id());
			List<VirtualFile> virlist = storageDao.getVirtualFileById(virtualFile);
			virtualFile = virlist.get(0);
			System.out.println(virtualFile);
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("virfile_id", virtualFile.getVirfile_id());
			valueStack.set("virfile_name", virtualFile.getVirfile_name());
			valueStack.set("virfile_size", virtualFile.getVirfile_size());
			status = "sharepassword_right";
		}else {
			//密码错误
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("share_id", share.getShare_id());
			valueStack.set("tips","密码错误");
			status = "sharepassword_error";
		}
		return status;
	}





	/**
	 * 
	 * 文件搜索
	 */
	@Override
	public String search(VirtualFile virtualFile) {
		System.out.println("==StorageServiceImpl.search(VirtualFile virtualFile)");
		List<VirtualFile> searchedList = storageDao.getVirtualFileByName(virtualFile);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("userFileList", searchedList);
		return "search_success";
	}



	/**
	 * 
	 * 获取预览文件的URL
	 */
	public String getPreviewURL(String file_path) {
		String server_protocol = properties.getProperty("server_protocol");
		String server_name = properties.getProperty("server_name");
		String server_port = properties.getProperty("server_port");
		String server_resource = properties.getProperty("server_resource");
		String previewURL = server_protocol + "://" + server_name + ":" + server_port + "/" + server_resource + file_path;
		System.out.println("previewURL: " + previewURL);
		return previewURL;
		
	}


	/**
	 * 
	 * 视频预览
	 */
	@Override
	public String videoPreview(VirtualFile virtualFile) {
		System.out.println("==StorageServiceImpl.videoPreview(VirtualFile virtualFile)");
		List<VirtualFile> vlist = storageDao.getVirtualFileById(virtualFile);
		virtualFile = vlist.get(0);
		System.out.println(virtualFile);
		RealFile realFile = new RealFile();
		realFile.setFile_id(virtualFile.getFile_id());
		List<RealFile> rlist = storageDao.getRealFileById(realFile);
		realFile = rlist.get(0);
		System.out.println(realFile);
		String file_path = realFile.getFile_path();
		String videoURL = getPreviewURL(file_path);
		String video_name = virtualFile.getVirfile_name();
		int virfile_id = virtualFile.getVirfile_id();
		System.out.println("videoURL : " + videoURL + " | video_name : " + video_name);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("videoURL", videoURL);
		valueStack.set("video_name", video_name);
		valueStack.set("virfile_id", virfile_id);
		return "videoPreview_success";
	}
	
	
	/**
	 * 
	 * 图像预览
	 */
	@Override
	public String imagePreview(VirtualFile virtualFile) {
		System.out.println("==StorageServiceImpl.imagePreview(VirtualFile virtualFile)");
		List<VirtualFile> vlist = storageDao.getVirtualFileById(virtualFile);
		virtualFile = vlist.get(0);
		System.out.println(virtualFile);
		RealFile realFile = new RealFile();
		realFile.setFile_id(virtualFile.getFile_id());
		List<RealFile> rlist = storageDao.getRealFileById(realFile);
		realFile = rlist.get(0);
		System.out.println(realFile);
		String image_path = realFile.getFile_path();
		System.out.println("image_path : " + image_path);
		String imageURL = getPreviewURL(image_path);
		
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(imageURL);
             
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("imageURL", imageURL);
		return "imagePreview_success";
	}





 
	/**
	 * 
	 * 音频预览
	 */
	@Override
	public String musicPreview(VirtualFile virtualFile) {
		System.out.println("==StorageServiceImpl.musicPreview(VirtualFile virtualFile)");
		List<VirtualFile> vlist = storageDao.getVirtualFileById(virtualFile);
		virtualFile = vlist.get(0);
		System.out.println(virtualFile);
		RealFile realFile = new RealFile();
		realFile.setFile_id(virtualFile.getFile_id());
		List<RealFile> rlist = storageDao.getRealFileById(realFile);
		realFile = rlist.get(0);
		System.out.println(realFile);
		String music_path = realFile.getFile_path();
		System.out.println("music_path : " + music_path);
		String musicURL = getPreviewURL(music_path);
		
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(musicURL);
             
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("musicURL", musicURL);
		return "musicPreview_success";
	}





	//文档预览
	@Override
	public String documentPreview(VirtualFile virtualFile) {
		System.out.println("==StorageServiceImpl.musicPreview(VirtualFile virtualFile)");
		List<VirtualFile> vlist = storageDao.getVirtualFileById(virtualFile);
		virtualFile = vlist.get(0);
		System.out.println(virtualFile);
		RealFile realFile = new RealFile();
		realFile.setFile_id(virtualFile.getFile_id());
		List<RealFile> rlist = storageDao.getRealFileById(realFile);
		realFile = rlist.get(0);
		System.out.println(realFile);
		String document_path = realFile.getFile_path();
		System.out.println("document_path : " + document_path);
		String documentURL = getPreviewURL(document_path);
		
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("documentURL", documentURL);
		valueStack.set("virfile_ext", virtualFile.getVirfile_ext());
		return "documentPreview_success";
	}

	
	
	
	
	
}
