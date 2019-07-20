package xin.trooper.storage.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import xin.trooper.storage.domain.Users;
import xin.trooper.storage.service.StorageService;
import xin.trooper.storage.service.UserService;
import xin.trooper.storage.utils.StorageUtils;

public class UserAction extends ActionSupport implements ModelDriven<Users>{
	/**
	 * 
	 * ģ���������ݷ�װ��ʽ
	 */
	private Users users = new Users();
	@Override
	public Users getModel() {
		return users;
	}
	
	/**
	 * 
	 * ע��UserService��UserAction
	 */
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	
	/**
	 * 
	 * �û�ע��
	 */
	public String regist() {
		System.out.println("==UserAction.regist()");
		String MD5password = StorageUtils.getStringMD5(users.getUser_password());
		users.setUser_password(MD5password);
		String status = userService.regist(users);
		return status;
	}


	
	/**
	 * 
	 * �û���¼
	 */
	public String login() {
		System.out.println("==UserAction.login()");
		String MD5password = StorageUtils.getStringMD5(users.getUser_password());
		users.setUser_password(MD5password);
		System.out.println(users);
		String status = userService.login(users);
		
		return status;
	}
	

	
	/**
	 * 
	 * �û�ע��
	 */
	public String logoff() {
		System.out.println("==UserAction.logoff()");
		
		return userService.logoff();
	}
	
	
}
