/**
 * UpdateCustomerController.java
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
import org.springframework.web.bind.support.SessionStatus;

import jsysSpring.sales.common.exception.SalesBusinessException;
import jsysSpring.sales.entity.AuthEmployee;
import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.form.SearchCustomerForm;
import jsysSpring.sales.form.UpdateCustomerForm;
import jsysSpring.sales.service.UpdateCustomerService;

/**
 * ユースケースID		UC204
 * ユースケース名		得意先を変更する
 * 得意先変更機能を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
//@SessionAttributes(types = UpdateCustomerForm.class)
@Controller
public class UpdateCustomerController {

	/** Service */
	@Autowired
	private UpdateCustomerService service;

	/** 認証済み従業員情報 */
	@Autowired
	private AuthEmployee authEmployee;

	/**
	 * セッションの認証済み従業員情報をModelに追加
	 * @return AuthEmployee
	 */
	@ModelAttribute("authEmployee")
	public AuthEmployee authEmployeeSetup() {
		return authEmployee;
	}

	/**
	 * 得意先変更画面の遷移
	 * @return /customer/V204_01CustomerChangeView
	 */
	@RequestMapping("/goUpdateCustomer")
	public String goUpdateCustomer(Model model) {

		// 得意先検索情報フォームオブジェクトをModelに設定
		model.addAttribute("searchCustomerForm", new SearchCustomerForm());
		//得意先変更情報フォームオブジェクトをModelに設定
		model.addAttribute("updateCustomerForm",new UpdateCustomerForm());

		//得意先変更画面
		return "/customer/V204_01CustomerChangeView";
	}

	/**
	 * 得意先を検索し、得意先変更画面を表示する
	 * マッピングするURL： /updateSearchCustomer
	 * マッピングするHTTPメソッド： POST
	 * @param form 得意先検索情報入力フォーム
	 * @param result 入力値検証結果オブジェクト
	 * @return 得意先変更画面（/customer/V204_01CustomerChangeView）
	 */
	@RequestMapping(value = "/updateSearchCustomer", method = RequestMethod.POST)
	public String updateSearchCustomer(
			@ModelAttribute("searchCustomerForm") @Validated SearchCustomerForm form,
			BindingResult result,Model model) {

		// 入力チェック
		if (result.hasErrors()) {
			// 得意先変更画面
			return "/customer/V204_01CustomerChangeView";
		}

		//得意先検索情報入力フォームから得意先コードを取得
		String customerCode = form.getCustomerCode();

		//serviceのupdateSearchCustomer()メソッドを呼び出し、得意先情報を取得
		Customer customer = service.updateSearchCustomer(customerCode);

		//得意先変更情報フォームオブジェクトに得意先情報を設定
		UpdateCustomerForm updateCustomerForm =
				new UpdateCustomerForm(customerCode,
														customer.getCustomerName(),
														customer.getCustomerTelno(),
														customer.getCustomerPostalcode(),
														customer.getCustomerAddress(),
														customer.getDiscountRate());

		//modelに得意先変更情報フォームオブジェクトを登録
		model.addAttribute("updateCustomerForm",updateCustomerForm);

		//得意先変更画面
		return "/customer/V204_01CustomerChangeView";
	}

	/**
	 * 得意先を変更し、得意先変更結果画面を表示する
	 * マッピングするURL： /deleteCustomer
	 * マッピングするHTTPメソッド： POST
	 * @param customer 得意先変更情報入力フォームオブジェクト
	 * @param result 入力値検証結果オブジェクト
	 * @return 得意先変更結果画面（/customer/V204_02CustomerChangeResultView）
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public String updateCustomer(	@ModelAttribute("updateCustomerForm")
												@Validated UpdateCustomerForm form,
												BindingResult result,
												Model model,SessionStatus status) {

		// 入力チェック
		if (result.hasErrors()) {
			//得意先検索画面の得意先コードを表示するために
			//得意先検索情報入力フォームを生成
			SearchCustomerForm searchCustomerForm = new SearchCustomerForm();
			//得意先検索情報入力フォームに得意先変更情報入力フォームの得意先コードを設定
			searchCustomerForm.setCustomerCode(form.getCustomerCode());
			//modelに得意先検索情報入力フォームを登録
			model.addAttribute("searchCustomerForm", searchCustomerForm);
			// 得意先変更画面
			return "/customer/V204_01CustomerChangeView";
		}

		//得意先変更情報入力フォームオブジェクトをもとに、得意先情報を作成
		Customer customer = new Customer(	form.getCustomerCode(),
																form.getCustomerName(),
																form.getCustomerTelno(),
																form.getCustomerPostalcode(),
																form.getCustomerAddress(),
																form.getDiscountRate());

		//serviceのupdateCustomer()メソッドを呼び出し、得意先情報を更新
		service.updateCustomer(customer);

		//modelに得意先情報を登録
		model.addAttribute(customer);

		//得意先変更結果画面
		return "/customer/V204_02CustomerChangeResultView";
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

		return "/customer/V204_01CustomerChangeView";
	}

}
