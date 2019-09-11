package cn.jbit.smbms.dao.address;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jbit.smbms.pojo.Address;
@Repository
public interface AddressDao {
	int insert(Address address);
	int delete(int id);
	int update(Address address);
	List<Address> select();
}
