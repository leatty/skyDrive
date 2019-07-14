package xin.trooper.storage.dao;

import java.util.List;

import xin.trooper.storage.domain.Share;
import xin.trooper.storage.domain.RealFile;
import xin.trooper.storage.domain.RecycleBin;
import xin.trooper.storage.domain.Users;
import xin.trooper.storage.domain.VirtualFile;

public interface StorageDao {



//	void saveUserFile(RealFile userFile);

	List<VirtualFile> getALllVirtualFile(Users users);

//	void deleteUserFile(RealFile userFile);

//	void userFileRename(RealFile userFile);

	void updateVirtualPath(RealFile userFile, VirtualFile virtualPath);

	List<VirtualFile> getAllVirtualFileBySortType(Users users, String sort_id);

	List<VirtualFile> getVirtualFileByExtName(Users users, List<String> typeList);

	List<RecycleBin> getAllRecycleBin(Users users);

	void deleteRecycleBin(RecycleBin recycleBin);

	void saveShare(Share share);

	List<Share> findAllFileShare(Users users);

	void deleteShare(Share fileShare);

	void saveRecycleBin(RecycleBin recycleBin);

	int saveRealFile(RealFile realFile);

	void saveVirtualFile(VirtualFile virtualFile);

	List<RealFile> getRealFileByHash(String fileMD5);

	void updateVirtualFile(VirtualFile virtualFile);

	void updateRealFile(RealFile real);

	void deleteVirtualFile(VirtualFile virtualFile);

	List<VirtualFile> getVirtualFileById(VirtualFile virtualFile);

	List<RealFile> getRealFileById(RealFile realFile);

	List<RecycleBin> getRecycleBinById(RecycleBin recycleBin);

	void deleteRealFile(RealFile realFile);

	List<Share> getALllShare(Users users);

	List<Share> getShareById(Share share);

	List<VirtualFile> getVirtualFileByName(VirtualFile virtualFile);

	void deleteShareByVirfileId(Share share);

}
