package org.hzz.system.db;

import org.hzz.bean.Business;
import org.hzz.bean.Customer;
import org.hzz.bean.Movie;
import org.hzz.bean.User;
import org.hzz.system.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDB {
    private static final List<User> ALL_USERS = new ArrayList<>();
    // 店铺对应的电影
    private static final Map<Business, List<Movie>> ALL_MOVICES = new HashMap<>();

    static {
        initData();
    }

    public MovieDB() {
    }

    private static void initData() {
        // =====================客户数据========================
        Customer c = new Customer();
        c.setLoginName("q10viking");
        c.setPassWord("123456");
        c.setUserName("上山的打虎人");
        c.setSex('男');
        c.setMoney(10000);
        c.setPhone("110110");
        ALL_USERS.add(c);

        Customer c1 = new Customer();
        c1.setLoginName("hzz");
        c1.setPassWord("123456");
        c1.setUserName("桂林第一深情");
        c1.setSex('女');
        c1.setMoney(2000);
        c1.setPhone("111111");
        ALL_USERS.add(c1);

        // =====================商家数据========================
        Business b = new Business();
        b.setLoginName("shophzz");
        b.setPassWord("123456");
        b.setUserName("包租公");
        b.setMoney(0);
        b.setSex('男');
        b.setPhone("110110");
        b.setAddress("火星6号2B二层");
        b.setShopName("甜甜圈国际影城");
        ALL_USERS.add(b);

        Business b2 = new Business();
        b2.setLoginName("shopq10viking");
        b2.setPassWord("123456");
        b2.setUserName("黑马包租婆");
        b2.setMoney(0);
        b2.setSex('女');
        b2.setPhone("110110");
        b2.setAddress("火星8号8B八层");
        b2.setShopName("巧克力国际影城");
        ALL_USERS.add(b2);

        Movie movie = Movie.builder()
                .name("功夫")
                .actor("周星驰")
                .time(120)
                .price(65)
                .number(80)
                .startTime(Util.parseDate("2020-02-22"))
                .build();
        List<Movie> list = new ArrayList<>();
        list.add(movie);
        ALL_MOVICES.put(b, list);
        ALL_MOVICES.put(b2, new ArrayList<Movie>());
    }

    public User getUserByName(String name) {
        return ALL_USERS.stream()
                .filter(user -> user.getLoginName().equals(name))
                .findAny()
                .orElse(null);
    }

    public List<Movie> getMoviesByBusiness(Business business) {
        return ALL_MOVICES.getOrDefault(business,new ArrayList<Movie>());
    }

    public boolean removeMovie(Business business, String movieName) {
        List<Movie> movies = ALL_MOVICES.get(business);
        for (int i = movies.size() - 1; i >= 0; i--) {
            if(movies.get(i).getName().equals(movieName)){
                movies.remove(i);
                return true;
            }
        }
        return false;
    }

    public Movie getMovieByName(Business business, String movieName){
        List<Movie> movies = ALL_MOVICES.get(business);
        for (Movie movie :
                movies) {
            if(movie.getName().equals(movieName))
                return movie;
        }
        return null;
    }

    public Map<Business, List<Movie>> getALLMovie(){
        return ALL_MOVICES;
    }


}
