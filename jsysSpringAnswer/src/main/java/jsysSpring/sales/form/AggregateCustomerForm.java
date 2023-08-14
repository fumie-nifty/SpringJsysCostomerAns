/**
 * AggregateCustomerForm.java
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
 * 商品別受注集計情報入力フォーム
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregateCustomerForm implements Serializable {

	/** 得意先コード	 */
	@NotBlank
	@Size(min=6, max = 6)
	private String customerCode;

}
