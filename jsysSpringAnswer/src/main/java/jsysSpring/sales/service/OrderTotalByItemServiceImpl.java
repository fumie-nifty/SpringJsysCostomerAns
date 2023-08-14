/**
 * OrderTotalByItemServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AggregationOrderTotalByItem;
import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.entity.OrderTotalByItem;
import jsysSpring.sales.mapper.CustomerMapper;
import jsysSpring.sales.mapper.OrderMapper;

/**
 * 商品別受注集計Serviceの実装クラス
 * @author FLM
 * @version 1.0.0
 */
@Service
public class OrderTotalByItemServiceImpl implements OrderTotalByItemService {

	/** Mapper */
	@Autowired
	private CustomerMapper customerMapper;

	/** Mapper */
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * @see jsysSpring.sales.service.OrderTotalByItemService#aggregateByItem(java.lang.String)
	 */
	@Override
	public AggregationOrderTotalByItem aggregateByItem(String customerCode)
			throws SalesBusinessException {

		//戻り値用のAggregationOrderTotalByItem（DTO）オブジェクトを生成
		AggregationOrderTotalByItem result = new AggregationOrderTotalByItem();

		//CustomerMapperのメソッド呼び出し得意先情報を取得
		Customer customer = customerMapper.findCustomerIgnoreDeleteFlag(customerCode);

		//customerがnullの場合、SalesBusinessExceptionをスロー
		if(customer==null) {
			throw new SalesBusinessException(customerCode + "に一致する得意先情報が存在しません。");
		}

		//OrderMapperメソッド呼び出し商品別受注集計リストを取得
		ArrayList<OrderTotalByItem> orderTotalByItemList = orderMapper.createOrderTotalListByItem(customerCode);

		//orderTotalByItemListが空の場合、SalesBusinessExceptionをスロー
		if(orderTotalByItemList.isEmpty()) {
			throw new SalesBusinessException("該当する集計情報が存在しません。");
		}

		//戻り値用のAggregationOrderTotalByItem（DTO）オブジェクトに商品別受注集計リストを設定
		result.setOrderTotalByItemList(orderTotalByItemList);
		//戻り値用のAggregationOrderTotalByItem（DTO）オブジェクトに得意先名を設定
		result.setCustomerName(customer.getCustomerName());

		//総合計の計算
		int aggregateTotal = 0;
		for (OrderTotalByItem order : orderTotalByItemList) {
			aggregateTotal += order.getTotalPrice();
		}

		//AggregationOrderTotalByItem（DTO）オブジェクトに総合計を設定
		result.setAggregateTotal(aggregateTotal);

		return result;
	}

}
