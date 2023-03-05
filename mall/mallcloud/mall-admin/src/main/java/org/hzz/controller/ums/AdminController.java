package org.hzz.controller.ums;

import org.hzz.common.api.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/login")
    public CommonResult login(){
        return CommonResult.success("success");
    }
}
