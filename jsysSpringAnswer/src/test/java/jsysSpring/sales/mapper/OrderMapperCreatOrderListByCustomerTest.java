/**
 * OrderMapperCreatOrderListByCustomerTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jsysSpring.sales.mapper;

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

import jsysSpring.sales.entity.OrderTotalByCustomer;
import jsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * OrderMapper.creatOrderListByCustomer()メソッドのテストをするクラス
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
@DisplayName("PT004_01:OrderMapper.creatOrderListByCustomer()メソッドのテスト")
public class OrderMapperCreatOrderListByCustomerTest {

	@Autowired
	OrderMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT004_01_001:リストが取得できる場合")
	void test001() {
		// setup
		List<OrderTotalByCustomer> expected = List.of(
			new OrderTotalByCustomer("KA0001","Aストア",13200),
			new OrderTotalByCustomer("KA0015","Oストア",18000));
		String startDate = "2021/12/01";
		String endDate = "2021/12/31";

		// assert
		assertThat(sut.createOrderTotalListByCustomer(startDate, endDate)).isEqualTo(expected);
	}

	@Test
	@DisplayName("PT004_01_002:空のリストが取得できる場合")
	void test002() {
		// setup
		String startDate = "2021/10/01";
		String endDate = "2021/10/31";

		// assert
		assertThat(sut.createOrderTotalListByCustomer(startDate, endDate)).isEmpty();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT004_01_003:DataAccessExceptionが発生する場合")
	class OrdersTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("orders", "orders2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("orders2", "orders");
		}

		@Test
		@DisplayName("リストの取得に失敗する")
		void test003() throws Exception {
			// setup
			String startDate = "2021/12/1";
			String endDate = "2021/1/31";
			// assert
			assertThatThrownBy(() -> sut.createOrderTotalListByCustomer(startDate, endDate))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
