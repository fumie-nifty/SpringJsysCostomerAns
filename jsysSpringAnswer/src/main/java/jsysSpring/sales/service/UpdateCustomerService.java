/**
 * UpdateCustomerService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.common.exception.SalesSystemException;
import jsysSpring.sales.entity.Customer;

/**
 * 得意先変更Service
 * @author FLM
 * @version 1.0.0
 */
public interface UpdateCustomerService {

	/**
	 * customerCodeをもとに得意先情報を検索し返却する
	 * @param customerCode 得意先コード
	 * @return 得意先情報
	 * @throws SalesBusinessException 検索結果が存在しない場合
	 */
	public Customer updateSearchCustomer(String customerCode) throws SalesBusinessException;

	/**
	 * customerをもとに得意先情報を変更する
	 * @param customer 得意先情報
	 * @throws SalesSystemException 変更結果が０件の場合
	 */
	public void updateCustomer(Customer customer) throws SalesSystemException;

}
