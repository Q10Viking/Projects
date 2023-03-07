package org.hzz.system.impl;

import org.hzz.bean.Business;
import org.hzz.bean.Customer;
import org.hzz.bean.User;
import org.hzz.system.BusinessMovieService;
import org.hzz.system.CustomMovieService;
import org.hzz.system.MovieSystem;
import org.hzz.system.command.MovieCommand;
import org.hzz.system.db.MovieDB;
import org.hzz.system.utils.Util;

import java.util.Optional;

public class MovieSystemImpl implements MovieSystem {
    public static final ThreadLocal<User> threadLocal = new ThreadLocal<>();
    private MovieDB db;
    private BusinessMovieService businessMovieService;
    private CustomMovieService customMovieService;


    public MovieSystemImpl() {
        this.db = new MovieDB();
        this.businessMovieService = new BusinessMovieServiceImpl(db);
        this.customMovieService = new CustomMovieServiceImpl(db);
    }

    @Override
    public void showMain() {
        while (true) {
            System.out.println("===============电影商铺首页=================");
            System.out.println("1. 登录");
            System.out.println("2. 用户注册");
            System.out.println("3. 商家注册");
            System.out.println("4. 退出系统");
            System.out.println("=========================================");
            System.out.println("请输入操作指定");

            String choice = Util.SC.nextLine();
            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                case "3":
                    System.out.println("实现中...");
                    break;
                case "4":
                    System.out.println("退出成功");
                    return;
                default:
                    System.out.println("命令输入错误，请重新输入");
                    break;
            }
        }
    }

    @Override
    public void login() {
        while (true) {
            System.out.println("请您输入登录名称：");
            String username = Util.SC.nextLine();
            System.out.println("请您输入登录密码：");
            String password = Util.SC.nextLine();

            User user = this.getUserByLoginName(username);

            if (user != null) {
                if (user.getPassWord().equals(password)) {
                    System.out.printf("欢迎： %s~~~" + System.lineSeparator(),
                            user.getUserName());
                    threadLocal.set(user);
                    if (user instanceof Customer) {
                        this.showCustomerMain();
                    } else {
                        this.showBusinessMain();
                    }
                } else {
                    System.out.println("密码错误，请重新输入");
                }
            } else {
                System.out.println("用户名错误，请重新输入");
            }
        }
    }

    @Override
    public void showCustomerMain() {
        User loginUser = MovieSystemImpl.threadLocal.get();
        while (true) {
            System.out.println("============客户界面===================");
            System.out.println(loginUser.getUserName() + (loginUser.getSex() == '男' ? "先生" : "女士" + "欢迎您进入系统" +
                    "\t余额：" + loginUser.getMoney()));
            System.out.println("请您选择要操作的功能：");
            System.out.println("1、展示全部影片信息功能:");
            System.out.println("2、根据电影名称查询电影信息:");
            System.out.println("3、评分功能:");
            System.out.println("4、购票功能:");
            System.out.println("5、退出系统:");
            System.out.println("请输入您要操作的命令：");

            String command = Util.SC.nextLine();
            switch (command) {
                case "1":
                    //TODO第五部分 : 展示全部排片信息
                    customMovieService.showAllMovies();
                    break;
                case "2":
                    // 根据电影名称查询电影信息(选做)
                    break;
                case "3":
                    // 评分功能(扩展功能,选做)
                    break;
                case "4":
                    //TOdo 第六部分 :  购票功能
                    customMovieService.buyTicket(loginUser);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("不存在该命令！！");
                    break;
            }
        }
    }

    @Override
    public void showBusinessMain() {
        User user = threadLocal.get();
        while (true) {
            System.out.println("==============商家界面===================");
            System.out.println(user.getUserName() + (user.getSex() == '男' ? "先生" : "女士" + "欢迎您进入系统"));
            System.out.println("1、展示详情:");
            System.out.println("2、上架电影:");
            System.out.println("3、下架电影:");
            System.out.println("4、修改电影:");
            System.out.println("5、退出:");
            System.out.println("请输入您要操作的命令：");
            String code = Util.SC.nextLine();


            Optional<MovieCommand> command = MovieCommand.getCommandByCode(code);
            if (command.isPresent() && command.get() == MovieCommand.EXIT) {
                break;
            } else {
                command.ifPresent(movieCommand -> movieCommand.operation(businessMovieService));
            }
        }
    }


    @Override
    public User getUserByLoginName(String loginName) {
        return this.db.getUserByName(loginName);
    }
}
