/**
 * DeleteCustomerServiceImpl.java
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
 * 得意先削除Serviceの実装クラス
 * @author FLM
 * @version 1.0.0
 */
@Service
public class DeleteCustomerServiceImpl implements DeleteCustomerService {

	/** Mapper */
	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * @see jsysSpring.sales.service.DeleteCustomerService#deleteSearchCustomer(java.lang.String)
	 */
	@Override
	public Customer deleteSearchCustomer(String customerCode)
			throws SalesBusinessException {

		//Mapperのメソッド呼び出し得意先情報を取得
		Customer customer = customerMapper.findCustomer(customerCode);

		//customerがnullの場合は、SalesBusinessExceptionをスロー
		if(customer==null) {
			throw new SalesBusinessException(customerCode + "に一致する得意先情報が存在しません。");
		}

		//得意先情報を返却
		return customer;
	}

	/**
	 * @see jsysSpring.sales.service.DeleteCustomerService#deleteCustomer(java.lang.String)
	 */
	@Override
	public void deleteCustomer(String customerCode)
			throws SalesSystemException {

		//Mapperのメソッド呼び出し得意先情報を削除し、削除したレコード数を取得
		int count = customerMapper.updateDeleteFlag(customerCode);

		//レコード件数が0の場合は、SalesSystemExceptionをスロー
		if(count==0) {
			throw new SalesSystemException();
		}

	}

}
