/**
 * RegistCustomerServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jsysSpring.sales.common.exception.SalesSystemException;
import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.mapper.CustomerMapper;
import jsysSpring.sales.mapper.CustomerNumberingMapper;

/**
 * 得意先登録Serviceの実装クラス
 * @author FLM
 * @version 1.0.0
 */
@Service
public class RegistCustomerServiceImpl implements RegistCustomerService {

	/** Mapper */
	@Autowired
	private CustomerMapper customerMapper;

	/** Mapper */
	@Autowired
	private CustomerNumberingMapper customerNumberingMapper;

	/**
	 * @see jsysSpring.sales.service.RegistCustomerService#registCustomer(jsysSpring.sales.entity.Customer)
	 */
	@Override
	@Transactional
	public Customer registCustomer(Customer customer) throws SalesSystemException{

		//Mapperのメソッドを呼び出し、 採番テーブルから得意先コードを取得
		Integer latestCutomerCode = customerNumberingMapper.findCustomerCode();

		//該当がなかった場合はSalesSystemExceptionをスロー
		if(latestCutomerCode==null) {
			throw new SalesSystemException();
		}

		//登録する得意先コードを作成
		String newCustomerCode = "KA" + String.format("%04d", latestCutomerCode + 1);
		//得意先情報に得意先コードを設定
		customer.setCustomerCode(newCustomerCode);

		//Mapperのメソッドを呼び出し、採番テーブルを更新
		int count = customerNumberingMapper.updateCustomerCode();

		//更新レコード数が0県の場合はSalesSystemExceptionをスロー
		if(count==0) {
			throw new SalesSystemException();
		}

		//Mapperのメソッドを呼び出し、得意先を登録
		customerMapper.insertCustomer(customer);

		//得意先情報を返却
		return customer;

	}

}
