/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Employee.java
 */
package jsysSpring.sales.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 従業員情報を管理するエンティティクラス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

	/**
	 * 従業員番号
	 */
	private String employeeNo;

	/**
	 * 従業員名
	 */
	private String employeeName;

	/**
	 * パスワード
	 */
	private String password;

}
