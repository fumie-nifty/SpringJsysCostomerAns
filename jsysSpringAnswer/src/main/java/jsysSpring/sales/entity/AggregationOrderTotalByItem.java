/**
 * AggregationOrderTotalByItem.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.entity;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品別受注集計情報を管理するDTOクラス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregationOrderTotalByItem implements Serializable {

	/**
	 * 総計
	 */
	private int aggregateTotal;

	/**
	 * 得意先名
	 */
	private String customerName;

	/**
	 * 商品別受注集計情報リスト
	 */
	private ArrayList<OrderTotalByItem> orderTotalByItemList;

}
