package org.hzz.controller.ums;

import org.hzz.common.api.CommonResult;
import org.hzz.dto.ums.UmsAdminLoginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @PostMapping("/register")
    public CommonResult register(UmsAdminLoginParam umsAdminLoginParam){

        return CommonResult.success(umsAdminLoginParam);
    }
    @PostMapping("/login")
    public CommonResult login(){
        return CommonResult.success("success");
    }
}
