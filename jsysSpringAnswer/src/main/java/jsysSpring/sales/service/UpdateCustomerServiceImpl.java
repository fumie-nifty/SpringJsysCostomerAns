/**
 * UpdateCustomerServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.common.exception.SalesSystemException;
import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.mapper.CustomerMapper;

/**
 * 得意先変更Serviceの実装クラス
 * @author FLM
 * @version 1.0.0
 */
@Service
public class UpdateCustomerServiceImpl implements UpdateCustomerService {

	/** Mapper */
	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * @see jsysSpring.sales.service.UpdateCustomerService#updateSearchCustomer(java.lang.String)
	 */
	@Override
	public Customer updateSearchCustomer(String customerCode)
			throws SalesBusinessException {

		//Mapperのメソッドを呼び出し、得意先情報を取得
		Customer customer = customerMapper.findCustomer(customerCode);

		//得意先情報が空の場合は、SalesBusinessExceptionをスロー
		if(customer==null) {
			throw new SalesBusinessException(customerCode + "に一致する得意先情報が存在しません。");
		}

		//得意先情報を返却
		return customer;
	}

	/**
	 * @see jsysSpring.sales.service.UpdateCustomerService#updateCustomer(jsysSpring.sales.entity.Customer)
	 */
	@Override
	public void updateCustomer(Customer customer) throws SalesSystemException {

		//Mapperのメソッドを呼び出し、得意先情報を更新
		int count = customerMapper.updateCustomer(customer);

		//レコード件数が0の場合は、SalesSystemExceptionをスロー
		if(count==0) {
			throw new SalesSystemException();
		}

	}

}
