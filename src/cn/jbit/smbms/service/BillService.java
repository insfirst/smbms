package cn.jbit.smbms.service;

import java.util.List;

import cn.jbit.smbms.Util.Pager;
import cn.jbit.smbms.pojo.Bill;

public interface BillService {
	int insert(Bill bill);
	int delete(int id);
	int update(Bill bill);
	List<Bill> select();
	Bill select(Integer id);
	List<Bill> selectByPayment(Bill bill);
	Pager<Bill> listByPage(String productName,Integer providerId,Integer pageIndex,Integer pageSize,Integer isPayment);
	
}
