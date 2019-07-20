package xin.trooper.storage.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import xin.trooper.storage.dao.StorageDao;
import xin.trooper.storage.dao.UserDao;
import xin.trooper.storage.domain.Users;
import xin.trooper.storage.domain.VirtualFile;
import xin.trooper.storage.service.UserService;
import xin.trooper.storage.utils.StorageUtils;

@Transactional
public class UserServiceImpl implements UserService {

	
	 
	//注入UserDao到UserService
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	//用户注册
	@Override
	public String regist(Users users) {
		System.out.println("==UserServiceImpl.regist(Users users)");
		System.out.println(users);
		String status = null;
		
		
		List<Users> list = userDao.findAll();
		for(Users user : list) {
			if(users.getUser_account().equals(user.getUser_account())) {
				status = "regist_error";
			}else{
				userDao.saveUser(users);
			 status = "regist_success";
			}
		}
		return status;
	
	}


	//用户登录
	@Override
	public String login(Users users) {
		System.out.println("==UserServiceImpl.login(Users users)");
		String status = null;
		
		List<Users> list = userDao.findByUserAccountAndUserPassword(users);
		
		if(list.isEmpty()) {
			System.out.println("账户名或密码不存在");
			status = "login_error";
		}else {
			System.out.println("用户存在");
			Users existUser = list.get(0);
			System.out.println(existUser);
			System.out.println("before login session : " + ActionContext.getContext().getSession());
			ActionContext.getContext().getSession().put("loginedUser", existUser);
			ActionContext.getContext().getSession().put("username", existUser.getUser_name());
			ActionContext.getContext().getSession().put("userStatus", "logined");
			System.out.println("after login session : " + ActionContext.getContext().getSession());
			List<VirtualFile> userFileList = userDao.getALllVirtualFile(existUser);
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("userFileList", userFileList);
			//ServletActionContext.getRequest().getSession().setAttribute("loginedUser", existUser);
			status = "login_success";
		}

		return status;
		
		
		
		
	}


	@Override
	public String logoff() {
		System.out.println("==UserServiceImpl.logoff()");
		
		System.out.println("before logoff session :" + ActionContext.getContext().getSession());
		ActionContext.getContext().getSession().put("userStatus", "logoffed");
		ActionContext.getContext().getSession().put("username", "请登录");
		//session.invalidate();
		System.out.println("after logoff session : " + ActionContext.getContext().getSession());
		return "logoff_success";
	}

}
