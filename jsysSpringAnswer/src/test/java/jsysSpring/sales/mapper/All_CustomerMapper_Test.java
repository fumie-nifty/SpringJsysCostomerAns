/**
 * All_CustomerMapper_Test.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * CustomerMapperの全テストクラスの実行をするクラス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@RunWith(JUnitPlatform.class)
@DisplayName("PT002:CustomerMapperのテスト")
@SelectClasses({
	CustomerMapperFindCustomerTest.class,
	CustomerMapperInsertCustomerTest.class,
	CustomerMapperDeleteCustomerTest.class,
	CustomerMapperUpdateCustomerTest.class,
	CustomerMapperFindAllCustomerTest.class,
	CustomerMapperFindCustomerrIgnoreDeleteFlagTest.class
	})
public class All_CustomerMapper_Test {}
