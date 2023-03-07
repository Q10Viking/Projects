package org.hzz.system.impl;

import org.hzz.bean.Business;
import org.hzz.bean.Movie;
import org.hzz.system.BusinessMovieService;
import org.hzz.system.db.MovieDB;
import org.hzz.system.utils.Util;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class BusinessMovieServiceImpl implements BusinessMovieService {

    private MovieDB db;

    public BusinessMovieServiceImpl(MovieDB db) {
        this.db = db;
    }

    @Override
    public void changeMovie() {
        while (true) {
            Business business = (Business) MovieSystemImpl.threadLocal.get();
            System.out.println("请输入要修改片名:");
            String movieName = Util.SC.nextLine();
            Movie movie = db.getMovieByName(business, movieName);

            if (movie == null) {
                System.out.println("您录入的电影名不存在, 请重新入要修改的电影片名...");
            } else {
                System.out.println("请输入修改后片名:");
                String newName = Util.SC.nextLine();
                System.out.println("请输入修改主演:");
                String actor =  Util.SC.nextLine();
                System.out.println("请输入修改时长:");
                String time =  Util.SC.nextLine();
                System.out.println("请输入修改票价:");
                String price =  Util.SC.nextLine();
                System.out.println("请输入修改票数:");
                String count =  Util.SC.nextLine();
                System.out.println("请输入修改放映时间:");
                String date =  Util.SC.nextLine();
                Date d =  Util.parseDate(date);

                movie.setName(newName);
                movie.setActor(actor);
                movie.setTime(Double.parseDouble(time));
                movie.setPrice(Double.parseDouble(price));
                movie.setNumber(Integer.parseInt(count));
                movie.setStartTime(d);
                System.out.println("修改成功");
                break;
            }
        }
    }

    @Override
    public void removeMovie() {
        while (true) {
            System.out.println("请输入你要下架的电影名称");
            String movieName = Util.SC.nextLine();
            Business business = (Business) MovieSystemImpl.threadLocal.get();
            boolean result = db.removeMovie(business, movieName);
            if (result) {
                System.out.printf("当前店铺%s已经成功下架电影%s" + System.lineSeparator(),
                        business.getShopName(), movieName);
            } else {
                // 键盘录入的电影不存在
                System.out.println("您的店铺没有商家" + movieName);
                System.out.println("请问继续下架吗? y/n");
                String check = Util.SC.nextLine();
                if ("n".equalsIgnoreCase(check)) return;
            }
        }
    }

    @Override
    public void showMovie() {
        String date = Util.SDF.format(new Date());
        Business business = (Business) MovieSystemImpl.threadLocal.get();
        System.out.printf("%s: %s 商家正在查看详情" + System.lineSeparator(),
                date, business.getUserName());
        System.out.printf("%s\t%s\t%s" + System.lineSeparator(),
                business.getShopName(), business.getPhone(), business.getAddress());
        List<Movie> movies = db.getMoviesByBusiness(business);

        if (movies.size() == 0) {
            System.out.println("当前未上传电影....");
        } else {
            Util.printTitle();
            for (Movie movie : movies) {
                Util.printMovie(movie);
            }
        }

    }

    @Override
    public void addMovie() {
        System.out.println("请输入片名:");
        String name = Util.SC.nextLine();
        System.out.println("请输入主演:");
        String actor = Util.SC.nextLine();
        System.out.println("请输入时长:");
        String time = Util.SC.nextLine();
        System.out.println("请输入票价:");
        String price = Util.SC.nextLine();
        System.out.println("请输入票数:");
        String number = Util.SC.nextLine();
        System.out.println("请输入放映时间:");
        String date = Util.SC.nextLine();

        try {
            Movie movie = Movie.builder()
                    .name(name)
                    .actor(actor)
                    .time(Double.parseDouble(time))
                    .price(Double.parseDouble(price))
                    .number(Integer.parseInt(number))
                    .startTime(Util.SDF.parse(date))
                    .build();
            Business user = (Business) MovieSystemImpl.threadLocal.get();
            List<Movie> movies = db.getMoviesByBusiness(user);
            movies.add(movie);
            System.out.println("成功上架电影: " + movie.getName());
        } catch (ParseException e) {
            throw new RuntimeException("解析失败");
        }
    }
}
