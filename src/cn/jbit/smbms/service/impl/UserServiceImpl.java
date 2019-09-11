package cn.jbit.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jbit.smbms.Util.Pager;
import cn.jbit.smbms.dao.user.UserDao;
import cn.jbit.smbms.pojo.User;
import cn.jbit.smbms.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;


	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> list(User user) {
		return userDao.select(user);
	}

	@Override
	public User login(String userCode, String userPassword) {
		User user=new User();
		user.setUserCode(userCode);
		user.setUserPassword(userPassword);
		return userDao.select(user).get(0);
	}

	@Override
	public List<User> list() {
		return userDao.select();
	}

	@Override
	public Pager<User> listByPage(String userName, Integer userRole, Integer pageIndex, Integer pageSize) {
		Pager<User>pager=new Pager<>();
		if(null==pageIndex) {
			pageIndex=1;
		}
		if(null!=userRole && 0==userRole) {
			userRole=null;
		}
		pager.setPageIndex(pageIndex);
		pager.setPageSize(pageSize);
		pager.setTotalCount(userDao.selectCount(userName,userRole));
		pager.setList(userDao.selectByPage(userName, userRole, (pageIndex-1)*pageSize, pageSize));
		return pager;
	}

	@Override
	public User get(Integer id) {
		User user=new User();
		if(null!=id) {
//		user.setUserRole(id);
			user.setId(id);
		}
		return userDao.select(user).get(0);
	}

	@Override
	public boolean add(User user) {
		return userDao.insert(user)>0;
	}

	@Override
	public boolean remove(Integer id) {
		return userDao.delete(id)>0;
	}

	@Override
	public User listByUserCode(String userCode) {
			User user=new User();
			user.setUserCode(userCode);
			return userDao.select(user).size()>0?userDao.select(user).get(0):null;
	}


}
