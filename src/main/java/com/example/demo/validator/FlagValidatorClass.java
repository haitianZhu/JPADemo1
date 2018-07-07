package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author: haitian
 * @Date: 2018/7/7 上午11:00
 * @Description:
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {

    // 临时变量保存flag值列表
    private String values;

    // 初始化values值
    @Override
    public void initialize(FlagValidator flagValidator) {
        this.values = flagValidator.values();
    }

    // 实现验证
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        // 分割定义的有效值
        String[] value_array = values.split(",");
        boolean isFlag = false;
        for (String s : value_array) {
            if (s.equals(value)) {
                isFlag = true;
                break;
            }
        }

        return isFlag;
    }
}
