/**
 * CustomerMapperDeleteCustomerTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jsysSpring.sales.mapper;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.*;
import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import jsysSpring.sales.test.util.ExecuteQueryForTestService;


/**
 * CustomerMapper.deleteCustomer()メソッドのテストをするクラス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:AllTest/setupJsysDB.xml")
@DatabaseTearDown("classpath:AllTest/setupJsysDB.xml")
@DisplayName("PT002_03:CustomerMapper.deleteCustomer()メソッドのテスト")
public class CustomerMapperDeleteCustomerTest {

	@Autowired
	CustomerMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@ExpectedDatabase(value = "classpath:CustomerMapper/expectedCustomerDelete.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT002_03_001:更新に削除に成功する場合")
	void test001() {
		// setup
		String testArg_customerCode = "KA0015";

		// assert
		int count = sut.updateDeleteFlag(testArg_customerCode);

		assertThat(count).isEqualTo(1);
	}

	@Test
	@ExpectedDatabase(
		value = "classpath:CustomerMapper/expectedCustomerInitial.xml",
		assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT002_03_002:削除に失敗する場合（得意先が未登録")
	void test002() {
		// setup
		String testArg_customerCode = "KA9999";

		// assert
		int count = sut.updateDeleteFlag(testArg_customerCode);

		assertThat(count).isEqualTo(0);
	}

	@Test
	@ExpectedDatabase(
		value = "classpath:CustomerMapper/expectedCustomerInitial.xml",
		assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT002_03_003:削除に失敗する場合（得意先が削除済み)")
	void test003() {
		// setup
		String testArg_customerCode = "KA0002";

		// assert
		int count = sut.updateDeleteFlag(testArg_customerCode);

		assertThat(count).isEqualTo(0);
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT002_03_004:DataAccessExceptionが発生する場合")
	class CustomerTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("customer", "customer2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("customer2", "customer");
		}

		@Test
		@DisplayName("従業員情報の変更に失敗する")
		void test004() throws Exception {
			// setup
			String testArg_customerCode = "KA0015";

			// assert
					assertThatThrownBy(() -> sut.updateDeleteFlag(testArg_customerCode))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
