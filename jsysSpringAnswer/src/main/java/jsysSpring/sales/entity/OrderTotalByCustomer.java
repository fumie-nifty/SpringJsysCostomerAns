/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderTotalByCustomer.java
 */
package jsysSpring.sales.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 得意先別受注集計情報を管理するエンティティクラス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotalByCustomer implements Serializable {

	/**
	 * 得意先コード
	 */
	private String customerCode;

	/**
	 * 得意先名
	 */
	private String customerName;

	/**
	 * 得意先別合計金額
	 */
	private int totalPrice;

}
