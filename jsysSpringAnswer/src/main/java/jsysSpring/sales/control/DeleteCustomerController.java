/**
 * DeleteCustomerController.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AuthEmployee;
import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.form.SearchCustomerForm;
import jsysSpring.sales.service.DeleteCustomerService;

/**
 * ユースケースID		UC203
 * ユースケース名		得意先を削除する
 * 得意先削除機能を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@SessionAttributes(types = Customer.class)
@Controller
public class DeleteCustomerController {

	/** Service */
	@Autowired
	DeleteCustomerService service;

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
	 * 得意先削除画面の遷移
	 * @return /customer/V203_01CustomerDeleteView
	 */
	@RequestMapping("/goDeleteCustomer")
	public String goDeleteCustomer(Model model) {

		// 得意先検索情報フォームオブジェクトをModelに設定
		model.addAttribute("searchCustomerForm", new SearchCustomerForm());

		//得意先削除画面
		return "/customer/V203_01CustomerDeleteView";
	}

	/**
	 * 得意先を検索し、得意先削除画面を表示する
	 * マッピングするURL： /deleteSearchCustomer
	 * マッピングするHTTPメソッド： POST
	 * @param form 得意先検索情報入力フォーム
	 * @param result 入力値検証結果オブジェクト
	 * @return 得意先削除画面（/customer/V203_01CustomerDeleteView）
	 */
	@RequestMapping(value = "/deleteSearchCustomer", method = RequestMethod.POST)
	public String deleteSearchCustomer(
			@ModelAttribute("searchCustomerForm") @Validated SearchCustomerForm form,
			BindingResult result,Model model) {

		// 入力チェック
		if (result.hasErrors()) {
			// 得意先削除画面
			return "/customer/V203_01CustomerDeleteView";
		}

		//得意先検索情報入力フォームから得意先コード取得
		String customerCode = form.getCustomerCode();

		//serviceのdeleteSearchCustomer()メソッドを呼び出し、得意先情報を取得
		Customer customer = service.deleteSearchCustomer(customerCode);

		//modelに得意先情報を設定
		model.addAttribute(customer);

		//得意先削除画面
		return "/customer/V203_01CustomerDeleteView";
	}

	/**
	 * 得意先を削除し、得意先削除結果画面を表示する
	 * マッピングするURL： /deleteCustomer
	 * マッピングするHTTPメソッド： POST
	 * @param customer 得意先情報
	 * @param result 入力値検証結果オブジェクト
	 * @return 得意先削除結果画面（/customer/V203_02CustomerDeleteResultView）
	 */
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public String deleteCustomer(@ModelAttribute("customer") Customer customer,Model model,SessionStatus status) {

		//セッション化のcustomerから得意先コードを取得
		String customerCode = customer.getCustomerCode();

		//serviceのdeleteCustomerを呼び出し得意先情報を削除
		service.deleteCustomer(customerCode);

		//セッション情報の削除
		status.setComplete();

		//得意先削除画面
		return "/customer/V203_02CustomerDeleteResultView";
	}

	/**
	 * 業務例外のハンドリング
	 * ハンドリングする例外クラス： SalesBusinessException.class
	 */
	@ExceptionHandler(SalesBusinessException.class)
	public String catchBizException(Model model, Exception e) {

		// エラーメッセージをModelに設定
		model.addAttribute("message", e.getMessage());

		// フォームオブジェクトをModelに設定
		model.addAttribute("searchCustomerForm", new SearchCustomerForm());
		model.addAttribute("authEmployee",authEmployee);

		return "/customer/V203_01CustomerDeleteView";
	}

}
