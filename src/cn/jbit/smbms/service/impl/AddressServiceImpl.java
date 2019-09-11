package cn.jbit.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jbit.smbms.dao.address.AddressDao;
import cn.jbit.smbms.pojo.Address;
import cn.jbit.smbms.service.AddressService;
@Service("addressService")
public class AddressServiceImpl implements AddressService{
	@Resource
	private AddressDao addressDao;
	
	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	@Override
	public int add(Address address) {
		return addressDao.insert(address);
	}

	@Override
	public int remove(int id) {
		return addressDao.delete(id);
	}

	@Override
	public int modify(Address address) {
		return addressDao.update(address);
	}

	@Override
	public List<Address> listAll() {
		return addressDao.select();
	}

}
