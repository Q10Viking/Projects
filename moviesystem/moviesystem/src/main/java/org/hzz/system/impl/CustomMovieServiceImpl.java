package org.hzz.system.impl;

import org.hzz.bean.Business;
import org.hzz.bean.Movie;
import org.hzz.bean.User;
import org.hzz.system.CustomMovieService;
import org.hzz.system.db.MovieDB;
import org.hzz.system.utils.Util;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CustomMovieServiceImpl implements CustomMovieService {
    private MovieDB db;
    public CustomMovieServiceImpl(MovieDB db){
        this.db = db;
    }
    @Override
    public void buyTicket(User user) {
        showAllMovies();
        System.out.println("=========用户购票功能=========");
        while(true){
            System.out.println("请输入您要卖票的门店:");
            String shopName = Util.SC.nextLine();
            Business business = getBusinessByShopName(shopName);
            List<Movie> movies = db.getMoviesByBusiness(business);

            if(movies.size() == 0){
                System.out.println("该电影院关门了...");
                System.out.println("是否继续卖票? Y/N");
                String check = Util.SC.nextLine();
                if("n".equalsIgnoreCase(check)) break;
            }else{
                System.out.println("请输入购买的电影名称:");
                String movieName = Util.SC.nextLine();

                Optional<Movie> movie = movies.stream().filter(m -> m.getName().equals(movieName))
                        .findFirst();
                if(movie.isPresent()){
                    System.out.println("请输入要购买的电影票数:");
                    int count = Integer.parseInt(Util.SC.nextLine());
                    Double money = count * movie.get().getPrice();
                    System.out.println("您成功购买了" + movieName + count + "张票!,总金额是:" + money);
                    user.setMoney(user.getMoney() - money);
                    business.setMoney(business.getMoney()+money);
                    movie.get().setNumber(movie.get().getNumber() - count);
                    break;
                }else{
                    System.out.println("您录入的电影" + movieName + "不存在, 请从新录入...");
                }
            }

        }
        System.out.println("结束购买");
    }

    @Override
    public void showAllMovies() {
        Map<Business, List<Movie>> allMovie = db.getALLMovie();
        for(Business business: allMovie.keySet()){
            List<Movie> movies = allMovie.get(business);
            System.out.println(business.getShopName() + "\t电话" + business.getPhone() + "\t地址:" + business.getAddress());

            if(movies.size() == 0){
                System.out.println("====此商家未上传电影====");
            }else{
                Util.printTitle();
                for (Movie movie :
                        movies) {
                    Util.printMovie(movie);
                }
            }
            System.out.println("---------------------------------------------------");
        }
    }

    @Override
    public Business getBusinessByShopName(String shopName) {
        Map<Business, List<Movie>> allMovie = db.getALLMovie();
        Set<Business> businesses = allMovie.keySet();
        for(Business business:businesses){
            if(business.getShopName().equals(shopName)){
                return business;
            }
        }
        return null;
    }
}
