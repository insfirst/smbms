package cn.jbit.smbms.service;

import java.util.List;

import cn.jbit.smbms.Util.Pager;
import cn.jbit.smbms.pojo.Provider;

public interface ProviderService {
	int add(Provider provider);
	int remove(int id);
	int modify(Provider provider);
	List<Provider> listAll();
	Provider get(Integer id);
	Pager<Provider> listByPage(String proCode,String proName,Integer pageIndex,Integer pageSize);
}
