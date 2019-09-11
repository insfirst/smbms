package cn.jbit.smbms.service;

import java.util.List;

import cn.jbit.smbms.pojo.Role;

public interface RoleService {
	List<Role>list();
	List<Role>listByName(String roleName);
}
