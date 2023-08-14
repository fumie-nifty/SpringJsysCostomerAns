/**
 * SearchCustomerListService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import java.util.ArrayList;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.Customer;

/**
 * 得意先一覧Service
 * @author FLM
 * @version 1.0.0
 */
public interface SearchCustomerListService {

	/**
	 * 得意先情報を検索し得意先情報リストを返却する
	 * @return 得意先情報リスト
	 * @throws SalesBusinessException 検索結果が存在しない場合
	 */
	public ArrayList<Customer> searchCustomerList() throws SalesBusinessException;

}
