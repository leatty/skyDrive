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
	 * ��������ļ���¼
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
	 * ��������ļ���¼
	 * ����sortType����
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
	 * ��������ļ���¼-�����ļ�������
	 * ��ֹ��ѯ�������û����ļ�����Ҫƥ���û�ID
	 */
	@Override
	public List<VirtualFile> getVirtualFileByExtName(Users users, List<String> typeList) {
		System.out.println("==StorageDaoImpl.getVirtualFileByExtName(Users users, List<String> typeList)");
		//��ѯ�����϶࣬��Ҫƴ���ַ���,Ŀǰ׼�������10��������������ѯ
		List<VirtualFile> virlist = new ArrayList<VirtualFile>();
		int length = typeList.size();
		System.out.println("length : " + length);
		for(int i = 0; i < length; i++) {
			System.out.println("==��ѯ���ļ�����ĵ�" + (i+1) + "���ļ���չ����=======================================");
			List<VirtualFile> list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile where user_id = ? and virfile_name like ?", users.getUser_id(), "%"+typeList.get(i)+"%");
			virlist.addAll(list);
			for(VirtualFile vf : virlist) {
				System.out.println(vf);
			}
			
			
		}
		return virlist;
	}




	/**
	 * ���������Ӽ�¼
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
	 *������վ����Ӽ�¼ 
	 */
	@Override
	public void saveRecycleBin(RecycleBin recycleBin) {
		System.out.println("==StorageDaoImpl.saveRecycleBin(RecycleBin recycleBin)");
		this.getHibernateTemplate().save(recycleBin);
	}

	
	
	/**
	 * ����ʵ�ļ�����Ӽ�¼
	 */
	@Override
	public int saveRealFile(RealFile realFile) {
		System.out.println("==StorageDaoImpl.saveRealFile(RealFile realFile)");
		this.getHibernateTemplate().save(realFile);
		System.out.println(realFile);
		return realFile.getFile_id();
	}

	
	/**
	 * �������ļ�����Ӽ�¼
	*/
	@Override
	public void saveVirtualFile(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.saveVirtualFile(VirtualFile virtualFile)");
		this.getHibernateTemplate().save(virtualFile);
		System.out.println(virtualFile);
	}

	
	/**
	 * �����ʵ�ļ���¼-�����ļ�Hash
	 */
	@Override
	public List<RealFile> getRealFileByHash(String fileMD5) {
		System.out.println("==StorageDaoImpl.getRealFileByHash(String fileMD5)");
		return (List<RealFile>) this.getHibernateTemplate().find("from RealFile where file_hash = ?", fileMD5);
	}

	
	/**
	 * ���������ļ���¼
	 */
	@Override
	public void updateVirtualFile(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.updateVirtualFile(VirtualFile virtualFile)");
		this.getHibernateTemplate().update(virtualFile);
	}

	
	/**
	 * ������ʵ�ļ���¼
	 */
	@Override
	public void updateRealFile(RealFile RealFile) {
		System.out.println("==StorageDaoImpl.updateRealFile(VirtualFile virtualFile)");
		this.getHibernateTemplate().update(RealFile);
		System.out.println(RealFile);
	}

	
	/**
	 * ɾ�������ļ���¼
	 */
	@Override
	public void deleteVirtualFile(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.deleteVirtualFile(int virfile_id)");
		this.getHibernateTemplate().delete(virtualFile);
		
	}

	
	/**
	 * ��������ļ���¼-���������ļ�ID
	 */
	@Override
	public List<VirtualFile> getVirtualFileById(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.getVirtualFileById(VirtualFile virtualFile)");
		List<VirtualFile> list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile where virfile_id = ?", virtualFile.getVirfile_id());
		return list;
	}

	/**
	 * ��û���վ��¼
	 */
	@Override
	public List<RecycleBin> getAllRecycleBin(Users users) {
		System.out.println("==StorageDaoImpl.getAllRecycleBin(Users users)");
		List<RecycleBin> list = (List<RecycleBin>) this.getHibernateTemplate().find("from RecycleBin where user_id = ?", users.getUser_id());
		return list;
	}
	
	/**
	 * �����ʵ�ļ���¼
	 */
	@Override
	public List<RealFile> getRealFileById(RealFile realFile) {
		System.out.println("==StorageDaoImpl.getRealFileById(RealFile realFile)");
		List<RealFile> list = (List<RealFile>) this.getHibernateTemplate().find("from RealFile where file_id = ?", realFile.getFile_id());
		return list;
	}
	
	
	/**
	 * ɾ������վ��¼
	 */
	@Override
	public void deleteRecycleBin(RecycleBin recycleBin) {
		System.out.println("==StorageDaoImpl.deleteRecycleBin(RecycleBin recycleBin)");
		this.getHibernateTemplate().delete(recycleBin);
	}

	
	
	
	/**
	 * ��û���վ��¼-���������ļ�ID
	 */
	@Override
	public List<RecycleBin> getRecycleBinById(RecycleBin recycleBin) {
		System.out.println("==StorageDaoImpl.getRecycleBinById(RecycleBin recycleBin)");
		List<RecycleBin> list = (List<RecycleBin>) this.getHibernateTemplate().find("from RecycleBin where garbage_id = ?", recycleBin.getGarbage_id());
		return list;
	}

	
	/**
	 * ɾ����ʵ�ļ���¼
	 */
	@Override
	public void deleteRealFile(RealFile realFile) {
		System.out.println("==StorageDaoImpl.deleteRealFile(RealFile realFile)");
		this.getHibernateTemplate().delete(realFile);
	}

	
	/**
	 * ��÷����¼
	 */
	@Override
	public List<Share> getALllShare(Users users) {
		System.out.println("==StorageDaoImpl.getALllShare(Users users)");
		List<Share> list = (List<Share>) this.getHibernateTemplate().find("from Share where user_id = ?", users.getUser_id());
		return list;
	}

	
	/**
	 * ��÷����¼-���ݷ���ID
	 */
	@Override
	public List<Share> getShareById(Share share) {
		System.out.println("==StorageDaoImpl.getShareById(Share share)");
		List<Share> list = (List<Share>) this.getHibernateTemplate().find("from Share where share_id = ?", share.getShare_id());
		return list;
	}

	
	/**
	 * ��������ļ���¼-���������ļ���
	 * Ϊ�˷�ֹ��ѯ�������û����ļ�������Ҫƥ���û�ID
	 */
	@Override
	public List<VirtualFile> getVirtualFileByName(VirtualFile virtualFile) {
		System.out.println("==StorageDaoImpl.getVirtualFileByName(VirtualFile virtualFile)");
		List<VirtualFile> list = (List<VirtualFile>) this.getHibernateTemplate().find("from VirtualFile where virfile_name like ? and user_id = ?", "%"+virtualFile.getVirfile_name()+"%",virtualFile.getUser_id());
		return list;
	}

	
	/**
	 * 
	 * ɾ������-���������ļ�ID
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
