/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * CustomerMapper.java
 */
package jsysSpring.sales.mapper;

import jsysSpring.sales.entity.Customer;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

/**
 * CUSTOMERテーブルを利用するMapperインターフェイス
 * @author FLM
 * @version 1.0 2022/03/20
 */

@Mapper
public interface CustomerMapper {

	/**
	 * 得意先を1件検索する。
	 * @param custCode 得意先コード
	 * @return 得意先
	 */
	public Customer findCustomer(String customerCode);

	/**
	 * 引数で指定した得意先を追加する。
	 * @param customer 得意先
	 * @return 追加結果
	 */
	public boolean insertCustomer(Customer customer);

	/**
	 * 引数で指定した得意先の削除フラグを更新する。
	 * @param custCode 得意先コード
	 * @return 更新結果
	 */
	public int updateDeleteFlag(String customerCode);

	/**
	 * 引数で指定した得意先を更新する。
	 * @param customer 得意先
	 * @return 更新結果
	 */
	public int updateCustomer(Customer customer);

	/**
	 * 得意先を全件検索する。
	 * @return 得意先リスト
	 */
	public ArrayList<Customer> findAllCustomer();

	/**
	 * 削除済みフラグを無視して、得意先を1件検索する。
	 * @param custCode 得意先コード
	 * @return 得意先
	 */
	public Customer findCustomerIgnoreDeleteFlag(String customerCode);

}
