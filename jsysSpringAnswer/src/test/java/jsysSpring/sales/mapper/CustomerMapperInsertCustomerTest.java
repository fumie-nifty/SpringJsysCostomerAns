/**
 * CustomerMapperInsertCustomerTest.java
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

import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.test.util.ExecuteQueryForTestService;


/**
 * CustomerMapper.insertCustomer()メソッドのテストをするクラス
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
@DisplayName("PT002_02:CustomerMapper.insertCustomer()メソッドのテスト")
public class CustomerMapperInsertCustomerTest {

	@Autowired
	CustomerMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@ExpectedDatabase(value = "classpath:CustomerMapper/expectedCustomerInsert.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT002_02_001:登録が成功する場合")
	void test001() {
		// setup
		Customer testArg_customer
			= new Customer("KA0099", "Xストア", "03-102-1234", "101-7788","東京都大田区蒲田４－５－９",10);

		// assert
		sut.insertCustomer(testArg_customer);
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT002_02_002:DataAccessExceptionが発生する場合")
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
		@DisplayName("登録に失敗する")
		void test002() throws Exception {

			Customer testArg_customer
			= new Customer("KA0088", "Zストア", "03-102-1234", "101-7788","東京都大田区蒲田４－５－９",10);

		// assert
			assertThatThrownBy(() ->sut.insertCustomer(testArg_customer))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
