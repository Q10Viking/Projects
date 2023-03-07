package org.hzz.bean;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Movie {
    private String name;        // 电影名字
    private String actor;       // 电影导演
    private double time;        // 电影时长
    private double price;       // 电影票价
    private int number;         // 电影余票
    private Date startTime;     // 放映时间
}
