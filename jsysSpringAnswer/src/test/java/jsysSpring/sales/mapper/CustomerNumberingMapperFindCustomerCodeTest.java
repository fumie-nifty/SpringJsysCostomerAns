/**
 * CustomerNumberingMapperFindCustomerCodeTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jsysSpring.sales.mapper;

import static com.github.springtestdbunit.annotation.DatabaseOperation.*;
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

import jsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * CustomerNumberingMapper.findCustomerCode()メソッドのテストをするクラス
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
@DisplayName("PT003_01:CustomerNumberingMapper.findCustomerCode()メソッドのテスト")
public class CustomerNumberingMapperFindCustomerCodeTest {

	@Autowired
	CustomerNumberingMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT003_01_001：検索が成功する場合")
	void test001() {
		// setup
		int expected = 15;

		// assert
		assertThat(sut.findCustomerCode()).isEqualTo(expected);

	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT003_01_002：検索が失敗する場合(レコード0件)")
	void test002() {

		// assert
		assertThat(sut.findCustomerCode()).isNull();

	}

	@Nested
	@SpringBootTest
	@DisplayName("PT003_01_003：DataAccessExceptionが発生する場合")
	class CustomernumberingTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("customernumbering", "customernumbering2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("customernumbering2", "customernumbering");
		}

		@Test
		@DisplayName("得意先番号の取得に失敗する")
		void test003() throws Exception {
			// assert
			assertThatThrownBy(() -> sut.findCustomerCode())
				.isInstanceOf(DataAccessException.class);
		}
	}
}
