package cn.jbit.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.jbit.smbms.pojo.Bill;


@Repository
public interface BillDao {
	List<Bill> select();
	List<Bill> selectByPayment(Bill bill);
	List<Bill> select(Bill bill);
	int insert(Bill bill);
	int delete(int id);
	int update(Bill bill);
	int selectCount(
			@Param("productName")String productName,
			@Param("providerId")Integer providerId,
			@Param("isPayment")Integer isPayment);
	List<Bill>listByPage(
			@Param("productName")String productName,
			@Param("providerId")Integer providerId,
			@Param("from")Integer from,
			@Param("pageSize")Integer pageSize,
			@Param("isPayment")Integer isPayment);
}
