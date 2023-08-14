/**
 * AggregationOrderTotalByCustomer.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.entity;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 得意先別受注集計情報を管理するDTOクラス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregationOrderTotalByCustomer implements Serializable {

	/**
	 * 総計
	 */
	private int aggregateTotal;

	/**
	 * 得意先別受注集計情報リスト
	 */
	private ArrayList<OrderTotalByCustomer> orderTotalByCustomerList ;


}
