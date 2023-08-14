/**
 * EmployeeMapperFindEmployeeTest.java
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

import jsysSpring.sales.entity.Employee;
import jsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * EmployeeMapper.findEmployee()メソッドのテストをするクラス
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
@DisplayName("PT001_01:EmployeeMapper.findEmployee()メソッドのテスト")
public class EmployeeMapperFindEmployeeTest {

	@Autowired
	EmployeeMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT001_01_001：検索が成功する場合")
	void test001() {
		// setup
		String testArg_employeeNo = "H20001";
		Employee expected = new Employee("H20001","安藤直也","zy0001");

		// assert
		assertThat(sut.findEmployee(testArg_employeeNo)).isEqualTo(expected);

	}

	@Test
	@DisplayName("PT001_01_002：検索が失敗する場合（存在しない従業員番号）")
	void test002() {
		// setup
		String testArg_employeeNo = "HAAAAA";

		// assert
		assertThat(sut.findEmployee(testArg_employeeNo)).isNull();
	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT001_01_003：検索が失敗する場合（レコード0件）")
	void test003() {
		// setup
		String testArg_employeeNo = "H20001";

		// assert
		assertThat(sut.findEmployee(testArg_employeeNo)).isNull();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT001_01_004：DataAccessExceptionが発生する場合")
	class MemberTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("employee", "employee2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("employee2", "employee");
		}

		@Test
		@DisplayName("従業員情報の取得に失敗する")
		void test004() throws Exception {
			// assert
			assertThatThrownBy(() -> sut.findEmployee("H20001"))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
