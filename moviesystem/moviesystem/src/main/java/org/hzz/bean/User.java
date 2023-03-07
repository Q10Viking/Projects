package org.hzz.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String loginName;   // 登录名 不能重复
    private String userName;    // 昵称
    private String passWord;    // 密码
    private char sex;           // 性别
    private String phone;       // 手机号码
    private double money;       // 账户金额
}
