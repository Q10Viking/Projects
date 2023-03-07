package org.hzz.system;

import org.hzz.bean.Business;
import org.hzz.bean.User;

public interface CustomMovieService {
    void buyTicket(User user);
    /** 用户展示所有的电影信息 */
    void showAllMovies();

    Business getBusinessByShopName(String shopName);
}
