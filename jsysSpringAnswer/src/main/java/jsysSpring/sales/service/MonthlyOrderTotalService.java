/**
 * MonthlyOrderTotalService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AggregationOrderTotalByCustomer;

/**
 * 月別得意先別受注集計Service
 * @author FLM
 * @version 1.0.0
 */
public interface MonthlyOrderTotalService {
	/**
	 * 年月に一致する得意先別受注集計情報オブジェクトを返却する
	 * @param year 年
	 * @param month 月
	 * @return 得意先別受注集計情報オブジェクト
	 * @throws SalesBusinessException 検索結果が存在しない場合
	 */
	public AggregationOrderTotalByCustomer aggregateMonthly(int year,int month) throws SalesBusinessException;

}
