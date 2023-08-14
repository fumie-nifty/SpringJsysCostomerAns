/**
 * RegistCustomerController.java
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

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AuthEmployee;
import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.form.RegistCustomerForm;
import jsysSpring.sales.service.RegistCustomerService;

/**
 * ユースケースID		UC20
 * ユースケース名		得意先を登録する
 * 得意先登録機能を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Controller
public class RegistCustomerController {

	/** Service */
	@Autowired
	RegistCustomerService service;

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
	 * 得意先登録画面の遷移
	 * @return /customer/V202_01CustomerRegistView
	 */
	@RequestMapping("/goRegistCustomer")
	public String goRegistCustomer(Model model) {

		// 得意先登録情報フォームオブジェクトをModelに設定
		model.addAttribute("registCustomerForm", new RegistCustomerForm());

		//得意先登録画面
		return "/customer/V202_01CustomerRegistView";
	}

	/**
	 * 得意先を登録し、得意先登録結果画面を表示する
	 * マッピングするURL： /registCustomer
	 * マッピングするHTTPメソッド： POST
	 * @param form 得意先登録情報入力フォーム
	 * @param result 入力値検証結果オブジェクト
	 * @return 得意先登録結果画面（/customer/V202_02CustomerRegistResultView）
	 */
	@RequestMapping(value = "/registCustomer", method = RequestMethod.POST)
	public String registCustomer(
			@ModelAttribute("registCustomerForm") @Validated RegistCustomerForm form,
			BindingResult result,Model model) {

		// 入力チェック
		if (result.hasErrors()) {
			// 得意先登録画面
			return "/customer/V202_01CustomerRegistView";
		}

		//得意先登録情報入力フォームのデータをもとに得意策情報を作成
		Customer customer = new Customer();
		customer.setCustomerName(form.getCustomerName());
		customer.setCustomerTelno(form.getCustomerTelno());
		customer.setCustomerPostalcode( form.getCustomerPostalcode());
		customer.setCustomerAddress( form.getCustomerAddress());
		customer.setDiscountRate(form.getDiscountRate());

		//serviceのregistCustomer()メソッドを呼び出し、得意先情報を登録
		service.registCustomer(customer);

		//modelに得意先情報を登録
		model.addAttribute(customer);

		//得意先登録結果画面
		return "/customer/V202_02CustomerRegistResultView";
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
		model.addAttribute("registCustomerForm", new RegistCustomerForm());
		model.addAttribute("authEmployee",authEmployee);

		return "/customer/V202_01CustomerRegistView";
	}

}
