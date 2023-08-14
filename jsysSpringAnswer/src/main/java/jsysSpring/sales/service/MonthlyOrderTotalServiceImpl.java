/**
 * MonthlyOrderTotalServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AggregationOrderTotalByCustomer;
import jsysSpring.sales.entity.OrderTotalByCustomer;
import jsysSpring.sales.mapper.OrderMapper;

/**
 * 月別得意先別受注集計Serviceの実装クラス
 * @author FLM
 * @version 1.0.0
 */
@Service
public class MonthlyOrderTotalServiceImpl implements MonthlyOrderTotalService {

	/** Mapper */
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * @see jsysSpring.sales.service.MonthlyOrderTotalService#aggregateMonthly(int, int)
	 */
	@Override
	public AggregationOrderTotalByCustomer aggregateMonthly(int year, int month)
			throws SalesBusinessException {

		//戻り値用のAggregationOrderTotalByCustomer（DTO）オブジェクトを生成
		AggregationOrderTotalByCustomer result = new AggregationOrderTotalByCustomer();

		//引数year(年)とmonth(月)をもとに、開始日(startDate)と終了日(endDate)を生成
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M/d");
		LocalDate startLocalDate = LocalDate.of(year, month,1);
		String startDate = startLocalDate.format(dtf);
		String endDate = LocalDate.of(year, month,startLocalDate.lengthOfMonth()).format(dtf);

		//Mapperのメソッド呼び出し得意先別受注集計リストを取得
		ArrayList<OrderTotalByCustomer> orderTotalByCustomerList =
				orderMapper.createOrderTotalListByCustomer(startDate, endDate);

		//得意先別受注集計リストが空の場合、SalesBusinessExceptionをスロー
		if(orderTotalByCustomerList.isEmpty()) {
			throw new SalesBusinessException("該当する集計情報が存在しません。");
		}

		//戻り値用のAggregationOrderTotalByCustomer（DTO）オブジェクトに得意先別受注集計リストを設定
		result.setOrderTotalByCustomerList(orderTotalByCustomerList);

		//総計の計算
		int aggregateTotal  = 0;
		for (OrderTotalByCustomer order : orderTotalByCustomerList) {
			aggregateTotal += order.getTotalPrice();
		}

		//戻り値用のAggregationOrderTotalByCustomer（DTO）オブジェクトに総計を設定
		result.setAggregateTotal(aggregateTotal);

		return result;
	}

}
