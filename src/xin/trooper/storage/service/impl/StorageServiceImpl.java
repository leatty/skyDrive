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
	 * �ļ��ϴ�
	 */
	@Override
	public String upload(String uploadFileName,File upload,String  uploadContextType, String md5) throws IOException {
		System.out.println("==StorageServiceImpl.upload(String uploadFileName,File upload,String  uploadContextType)");
		String status = null;
		if(upload != null) {
			System.out.println("uploadFileName: " + uploadFileName + "  md5: " + md5);
			//�����ļ��ϴ�·��,path��storage.properties�е�path������  
			String path = properties.getProperty("path");
			System.out.println("path: " + path);
			//һ��Ŀ¼�´�ŵ���ͬ�ļ���������ļ���
			//uuidFileName: "uuidFileName:5544c4dd87f347dba2501f7614c79c4c.jpg"
			String uuidFileName = StorageUtils.getUuidFileName(uploadFileName);
			System.out.println("uuidFileName: "+uuidFileName);
			//һ��Ŀ¼�´�ŵ��ļ����࣬Ŀ¼����(���ϴ�·�����������Ŀ¼���ļ�������벻ͬ��Ŀ¼) dirPath: "/1/6"
			String dirPath = StorageUtils.getPath(uuidFileName);
			//����Ŀ¼��  partPath:"D:/upload/1/6"
			String partPath = path + dirPath;
			File mkrfile = new File(partPath);
			if(mkrfile.exists()) {
			}else {
				mkrfile.mkdirs();
			}
			//����copyFile()������Ҫ�Ĵ洢·��
			//storagePath: "D:/upload/1/6/5544c4dd87f347dba2501f7614c79c4c.jpg"
			String storagePath = partPath + "/" + uuidFileName;
			System.out.println("storagePath: " + storagePath);
			//�ϴ��ļ�
			//���ж��ļ��Ƿ��ڷ�����Ѿ�����
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
					System.out.println("���������ȷ��");
				}else {
					System.out.println("������̳���");
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
			
		    //���ݿ�������ļ���¼��virtualfIle���Realfile��
			//1.��ȡ��ǰ��¼�û���Ϣ
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
			//��ѯ�û��ļ��б�,ѹ��ֵջ
			this.findALLStorageFile();
			status = "upload_success";
		}else {
			status= "upload_error";
		}
		return status;	
	}

	


	/**
	 * 
	 * ��ȡ�û��洢���ļ��б�,����ֵջ
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
	    * �ӷ������� ɾ���ļ�  
	 * @param fileName �ļ���  
	 * @return true: �ӷ�������ɾ���ɹ�   false:����ʧ��  
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
	 * �ļ�ɾ��(��ʵ�ļ�ɾ��)
	 */
	@Override
	public String delete(RecycleBin recycleBin) {
		String status = null;
		System.out.println("==StorageServiceImpl.delete(RecycleBin recycleBin)");
		//�鿴��ʵ�ļ���������
		List<RecycleBin> recyclebinlist = storageDao.getRecycleBinById(recycleBin);
		recycleBin = recyclebinlist.get(0);
		System.out.println(recycleBin);
		RealFile realFile = new RealFile();
		realFile.setFile_id(recycleBin.getFile_id());
		List<RealFile> realfilelist = (List<RealFile>) storageDao.getRealFileById(realFile);
		realFile = realfilelist.get(0);
		System.out.println(realFile);
	    //������ʵ�ļ����������в�ͬ����
		if(realFile.getFile_amount() > 1) {
			//����1��ɾ������վ��¼������ʵ�ļ���������һ
			storageDao.deleteRecycleBin(recycleBin);
			realFile.setFile_amount(realFile.getFile_amount() - 1);
			storageDao.updateRealFile(realFile);
			status = this.findAllRecycleBin();
		}else {
			//����1��ɾ������վ��¼��ͬʱɾ����ʵ�ļ���¼�ʹ洢���ļ�
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
	 * �ļ�������
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
	 * �����ļ�ʱ-�ṩ(��ʵ)�ļ�������·����(����)�ļ�������
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
	 * �ļ�����
	 * �����ڲ�ѯ��ʱ��Ӹ�orderby
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
	 * �����ȡ�û����ļ��б�-�������ͣ�ͼƬ���ĵ�����Ƶ�����֣�
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
	 * ���������ļ�
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
	 * ˽�ܷ����ļ�
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
	 * ��ȡ�û��ķ����б�,����ֵջ
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
	 * �ļ�����ɾ��
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
	 * ɾ�������ļ���¼����������վ��¼
	 */
	@Override
	public String virdelete(int virfile_id) {
		System.out.println("==StorageServiceImpl.virdelete(int virfile_id)");
		//ɾ�������ļ���¼
		VirtualFile virtualFile = new VirtualFile();
		virtualFile.setVirfile_id(virfile_id);
		List<VirtualFile> list = storageDao.getVirtualFileById(virtualFile);
		virtualFile = list.get(0);
		System.out.println(virtualFile);
		storageDao.deleteVirtualFile(virtualFile);
		//ɾ�������ļ�
		Share share = new Share();
		share.setVirfile_id(virtualFile.getVirfile_id());
		System.out.println(share);
		storageDao.deleteShareByVirfileId(share);
		//��ӻ���վ��¼
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
		//��ѯ�û��ļ��б�,ѹ��ֵջ
		this.findALLStorageFile();
		return  "virdelete_success";
	}




	/**
	 * ��ȡ�û�����վ�б�����ֵջ
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
	 * �ָ���ɾ�����ļ�
	 */
	@Override
	public String recover(RecycleBin recycleBin) {
		System.out.println("==StorageServiceImpl.recover(RecycleBin recycleBin)");
		//ɾ������վ��¼
		List<RecycleBin> list = storageDao.getRecycleBinById(recycleBin);
		recycleBin = list.get(0);
		System.out.println(recycleBin);
		storageDao.deleteRecycleBin(recycleBin);
		//��������ļ���¼
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
	 * ���������ļ�����&����˽�ܷ������ص��߼�
	 */
	@Override
	public String shareDownload(Share share) {
		System.out.println("==StorageServiceImpl.shareDownload(Share share)");
		String status = null; 
		List<Share> sharelist = storageDao.getShareById(share);
		share = sharelist.get(0);
		System.out.println(share);
		//���ݷ������ͷ��ز�ͬ�ġ�status��
		String shareType  = share.getShare_password();
		if(shareType.equals("common_share")) {
			//��������
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
			//˽�ܷ������߼�
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("share_id", share.getShare_id());
			status = "previoussharedownload";
		}
		return status;
	}





	/**
	 * ˽�ܷ����ļ�����
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
		//���������Ƿ���ȷִ�в�ͬ���߼�
		if(correct_password.equals(share_password)) {
			//������ȷ
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
			//�������
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("share_id", share.getShare_id());
			valueStack.set("tips","�������");
			status = "sharepassword_error";
		}
		return status;
	}





	/**
	 * 
	 * �ļ�����
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
	 * ��ȡԤ���ļ���URL
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
	 * ��ƵԤ��
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
	 * ͼ��Ԥ��
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
	 * ��ƵԤ��
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





	//�ĵ�Ԥ��
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
