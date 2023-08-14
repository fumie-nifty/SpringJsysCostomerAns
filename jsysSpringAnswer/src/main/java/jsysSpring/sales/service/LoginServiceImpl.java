/**
 * LoginServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.Employee;
import jsysSpring.sales.mapper.EmployeeMapper;

/**
 * ログインServiceの実装クラス
 * @author FLM
 * @version 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

	/** Service */
	@Autowired
	private EmployeeMapper employeeMapper;

	/**
	 * @see jsysSpring.sales.service.LoginService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Employee login(String employeeNo, String password)
			throws SalesBusinessException {

		//Mapperのメソッド呼び出し従業員情報を取得
		Employee employee = employeeMapper.findEmployee(employeeNo);

		//認証処理
		if(employee==null || !employee.getPassword().equals(password)){
			throw new SalesBusinessException("入力した従業員番号またはパスワードが間違っています。");
		}

		//従業員情報を返却
		return employee;
	}

}
