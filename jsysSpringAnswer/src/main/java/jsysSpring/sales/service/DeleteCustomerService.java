/**
 * DeleteCustomerService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.common.exception.SalesSystemException;
import jsysSpring.sales.entity.Customer;

/**
 * 得意先削除Service
 * @author FLM
 * @version 1.0.0
 */
public interface DeleteCustomerService {

	/**
	 * customerCodeをもとに得意先情報を検索し返却する
	 * @param customerCode 得意先コード
	 * @return 得意先情報
	 * @throws SalesBusinessException 検索結果が存在しない場合
	 */
	public Customer deleteSearchCustomer(String customerCode) throws SalesBusinessException;

	/**
	 * customerCodeをもとに得意先情報を削除する
	 * @param customerCode 得意先コード
	 * @throws SalesSystemException 削除結果が０件の場合
	 */
	public void deleteCustomer(String customerCode) throws SalesSystemException;

}
