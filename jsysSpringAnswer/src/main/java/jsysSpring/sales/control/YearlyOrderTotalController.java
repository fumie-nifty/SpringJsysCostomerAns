/**
 * YearlyOrderTotalController.java
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
import jsysSpring.sales.entity.AggregationOrderTotalByCustomer;
import jsysSpring.sales.entity.AuthEmployee;
import jsysSpring.sales.entity.OrderTotalByCustomer;
import jsysSpring.sales.form.AggregateYearForm;
import jsysSpring.sales.service.YearlyOrderTotalService;

/**
 * ユースケースID		UC302
 * ユースケース名		年次の受注を集計する
 * 年別の受注集計を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Controller
public class YearlyOrderTotalController {

	/** Service */
	@Autowired
	YearlyOrderTotalService service;

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
	 * 年次受注集計画面
	 * @return /aggregate/V302_01YearlyOrderTotalView
	 */
	@RequestMapping("/goYearlyOrderTotal")
	public String goYearlyOrderTotal(Model model) {

		// 集計年情報フォームオブジェクトをModelに設定
		model.addAttribute("aggregateYearForm", new AggregateYearForm());

		//年次受注集計画面
		return "/aggregate/V302_01YearlyOrderTotalView";
	}

	/**
	 * 受注情報を集計し、年次受注集計画面を表示する
	 * マッピングするURL： /aggregateYearly
	 * マッピングするHTTPメソッド： POST
	 * @param form 集計年情報入力フォーム
	 * @param result 入力値検証結果オブジェクト
	 * @return 年次受注集計画面（/aggregate/V302_01YearlyOrderTotalView）
	 */
	@RequestMapping(value = "/aggregateYearly", method = RequestMethod.POST)
	public String aggregateYearly(
			@ModelAttribute("aggregateYearForm") @Validated AggregateYearForm form,
			BindingResult result,Model model,SessionStatus status) {

		// 入力チェック
		if (result.hasErrors()) {
			// 年次受注集計画面
			return "/aggregate/V302_01YearlyOrderTotalView";
		}

		//serviceのaggregateYearly()メソッドを呼び出し得意先別受注集計情報DTOを取得
		AggregationOrderTotalByCustomer sggregationOrderTotalByCustomer =
				service.aggregateYearly(form.getYear());

		//得意先別受注集計情報DTOから得意先別受注集計情報リストを取得
		ArrayList<OrderTotalByCustomer> orderTotalByCustomerList =
				sggregationOrderTotalByCustomer.getOrderTotalByCustomerList();

		//得意先別受注集計情報DTOから総計を取得
		Integer aggregateTotal = sggregationOrderTotalByCustomer.getAggregateTotal();

		//modleに得意先別受注集計リストを設定
		model.addAttribute("orderTotalByCustomerList",orderTotalByCustomerList);
		//modleに総計を設定
		model.addAttribute("aggregateTotal",aggregateTotal);

		//年次受注集計画面
		return "/aggregate/V302_01YearlyOrderTotalView";
	}

	/**
	 * 業務例外のハンドリング
	 * ハンドリングする例外クラス： BusinessException.class
	 * @param model Modelオブジェクト
	 * @param e 例外オブジェクト
	 * @return 年次受注集計画面（/aggregate/V302_01YearlyOrderTotalView）
	 */
	@ExceptionHandler(SalesBusinessException.class)
	public String catchBizException(Model model, Exception e) {

		// エラーメッセージをModelに設定
		model.addAttribute("message", e.getMessage());

		// フォームオブジェクトをModelに設定
		model.addAttribute("aggregateYearForm", new AggregateYearForm());
		model.addAttribute("authEmployee",authEmployee);

		return "/aggregate/V302_01YearlyOrderTotalView";
	}


}
