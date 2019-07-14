package xin.trooper.storage.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.trooper.storage.dao.UserDao;
import xin.trooper.storage.domain.Users;
import xin.trooper.storage.domain.VirtualFile;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void saveUser(Users users) {
		System.out.println("==UserDaoImpl.saveUser(Users users)");
		System.out.println(users);

		this.getHibernateTemplate().save(users);
	}

	@Override
	public List findAll() {
		System.out.println("==UserDaoImpl.findAll(Users users)");
		List<Users> list = (List<Users>) this.getHibernateTemplate().find("from Users");
		if(list.isEmpty()) {
			Users user = new Users();
			user.setUser_account("this.is.first.user");
			list.add(user);
		}
		return list;
	}
	


	@Override
	public List<Users> findByUserAccountAndUserPassword(Users users) {
		System.out.println("==UserDaoImpl.findByUserAccountAndUserPassword(Users users)");
		List<Users> list = (List<Users>) this.getHibernateTemplate().find("from Users where user_account = ? and user_password = ?",users.getUser_account(),users.getUser_password());
		return list;
	}


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
	

}
