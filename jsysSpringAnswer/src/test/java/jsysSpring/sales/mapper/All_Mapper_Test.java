/**
 * All_Mapper_Test.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * 全てのMapperクラスのテストクラスを実行するクラス
 * @author FLM
 * @version 1.0 2022/03/20
 */
@RunWith(JUnitPlatform.class)
@DisplayName("PT:Mapperのテスト")
@SelectPackages({ "jsysSpring.sales.mapper" })
public class All_Mapper_Test {}
