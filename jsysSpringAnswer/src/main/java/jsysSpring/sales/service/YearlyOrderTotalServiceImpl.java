/**
 * YearlyOrderTotalServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AggregationOrderTotalByCustomer;
import jsysSpring.sales.entity.OrderTotalByCustomer;
import jsysSpring.sales.mapper.OrderMapper;

/**
 * 年次得意先別受注集計Serviceの実装クラス
 * @author FLM
 * @version 1.0.0
 */
@Service
public class YearlyOrderTotalServiceImpl implements YearlyOrderTotalService {

	/** Mapper */
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * @see jsysSpring.sales.service.YearlyOrderTotalService#aggregateYearly(int)
	 */
	@Override
	public AggregationOrderTotalByCustomer aggregateYearly(int year)
			throws SalesBusinessException {

		//戻り値用のAggregationOrderTotalByCustomer（DTO）オブジェクトを生成
		AggregationOrderTotalByCustomer result = new AggregationOrderTotalByCustomer();

		//引数year(年)をもとに、開始日(startDate)と終了日(endDate)を生成
		String startDate = year + "/01/" + "01";
		String endDate = year + "/12/" + "31";

		//Mapperのメソッド呼び出し得意先別受注集計リストを取得
		ArrayList<OrderTotalByCustomer> orderTotalByCustomerList =
				orderMapper.createOrderTotalListByCustomer(startDate, endDate);

		//得意先別受注集計リストが空の場合、SalesBusinessExceptionをスローする
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
