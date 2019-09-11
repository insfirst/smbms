package cn.jbit.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.jbit.smbms.pojo.User;

@Repository
public interface UserDao {
	int insert(User user);

	int delete(int id);

	int update(User user);

	List<User> selectByName(@Param("userName") String userName);

	List<User> select(User user);

	List<User> select();

	List<User> selectByPage(
			@Param("userName")String userName,
			@Param("userRole")Integer userRole,
			@Param("from")Integer from,
			@Param("pageSize")Integer pageSize);
	int selectCount(@Param("userName")String userName,@Param("userRole")Integer userRole);
}
	