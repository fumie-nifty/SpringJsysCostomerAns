/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderTotalByItem.java
 */
package jsysSpring.sales.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品別受注集計を管理するエンティティクラス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotalByItem implements Serializable {

	/**
	 * 商品コード
	 */
	private String itemCode;

	/**
	 * 商品名
	 */
	private String itemName;

	/**
	 * 合計数量
	 */
	private int totalAmount;

	/**
	 * 単価
	 */
	private int price;

	/**
	 * 商品別合計金額
	 */
	private int totalPrice;

}
