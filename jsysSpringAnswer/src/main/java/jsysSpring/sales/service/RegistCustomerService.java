/**
 * RegistCustomerService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import jsysSpring.sales.common.exception.SalesSystemException;
import jsysSpring.sales.entity.Customer;

/**
 * 得意先登録Service
 * @author FLM
 * @version 1.0.0
 */
public interface RegistCustomerService {

	/**
	 * customerをもとに得意先情報を登録し、採番した得意先コードをセットした得意先情報を返却する
	 * @param customer 得意先情報（得意先コードnull）
	 * @return 得意先情報
	 * @throws SalesSystemException 採番テーブルが０件の場合
	 */
	public Customer registCustomer(Customer customer) throws SalesSystemException;

}
