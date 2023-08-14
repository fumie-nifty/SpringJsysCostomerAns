/**
 * AggregateYearMothForm.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsysSpring.sales.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 月別受注集計情報入力フォーム
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregateYearMothForm implements Serializable {

	/** 年 */
	@Min(2000)
	@Max(9999)
	@NotNull
	private Integer year;

	/** 月 */
	@Min(1)
	@Max(12)
	@NotNull
	private Integer month;
}
