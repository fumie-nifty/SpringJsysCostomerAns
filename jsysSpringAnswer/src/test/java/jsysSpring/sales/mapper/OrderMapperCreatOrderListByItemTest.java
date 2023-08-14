/**
 * OrderMapperCreatOrderListByItemTest.java
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

import jsysSpring.sales.entity.OrderTotalByItem;
import jsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * OrderMapper.creatOrderListByItem()メソッドのテストをするクラス
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
@DisplayName("PT004_02:OrderMapper.creatOrderListByItem()メソッドのテスト")
public class OrderMapperCreatOrderListByItemTest {

	@Autowired
	OrderMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT004_02_001:リストが取得できる場合")
	void test001() {
		// setup
		List<OrderTotalByItem> expected = List.of(
			new OrderTotalByItem("AX0001","ミネラルウォーター（500ml）",25,80,2000),
			new OrderTotalByItem("BX0001","黄金にんにく・10日分",20,1200,24000),
			new OrderTotalByItem("CX0001","食べる納豆キナーゼ・10日分",10,600,6000));
		String customerCode = "KA0001";

		// assert
		assertThat(sut.createOrderTotalListByItem(customerCode)).isEqualTo(expected);
	}

	@Test
	@DisplayName("PT004_02_002:空のリストが取得できる場合")
	void test002() {
		// setup
		String customerCode = "KA0005";

		// assert
		assertThat(sut.createOrderTotalListByItem(customerCode)).isEmpty();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT004_02_003:DataAccessExceptionが発生する場合")
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
			String customerCode = "KA0001";
			// assert
			assertThatThrownBy(() -> sut.createOrderTotalListByItem(customerCode))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
