package org.hzz.model.ums;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UmsAdmin {
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
    private String note;
    private Date createTime;
    private Date loginTime;
    private Integer status;
}
