package com.example.demo.login.domain.model;

import java.util.Date;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class SignupForm {
	
	//必須入力、メールアドレス形式
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String userId;
	
//	必須入力、長さ4~100、半角英数字のみ
	@NotBlank(groups = ValidGroup1.class)
	@Length(min=4, max=100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class)
	private String password;
	
	@NotBlank(groups = ValidGroup1.class)
	private String userName;
	
	//@DataTImeFormat
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	
	//値が20~10
	@Min(value=20, groups = ValidGroup2.class)
	@Max(value=100, groups = ValidGroup2.class)
	private int age;
	
	//falseのみ可能
	@AssertFalse(groups = ValidGroup2.class)
	private boolean marriage;

}
