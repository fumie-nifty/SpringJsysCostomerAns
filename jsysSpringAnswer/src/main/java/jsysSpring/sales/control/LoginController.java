/**
 * LoginController.java
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
import jsysSpring.sales.entity.Employee;
import jsysSpring.sales.form.LoginForm;
import jsysSpring.sales.service.LoginService;

/**
 * ユースケースID		UC101
 * ユースケース名		ログインする
 * ログイン機能を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Controller
public class LoginController {

	/** Service */
	@Autowired
	LoginService service;

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
	 * ログイン覧画面（トップ）への遷移
	 * @return /V101_01Login
	 */
	@RequestMapping("/")
	public String top(Model model) {

		// ログイン情報フォームオブジェクトをModelに設定
		model.addAttribute("loginForm", new LoginForm());

		//ログイン画面
		return "/V101_01Login";
	}

	/**
	 * ログイン画面を表示する
	 * マッピングするURL： /logint
	 * マッピングするHTTPメソッド： POST
	 * @param form ログイン情報入力フォーム
	 * @param result 入力値検証結果オブジェクト
	 * @return メインメニュー画面（/V101_02MainMenu）
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logint(
			@ModelAttribute("loginForm") @Validated LoginForm form,
			BindingResult result) {

		// 入力チェック
		if (result.hasErrors()) {
			// ログイン画面
			return "/V101_01Login";
		}

		//フォームオブジェクトから入力データの取得
		String employeeNo = form.getEmployeeNo();
		String password = form.getPassword();

		//LoginServiceのlogin呼び出し、認証済み従業員情報を取得
		Employee employee = service.login(employeeNo, password);

		//セッションの認証従業員オブジェクトに従業員番号と従業員名を設定
		authEmployee.setEmployeeNo(employee.getEmployeeNo());
		authEmployee.setEmployeeName(employee.getEmployeeName());

		//メインメニュー画面
		return "/V101_02MainMenu";
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
		model.addAttribute("loginForm", new LoginForm());

		return "/V101_01Login";
	}

}
