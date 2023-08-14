/**
 * LoginForm.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログイン情報入力フォーム
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm  implements Serializable {

	/** 従業員番号	 */
	@NotBlank
	@Size(min=6, max = 6)
	private String employeeNo;

	/** 従業員名 */
	@Size(max = 32)
	private String employeeName;

	/** パスワード	 */
	@NotBlank
	@Size(max = 6)
	private String password;

}
