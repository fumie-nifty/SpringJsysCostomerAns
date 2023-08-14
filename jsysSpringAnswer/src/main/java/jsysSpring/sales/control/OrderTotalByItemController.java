/**
 * OrderTotalByItemController.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.control;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AggregationOrderTotalByItem;
import jsysSpring.sales.entity.AuthEmployee;
import jsysSpring.sales.entity.OrderTotalByItem;
import jsysSpring.sales.form.AggregateCustomerForm;
import jsysSpring.sales.service.OrderTotalByItemService;

/**
 * ユースケースID		UC303
 * ユースケース名		商品別の受注を集計する
 * 商品別の受注集計を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Controller
public class OrderTotalByItemController {

	/** Service */
	@Autowired
	OrderTotalByItemService service;

	/** 認証済み従業員情報 */
	@Autowired
	AuthEmployee authEmployee;

	/**
	 * セッションの認証済み従業員情報をModelに追加
	 * @return AuthEmployee
	 */
	@ModelAttribute("authEmployee")
	public AuthEmployee authEmployeeSetup() {
		return authEmployee;
	}

	/**
	 * 商品別受注集計画面遷移
	 * @return /aggregate/V303_01OrderTotalByItemView
	 */
	@RequestMapping("/goOrderTotalByItem")
	public String goOrderTotalByItem(Model model) {

		// 集計得意先情報フォームオブジェクトをModelに設定
		model.addAttribute("aggregateCustomerForm", new AggregateCustomerForm());

		//商品別受注集計画
		return "/aggregate/V303_01OrderTotalByItemView";
	}

	/**
	 * 受注情報を集計し、商品別受注集計画面を表示する
	 * マッピングするURL： /aggregateByItem
	 * マッピングするHTTPメソッド： POST
	 * @param form 集計得意先情報入力フォーム
	 * @param result 入力値検証結果オブジェクト
	 * @return 月別受注集計画面（/aggregate/V303_01OrderTotalByItemView）
	 */
	@RequestMapping(value = "/aggregateByItem", method = RequestMethod.POST)
	public String aggregateByItem(
			@ModelAttribute("aggregateCustomerForm") @Validated AggregateCustomerForm form,
			BindingResult result,Model model,SessionStatus status	) {

		// 入力チェック
		if (result.hasErrors()) {
			// 商品別受注集計画面
			return "/aggregate/V303_01OrderTotalByItemView";
		}

		//serviceのaggregateByItem()メソッドを呼び出し商品別受注集計情報DTOを取得
		AggregationOrderTotalByItem aggregationOrderTotalByItem =
				service.aggregateByItem(form.getCustomerCode());

		//商品別受注集計情報DTOから商品別受注集計情報リストを取得
		ArrayList<OrderTotalByItem> orderTotalByItemList =
				aggregationOrderTotalByItem.getOrderTotalByItemList();
		//商品別受注集計情報DTOから総計を取得
		Integer aggregateTotal = aggregationOrderTotalByItem.getAggregateTotal();
		//商品別受注集計情報DTOから得意先名を取得
		String customerName = aggregationOrderTotalByItem.getCustomerName();

		//modleに商品別受注集計情報リストを設定
		model.addAttribute("orderTotalByItemList",orderTotalByItemList);
		//modleに総計を設定
		model.addAttribute("aggregateTotal",aggregateTotal);
		//modleに得意先名を設定
		model.addAttribute("customerName", customerName);

		//商品別受注集計画面
		return "/aggregate/V303_01OrderTotalByItemView";
	}

	/**
	 * 業務例外のハンドリング
	 * ハンドリングする例外クラス： BusinessException.class
	 * @param model Modelオブジェクト
	 * @param e 例外オブジェクト
	 * @return 商品別受注集計画面（/aggregate/V303_01OrderTotalByItemView）
	 */
	@ExceptionHandler(SalesBusinessException.class)
	public String catchBizException(Model model, Exception e) {

		// エラーメッセージをModelに設定
		model.addAttribute("message", e.getMessage());

		// フォームオブジェクトをModelに設定
		model.addAttribute("aggregateCustomerForm", new AggregateCustomerForm());
		model.addAttribute("authEmployee",authEmployee);

		return "/aggregate/V303_01OrderTotalByItemView";
	}


}
