/**
 * CustomerMapperFindAllCustomerTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jsysSpring.sales.mapper;

import static com.github.springtestdbunit.annotation.DatabaseOperation.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

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

import jsysSpring.sales.entity.Customer;
import jsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * CustomerMapper.findAllCustomer()メソッドのテストをするクラス
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
@DisplayName("PT002_05:CustomerMapper.findAllCustomer()メソッドのテスト")
public class CustomerMapperFindAllCustomerTest {

	@Autowired
	CustomerMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT002_05_001:リストが取得できる場合")
	void test001() {
		// setup
		List<Customer> expected = List.of(
			new Customer("KA0001","Aストア","045-128-3581","220-0001","横浜市西区北幸２－１",0),
			new Customer("KA0003","Cストア","045-128-3583","220-0003","横浜市西区みなとみらい２－１",0),
			new Customer("KA0004","Dストア","045-128-3584","220-0004","横浜市西区南幸１－４",0),
			new Customer("KA0005","Eストア","045-128-3585","220-0005","横浜市西区北幸２－５",0),
			new Customer("KA0006","Fストア","045-128-3586","220-0006","横浜市西区北幸２－６",0),
			new Customer("KA0007","Gストア","045-128-3587","220-0007","横浜市西区高島１－１",0),
			new Customer("KA0008","Hストア","045-128-3588","220-0008","横浜市西区高島１－２",0),
			new Customer("KA0009","Iストア","045-128-3589","220-0009","横浜市西区高島２－３",0),
			new Customer("KA0011","Kストア","045-150-3591","220-0011","横浜市西区みなとみらい２－１",0),
			new Customer("KA0012","Lストア","045-150-3592","220-0012","横浜市西区みなとみらい２－２",0),
			new Customer("KA0013","Mストア","045-150-3593","220-0013","横浜市西区みなとみらい２－３",0),
			new Customer("KA0014","Nストア","045-150-3594","220-0014","横浜市西区みなとみらい２－４",0),
			new Customer("KA0015","Oストア","045-150-3595","220-0015","横浜市西区みなとみらい２－５",0));

		// assert
		assertThat(sut.findAllCustomer()).isEqualTo(expected);
	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT002_05_002:空のリストを取得する場合（テーブルデータ0件)")
	void test002() {
		// assert
		assertThat(sut.findAllCustomer()).isEmpty();
	}

	@Test
	@DatabaseSetup("classpath:CustomerMapper/setupCustomerAllDeleteFlag.xml")
	@DisplayName("PT002_05_003:空のリストを取得する場合（全得意先が削除済み)")
	void test003() {
		// assert
		assertThat(sut.findAllCustomer()).isEmpty();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT002_05_004:DataAccessExceptionが発生する場合")
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
		@DisplayName("リストの取得に失敗する")
		void test004() throws Exception {
			// assert
			assertThatThrownBy(() -> sut.findAllCustomer())
				.isInstanceOf(DataAccessException.class);
		}
	}
}
