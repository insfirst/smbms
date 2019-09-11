package cn.jbit.smbms.service;

import java.util.List;

import cn.jbit.smbms.pojo.Address;

public interface AddressService {
	int add(Address address);
	int remove(int id);
	int modify(Address address);
	List<Address> listAll();
}
