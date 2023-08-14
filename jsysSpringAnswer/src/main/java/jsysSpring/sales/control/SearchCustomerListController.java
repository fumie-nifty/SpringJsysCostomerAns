/**
 * SearchCustomerListController.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.control;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AuthEmployee;
import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.service.SearchCustomerListService;

/**
 * ユースケースID		UC205
 * ユースケース名		得意先一覧を表示する
 * 得意先一覧機能を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Controller
public class SearchCustomerListController {

	/** Service */
	@Autowired
	private SearchCustomerListService service;

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
	 * 得意先一覧画面の遷移
	 * @return /customer/V205_01CustomerListView
	 */
	@RequestMapping("/searchCustomerList")
	public String searchCustomerList(Model model) {

		//serviceのsearchCustomerList()メソッドを呼び出し得意先情報リストを取得
		ArrayList<Customer> customerList = service.searchCustomerList();

		// modelに得意先情報リストを登録
		model.addAttribute("customerList", customerList);

		//得意先一覧画面
		return "/customer/V205_01CustomerListView";
	}

	/**
	 * 業務例外のハンドリング
	 * ハンドリングする例外クラス： SalesBusinessException.class
	 */
	@ExceptionHandler(SalesBusinessException.class)
	public String catchBizException(Model model, Exception e) {

		// エラーメッセージをModelに設定
		model.addAttribute("message", e.getMessage());

		model.addAttribute("authEmployee",authEmployee);

		return "/customer/V205_01CustomerListView";
	}

}
