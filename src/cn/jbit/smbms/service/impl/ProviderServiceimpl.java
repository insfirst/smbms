package cn.jbit.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jbit.smbms.Util.Pager;
import cn.jbit.smbms.dao.provider.ProviderDao;
import cn.jbit.smbms.pojo.Provider;
import cn.jbit.smbms.service.ProviderService;
@Service("providerService")
public class ProviderServiceimpl implements ProviderService {
	@Resource
	private ProviderDao providerDao;
	
	public ProviderDao getProviderDao() {
		return providerDao;
	}

	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}

	public int add(Provider provider) {
		return providerDao.insert(provider);
	}

	@Override
	public int remove(int id) {
		return providerDao.delete(id);
	}

	@Override
	public int modify(Provider provider) {
		return providerDao.update(provider);
	}

	@Override
	public List<Provider> listAll() {
		return providerDao.select();
	}

	@Override
	public Pager<Provider> listByPage(String proCode, String proName, Integer pageIndex, Integer pageSize) {
		Pager<Provider>pager=new Pager<Provider>();
		if(null==pageIndex) {
			pageIndex=1;
		}
		pager.setPageIndex(pageIndex);
		pager.setTotalCount(providerDao.selectCount(proCode, proName));
		pager.setPageSize(pageSize);
		pager.setList(providerDao.selectByPage(proCode, proName, (pageIndex-1)*pageSize, pageSize));
		return pager;
	}

	@Override
	public Provider get(Integer id) {
		Provider p=new Provider();
		p.setId(id);
		return providerDao.select(p).get(0);
	}

}
