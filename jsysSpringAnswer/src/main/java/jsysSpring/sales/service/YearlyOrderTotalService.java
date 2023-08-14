/**
 * YearlyOrderTotalService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AggregationOrderTotalByCustomer;

/**
 * 年次得意先別受注集計Service
 * @author FLM
 * @version 1.0.0
 */
public interface YearlyOrderTotalService {
	/**
	 * 年に一致する得意先別受注集計情報オブジェクトを返却する
	 * @param year 年
	 * @return 得意先別受注集計情報オブジェクト
	 * @throws SalesBusinessException 検索結果が存在しない場合
	 */
	public AggregationOrderTotalByCustomer aggregateYearly(int year) throws SalesBusinessException;

}
