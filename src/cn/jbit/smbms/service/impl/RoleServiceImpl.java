package cn.jbit.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jbit.smbms.dao.role.RoleDao;
import cn.jbit.smbms.pojo.Role;
import cn.jbit.smbms.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public List<Role> list() {
		return roleDao.select();
	}

	@Override
	public List<Role> listByName(String roleName) {
		return roleDao.selectByName(roleName);
	}

}
