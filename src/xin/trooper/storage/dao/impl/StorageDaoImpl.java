package xin.trooper.storage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.trooper.storage.dao.StorageDao;
import xin.trooper.storage.domain.Share;
import xin.trooper.storage.domain.RealFile;
import xin.trooper.storage.domain.RecycleBin;
import xin.trooper.storage.domain.Users;
import xin.trooper.storage.domain.VirtualFile;

public class StorageDaoImpl extends HibernateDaoSupport implements StorageDao {

//	@Override
//	public void saveUserFile(RealFile userFile) {
//		System.out.println("==StorageDaoImpl.saveUserFile(UserFile userFile)");
//		this.getHibernateTemplate().save(userFile);
//	}

	
	/**
	 * 获得虚拟文件记录
	 */
	@Override
	public List<VirtualFile> getALllVirtualFile(Users users) {
		System.out.println("==StorageDaoImpl.getALllVirtualFile(Users users)");
		System.out.println(users);
		List<VirtualFile> list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile where user_id = ?", users.getUser_id());
		for(VirtualFile userFile: list) {
			System.out.println(userFile);
		}
		return list;
	}
 
//	@Override
//	public void deleteUserFile(RealFile userFile) {
//		System.out.println("==StorageDaoImpl.deleteUserFile(UserFile userFile)");
//		this.getHibernateTemplate().delete(userFile);
//	}

//	@Override
//	public void userFileRename(RealFile userFile) {
//		System.out.println("==StorageDaoImpl.UserFileRename(UserFile userFile)");
//		RealFile updateUserFile = this.getHibernateTemplate().get(RealFile.class, userFile.getFile_id());
//		System.out.println(updateUserFile);
//		updateUserFile.setFile_name(userFile.getFile_name());
//		System.out.println(updateUserFile);
//	}


	@Override
	public void updateVirtualPath(RealFile userFile, VirtualFile virtualPath) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * 
	 * 获得虚拟文件记录
	 * 按照sortType排序
	 */
	@Override
	public List<VirtualFile> getAllVirtualFileBySortType(Users users, String sortType) {
		List<VirtualFile> list = new ArrayList<VirtualFile>();
		System.out.println("==StorageDaoImpl.getAllVirtualFileBySortType(Users users, String sortType)");
		if(sortType.equals("virfile_name")) {
			list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile v where v.user_id = ? order by v.virfile_name", users.getUser_id());
		}else if(sortType.equals("upload_date")){
			list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile v where v.user_id = ? order by v.upload_date", users.getUser_id());
		}else if(sortType.equals("virfile_size")) {
			list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile v where v.user_id = ? order by v.virfile_size", users.getUser_id());
		}
		for(VirtualFile vf : list) {
			System.out.println(vf);
		}
		return list;
	}

	/**
	 * 获得虚拟文件记录-根据文件的类型
	 * 防止查询到其他用户的文件，需要匹配用户ID
	 */
	@Override
	public List<VirtualFile> getVirtualFileByExtName(Users users, List<String> typeList) {
		System.out.println("==StorageDaoImpl.getVirtualFileByExtName(Users users, List<String> typeList)");
		//查询条件较多，需要拼接字符串,目前准备了最多10个参数的条件查询
		List<VirtualFile> virlist = new ArrayList<VirtualFile>();
		int length = typeList.size();
		System.out.println("length : " + length);
		for(int i = 0; i < length; i++) {
			System.out.println("==查询该文件大类的第" + (i+1) + "个文件拓展类型=======================================");
			List<VirtualFile> list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile where user_id = ? and virfile_name like ?", users.getUser_id(), "%"+typeList.get(i)+"%");
			virlist.addAll(list);
			for(VirtualFile vf : virlist) {
				System.out.println(vf);
			}
			
			
		}
		return virlist;
	}




	/**
	 * 给分享表添加记录
	 */
	@Override
	public void saveShare(Share share) {
		System.out.println("==StorageDaoImpl.saveShare(Share fileShare)");
		this.getHibernateTemplate().save(share);
	}

	@Override
	public List<Share> findAllFileShare(Users users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShare(Share share) {
		System.out.println("==StorageDaoImpl.deleteShare(Share share)");
		this.getHibernateTemplate().delete(share);		
	}


	/**
	 *给回收站表添加记录 
	 */
	@Override
	public void saveRecycleBin(RecycleBin recycleBin) {
		System.out.println("==StorageDaoImpl.saveRecycleBin(RecycleBin recycleBin)");
		this.getHibernateTemplate().save(recycleBin);
	}

	
	
	/**
	 * 给真实文件表添加记录
	 */
	@Override
	public int saveRealFile(RealFile realFile) {
		System.out.println("==StorageDaoImpl.saveRealFile(RealFile realFile)");
		this.getHibernateTemplate().save(realFile);
		System.out.println(realFile);
		return realFile.getFile_id();
	}

	
	/**
	 * 给虚拟文件表添加记录
	*/
	@Override
	public void saveVirtualFile(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.saveVirtualFile(VirtualFile virtualFile)");
		this.getHibernateTemplate().save(virtualFile);
		System.out.println(virtualFile);
	}

	
	/**
	 * 获得真实文件记录-根据文件Hash
	 */
	@Override
	public List<RealFile> getRealFileByHash(String fileMD5) {
		System.out.println("==StorageDaoImpl.getRealFileByHash(String fileMD5)");
		return (List<RealFile>) this.getHibernateTemplate().find("from RealFile where file_hash = ?", fileMD5);
	}

	
	/**
	 * 更新虚拟文件记录
	 */
	@Override
	public void updateVirtualFile(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.updateVirtualFile(VirtualFile virtualFile)");
		this.getHibernateTemplate().update(virtualFile);
	}

	
	/**
	 * 更新真实文件记录
	 */
	@Override
	public void updateRealFile(RealFile RealFile) {
		System.out.println("==StorageDaoImpl.updateRealFile(VirtualFile virtualFile)");
		this.getHibernateTemplate().update(RealFile);
		System.out.println(RealFile);
	}

	
	/**
	 * 删除虚拟文件记录
	 */
	@Override
	public void deleteVirtualFile(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.deleteVirtualFile(int virfile_id)");
		this.getHibernateTemplate().delete(virtualFile);
		
	}

	
	/**
	 * 获得虚拟文件记录-根据虚拟文件ID
	 */
	@Override
	public List<VirtualFile> getVirtualFileById(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.getVirtualFileById(VirtualFile virtualFile)");
		List<VirtualFile> list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile where virfile_id = ?", virtualFile.getVirfile_id());
		return list;
	}

	/**
	 * 获得回收站记录
	 */
	@Override
	public List<RecycleBin> getAllRecycleBin(Users users) {
		System.out.println("==StorageDaoImpl.getAllRecycleBin(Users users)");
		List<RecycleBin> list = (List<RecycleBin>) this.getHibernateTemplate().find("from RecycleBin where user_id = ?", users.getUser_id());
		return list;
	}
	
	/**
	 * 获得真实文件记录
	 */
	@Override
	public List<RealFile> getRealFileById(RealFile realFile) {
		System.out.println("==StorageDaoImpl.getRealFileById(RealFile realFile)");
		List<RealFile> list = (List<RealFile>) this.getHibernateTemplate().find("from RealFile where file_id = ?", realFile.getFile_id());
		return list;
	}
	
	
	/**
	 * 删除回收站记录
	 */
	@Override
	public void deleteRecycleBin(RecycleBin recycleBin) {
		System.out.println("==StorageDaoImpl.deleteRecycleBin(RecycleBin recycleBin)");
		this.getHibernateTemplate().delete(recycleBin);
	}

	
	
	
	/**
	 * 获得回收站记录-根据垃圾文件ID
	 */
	@Override
	public List<RecycleBin> getRecycleBinById(RecycleBin recycleBin) {
		System.out.println("==StorageDaoImpl.getRecycleBinById(RecycleBin recycleBin)");
		List<RecycleBin> list = (List<RecycleBin>) this.getHibernateTemplate().find("from RecycleBin where garbage_id = ?", recycleBin.getGarbage_id());
		return list;
	}

	
	/**
	 * 删除真实文件记录
	 */
	@Override
	public void deleteRealFile(RealFile realFile) {
		System.out.println("==StorageDaoImpl.deleteRealFile(RealFile realFile)");
		this.getHibernateTemplate().delete(realFile);
	}

	
	/**
	 * 获得分享记录
	 */
	@Override
	public List<Share> getALllShare(Users users) {
		System.out.println("==StorageDaoImpl.getALllShare(Users users)");
		List<Share> list = (List<Share>) this.getHibernateTemplate().find("from Share where user_id = ?", users.getUser_id());
		return list;
	}

	
	/**
	 * 获得分享记录-根据分享ID
	 */
	@Override
	public List<Share> getShareById(Share share) {
		System.out.println("==StorageDaoImpl.getShareById(Share share)");
		List<Share> list = (List<Share>) this.getHibernateTemplate().find("from Share where share_id = ?", share.getShare_id());
		return list;
	}

	
	/**
	 * 获得虚拟文件记录-根据虚拟文件名
	 * 为了防止查询到其他用户的文件，还需要匹配用户ID
	 */
	@Override
	public List<VirtualFile> getVirtualFileByName(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.getVirtualFileByName(VirtualFile virtualFile)");
		List<VirtualFile> list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile where virfile_name like ? and user_id = ?", "%"+virtualFile.getVirfile_name()+"%",virtualFile.getUser_id());
		return list;
	}

	
	/**
	 * 
	 * 删除分享-根据虚拟文件ID
	 */
	@Override
	public void deleteShareByVirfileId(Share share) {
		System.out.println("==StorageDaoImpl.deleteShareByVirfileId(Share share)");
		List<Share> list = (List<Share>) this.getHibernateTemplate().find("from Share where virfile_id = ?", share.getVirfile_id());
		for(Share s : list) {
			System.out.println(s);
		}
		this.getHibernateTemplate().deleteAll(list);
	}
}
