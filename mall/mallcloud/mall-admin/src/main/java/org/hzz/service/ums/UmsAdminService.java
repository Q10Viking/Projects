package org.hzz.service.ums;

import org.hzz.dto.ums.UmsAdminLoginParam;
import org.hzz.dto.ums.UmsAdminParam;
import org.hzz.model.ums.UmsAdmin;

public interface UmsAdminService {
    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);
}
