/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * EmployeeMapper.java
 */
package jsysSpring.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import jsysSpring.sales.entity.Employee;

/**
 * EMPLOYEEテーブルを利用するMapperインターフェイス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@Mapper
public interface EmployeeMapper {

	/**
	 * 引数で指定した従業員番号と一致する従業員を検索する。
	 * @param employeeNo 従業員番号
	 * @param password パスワード
	 * @return 従業員
	 */
	public Employee findEmployee(String employeeNo);

}
