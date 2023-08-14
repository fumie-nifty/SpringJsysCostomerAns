/**
 * LoginService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.Employee;

/**
 * ログインService
 * @author FLM
 * @version 1.0.0
 */
public interface LoginService {

	/**
	 * employeeNoをもとに従業員情報を検索し、パスワードが等しい
	 * 従業員情報を返却する
	 * @param employeeId 従業員番号
	 * @param password パスワード
	 * @return 従業員情報
	 * @throws SalesBusinessException パスワードが異なる、もしくは検索結果が存在しない場合
	 */
	public Employee login(String employeeNo,	String password) throws SalesBusinessException;

}
