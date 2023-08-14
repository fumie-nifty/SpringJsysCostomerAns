/**
 * AuthEmployee.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 認証済み従業員情報を管理するエンティティクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@SessionScope
public class AuthEmployee implements Serializable{

	/**
	 * 従業員番号
	 */
	private String employeeNo;

	/**
	 * 従業員名
	 */
	private String employeeName;

}
