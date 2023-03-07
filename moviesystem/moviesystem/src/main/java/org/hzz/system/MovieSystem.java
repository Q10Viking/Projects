package org.hzz.system;

import org.hzz.bean.Business;
import org.hzz.bean.User;

public interface MovieSystem{

    /** 首页展示 */
    void showMain();
    /** 用户登录 , 用户包含了 [商家] 和 [客户] , 需要作出判断进入对应的页面展示*/
    void login();

    /** 客户主页面展示 */
    void showCustomerMain();
    void showBusinessMain();

    /**
     * 方法的功能为 : 接收一个用户名 , 在集合中查找此用户是否存在
     * 如果用户存在返回此用户的对象
     * 如果用户不存在返回null
     */
     User getUserByLoginName(String loginName);
}
