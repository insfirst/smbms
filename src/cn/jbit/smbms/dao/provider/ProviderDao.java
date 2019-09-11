package cn.jbit.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.jbit.smbms.pojo.Provider;
@Repository
public interface ProviderDao {
	int insert(Provider provider);
	int delete(int id);
	int update(Provider provider);
	List<Provider> select();
	int selectCount(@Param("proCode")String proCode,@Param("proName")String proName);
	List<Provider>selectByPage(
			@Param("proCode")String proCode,
			@Param("proName")String proName,
			@Param("from")Integer from,
			@Param("pageSize")Integer pageSize);
	List<Provider> select(Provider provider);
}
