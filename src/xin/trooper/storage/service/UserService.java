package xin.trooper.storage.service;

import xin.trooper.storage.domain.Users;

public interface UserService {

	String regist(Users users);

	String login(Users users);

	String logoff();
}
