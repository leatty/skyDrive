package xin.trooper.storage.dao;

import java.util.List;

import xin.trooper.storage.domain.Users;
import xin.trooper.storage.domain.VirtualFile;

public interface UserDao {
	
	void saveUser(Users users);

	List findAll();

	
	List<Users> findByUserAccountAndUserPassword(Users users);

	List<VirtualFile> getALllVirtualFile(Users users);
	
}
