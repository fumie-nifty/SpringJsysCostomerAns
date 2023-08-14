/**
 * OrderTotalByItemService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AggregationOrderTotalByItem;

/**
 * 商品別受注集計Service
 * @author FLM
 * @version 1.0.0
 */
public interface OrderTotalByItemService {
	/**
	 * 得意先コードに一致した得意先の商品別受注集計情報オブジェクトを返却する
	 * @param customerCode 得意先コード
	 * @return 商品別受注集計情報オブジェクト
	 * @throws SalesBusinessException 検索結果が存在しない場合
	 */
	public AggregationOrderTotalByItem aggregateByItem(String customerCode) throws SalesBusinessException;

}
