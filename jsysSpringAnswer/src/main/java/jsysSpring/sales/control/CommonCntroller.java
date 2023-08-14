/**
 * CommonCntroller.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import jsysSpring.sales.entity.AuthEmployee;

/**
 * ユースケースID		－
 * ユースケース名		ー
 * 共通処理を管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Controller
public class CommonCntroller {

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
	 * セッション情報を破棄してログイン画面を表示する
	 * マッピングするURL： /logout
	 * マッピングするHTTPメソッド： GET
	 * @param status セッション情報管理オブジェクト
	 * @return 「/」にフォワード
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status) {

		//セッション情報の解放
		status.setComplete();

		//「/」にフォワード
		return "redirect:/";

	}

	/**
	 * メインメニュー覧画面への遷移
	 * @return /V101_02MainMenu
	 */
	@RequestMapping(value = "/mainMenu", method = RequestMethod.GET)
	public String mainMenu() {

		return "/V101_02MainMenu";

	}

	/**
	 * 得意先管理メニュー覧画面への遷移
	 * @return /customer/V200_01CustomerManagementMenu
	 */
	@RequestMapping(value = "/customerMenu", method = RequestMethod.GET)
	public String customerMenu() {

		return "/customer/V200_01CustomerManagementMenu";

	}

	/**
	 * 得意先別集計メニュー覧画面への遷移
	 * @return /aggregate/V300_01OrderTotalMenu
	 */
	@RequestMapping(value = "/aggregateMenu", method = RequestMethod.GET)
	public String aggregateMenu() {

		return "/aggregate/V300_01OrderTotalMenu";

	}

}
