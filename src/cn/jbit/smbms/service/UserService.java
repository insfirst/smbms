package cn.jbit.smbms.service;

import java.util.List;

import cn.jbit.smbms.Util.Pager;
import cn.jbit.smbms.pojo.User;

public interface UserService {
	User get(Integer id);
	List<User>list();
	List<User>list(User user);
	User login(String userCode, String userPassword);
	Pager<User>listByPage(String userName,Integer userRole,Integer pageIndex,Integer pageSize);
	boolean add(User user);
	boolean remove(Integer id);
	User listByUserCode(String userCode);
}
