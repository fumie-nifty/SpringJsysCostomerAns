/**
 * MonthlyOrderTotalController.java
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

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AggregationOrderTotalByCustomer;
import jsysSpring.sales.entity.AuthEmployee;
import jsysSpring.sales.entity.OrderTotalByCustomer;
import jsysSpring.sales.form.AggregateYearMothForm;
import jsysSpring.sales.service.MonthlyOrderTotalService;

/**
 * ユースケースID		UC301
 * ユースケース名		月別の受注を集計する
 * 月別の受注集計を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Controller
public class MonthlyOrderTotalController {

	/** Service */
	@Autowired
	MonthlyOrderTotalService service;

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
	 * 月別受注集計画面遷移
	 * @return /aggregate/V301_01MonthlyOrderTotalView
	 */
	@RequestMapping("/goMonthlyOrderTotal")
	public String goMonthlyOrderTotal(Model model) {

		// 集計年月情報フォームオブジェクトをModelに設定
		model.addAttribute("aggregateYearMothForm", new AggregateYearMothForm());

		//月別受注集計画面
		return "/aggregate/V301_01MonthlyOrderTotalView";
	}

	/**
	 * 受注情報を集計し、月別受注集計画面を表示する
	 * マッピングするURL： /searchCustomer
	 * マッピングするHTTPメソッド： POST
	 * @param form 集計年月情報入力フォーム
	 * @param result 入力値検証結果オブジェクト
	 * @return 月別受注集計画面（/aggregate/V301_01MonthlyOrderTotalView）
	 */
	@RequestMapping(value = "/aggregateMonthly", method = RequestMethod.POST)
	public String aggregateMonthly(
			@ModelAttribute("aggregateYearMothForm") @Validated AggregateYearMothForm form,
			BindingResult result,Model model) {

		// 入力チェック
		if (result.hasErrors()) {
			// 月別受注集計画面
			return "/aggregate/V301_01MonthlyOrderTotalView";
		}

		//serviceのaggregateYearly()メソッドを呼び出し得意先別受注集計情報DTOを取得
		AggregationOrderTotalByCustomer aggregationOrderTotalByCustomer =
				service.aggregateMonthly(form.getYear(), form.getMonth());

		//得意先別受注集計情報DTOから得意先別受注集計情報リストを取得
		ArrayList<OrderTotalByCustomer> orderTotalByCustomerList =
				aggregationOrderTotalByCustomer.getOrderTotalByCustomerList();

		//得意先別受注集計情報DTOから総計を取得
		Integer aggregateTotal = aggregationOrderTotalByCustomer.getAggregateTotal();

		//modleに得意先別受注集計リストを設定
		model.addAttribute("orderTotalByCustomerList",orderTotalByCustomerList);
		//modleに総計を設定
		model.addAttribute("aggregateTotal",aggregateTotal);

		//月別受注集計画面
		return "/aggregate/V301_01MonthlyOrderTotalView";
	}

	/**
	 * 業務例外のハンドリング
	 * ハンドリングする例外クラス： BusinessException.class
	 * @param model Modelオブジェクト
	 * @param e 例外オブジェクト
	 * @return 月別受注集計画面（/aggregate/V301_01MonthlyOrderTotalView）
	 */
	@ExceptionHandler(SalesBusinessException.class)
	public String catchBizException(Model model, Exception e) {

		// エラーメッセージをModelに設定
		model.addAttribute("message", e.getMessage());

		// フォームオブジェクトをModelに設定
		model.addAttribute("aggregateYearMothForm", new AggregateYearMothForm());
		model.addAttribute("authEmployee",authEmployee);

		return "/aggregate/V301_01MonthlyOrderTotalView";
	}

}
