/**
 * UpdateCustomerForm.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 得意先更新情報入力フォーム
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerForm implements Serializable {

	/** 得意先コード	 */
	@NotBlank
	@Size(min=6, max = 6)
	private String customerCode;

	/** 得意先名 */
	@NotBlank
	@Size(max = 32)
	private String customerName;

	/** 電話番号 */
	@NotBlank
	@Size(max = 13)
	private String customerTelno;

	/** 郵便番号 */
	@NotBlank
	@Size(max = 8)
	private String customerPostalcode;

	/** 住所 */
	@NotBlank
	@Size(max = 40)
	private String customerAddress;

	/** 割引率 */
	@Min(0)
	@Max(99)
	@NotNull
	private Integer discountRate;
}
