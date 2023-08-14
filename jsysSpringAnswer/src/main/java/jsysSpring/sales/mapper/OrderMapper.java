/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderMapper.java
 */
package jsysSpring.sales.mapper;

import jsysSpring.sales.entity.OrderTotalByItem;
import jsysSpring.sales.entity.OrderTotalByCustomer;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

/**
 * ORDERテーブルを利用するMapperインターフェイス
 * @author FLM
 * @version 1.0 2022/03/20
 */

@Mapper
public interface OrderMapper {

	/**
	 * 引数で指定された期間の得意先別の受注金額集計リストを取得する。
	 * @param startDate 開始日
	 * @param endDate 終了日
	 * @return 得意先別受注集計リスト
	 */
	public ArrayList<OrderTotalByCustomer> createOrderTotalListByCustomer(String startDate, String endDate);

	/**
	 * 引数で指定された得意先の商品別の受注金額集計リストを取得する。
	 * @param customerCode 得意先コード
	 * @return 商品別受注集計リスト
	 */
	public ArrayList<OrderTotalByItem> createOrderTotalListByItem(String customerCode);

}
