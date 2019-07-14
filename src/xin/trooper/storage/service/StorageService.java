package xin.trooper.storage.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import xin.trooper.storage.domain.Share;
import xin.trooper.storage.domain.RealFile;
import xin.trooper.storage.domain.RecycleBin;
import xin.trooper.storage.domain.Users;
import xin.trooper.storage.domain.VirtualFile;

public interface StorageService {

	 String upload(String uploadFileName,File upload,String  uploadContextType) throws IOException;

	String findALLStorageFile();

	String delete(RecycleBin recycleBin);

	String rename(VirtualFile virtualFile);

	List<String> download(int fileName);

	String fileCopy(RealFile userFile, VirtualFile virtualPath);

	String sort(String sort_id);

	String classify(List<String> list);

	String findAllRecycleBin();

	String commonShare(Share share);

	String privateShare(Share share);

	String findAllShare();

	String shareDelete(Share share);

	String virdelete(int user_id);

	String recover(RecycleBin recycleBin);

	String shareDownload(Share share);

	String privateShareDownload(int share_id, String share_password);

	String search(VirtualFile virtualFile);

	String videoPreview(VirtualFile virtualFile);

	String imagePreview(VirtualFile virtualFile) throws IOException;

	String musicPreview(VirtualFile virtualFile);

	String documentPreview(VirtualFile virtualFile);




		
	

}
