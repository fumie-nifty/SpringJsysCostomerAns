/**
 * SearchCustomerListServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.mapper.CustomerMapper;

/**
 * 得意先一覧Serviceの実装クラス
 * @author FLM
 * @version 1.0.0
 */
@Service
public class SearchCustomerListServiceImpl
		implements SearchCustomerListService {

	/** Mapper */
	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * @see jsysSpring.sales.service.SearchCustomerListService#searchCustomerList()
	 */
	@Override
	public ArrayList<Customer> searchCustomerList()
			throws SalesBusinessException {

		//Mapperのメソッドを呼び出し、得意先情報リストを取得
		ArrayList<Customer> searchCustomerList = customerMapper.findAllCustomer();

		//得意先情報リストが空の場合は、SalesBusinessExceptionをスロー
		if(searchCustomerList.isEmpty()) {
			throw new SalesBusinessException("該当する得意先情報が存在しません。");
		}

		//得意先情報リストを返却
		return searchCustomerList;
	}

}
