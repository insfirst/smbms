package cn.jbit.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jbit.smbms.Util.Pager;
import cn.jbit.smbms.dao.bill.BillDao;
import cn.jbit.smbms.pojo.Bill;
import cn.jbit.smbms.service.BillService;
@Service("billService")
public class BillServiceImpl implements BillService {
	@Resource
	private BillDao billDao;
	
	public BillDao getBillDao() {
		return billDao;
	}

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}
	
	@Override
	public int insert(Bill bill) {
		return billDao.insert(bill);
	}

	@Override
	public int delete(int id) {
		return billDao.delete(id);
	}

	@Override
	public int update(Bill bill) {
		return billDao.update(bill);
	}

	@Override
	public List<Bill> select() {
		return billDao.select();
	}

	@Override
	public List<Bill> selectByPayment(Bill bill) {
		return billDao.selectByPayment(bill);
	}

	@Override
	public Pager<Bill> listByPage(String productName, Integer providerId, Integer pageIndex, Integer pageSize,Integer isPayment) {
		if(null==pageIndex) {
			pageIndex=1;
		}
		if(null!=providerId && providerId==0) {
			providerId=null;
		}
		if(null!=isPayment && isPayment==0) {
			isPayment=null;
		}
		Pager<Bill> pager=new Pager<>();
		pager.setPageSize(pageSize);
		pager.setPageIndex(pageIndex);
		pager.setTotalCount(billDao.selectCount(productName, providerId,isPayment));
		pager.setList(billDao.listByPage(productName, providerId, (pageIndex-1)*pageSize, pageSize,isPayment));
		return pager;
	}

	@Override
	public Bill select(Integer id) {
		Bill bill=new Bill();
		bill.setId(id);
		return billDao.select(bill).get(0);
	}

}
