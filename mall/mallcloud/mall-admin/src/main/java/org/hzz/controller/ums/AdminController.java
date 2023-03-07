package org.hzz.controller.ums;

import org.hzz.common.api.CommonResult;
import org.hzz.dto.ums.UmsAdminParam;
import org.hzz.model.ums.UmsAdmin;
import org.hzz.service.ums.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @PostMapping("/register")
    public CommonResult register(@RequestBody UmsAdminParam umsAdminParam){
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        return CommonResult.success(umsAdminParam);
    }


    @PostMapping("/login")
    public CommonResult login(){
        return CommonResult.success("success");
    }
}
