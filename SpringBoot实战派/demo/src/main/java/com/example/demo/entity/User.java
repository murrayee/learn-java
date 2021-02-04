package com.example.demo.entity;
import com.example.demo.AnswerConstraint;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class User implements Serializable {

    private long id;

    @NotNull(message = "用户名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 18 ,message = "最小18岁")
    @Max(value = 60,message = "最大60岁")
    private Integer age;

    @NotNull(message = "邮箱不能为空")
    private String email;

    @AnswerConstraint
    private String answer;
}
