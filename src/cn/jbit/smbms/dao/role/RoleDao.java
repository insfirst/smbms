package cn.jbit.smbms.dao.role;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jbit.smbms.pojo.Role;

@Repository
public interface RoleDao {
	List<Role> select();
	List<Role> selectByName(String roleName);
}
