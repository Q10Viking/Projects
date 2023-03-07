package org.hzz.system.utils;

import org.hzz.bean.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Util {
    public static final Scanner SC =  new Scanner(System.in);
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public static String stringFormatTenLength(String ...msg){
        StringBuilder builder = new StringBuilder();
        for (String m:msg){
            builder.append(String.format("%-10s",m));
        }
        return builder.toString();
    }

    public static void printTitle(){
        System.out.println(Util.stringFormatTenLength(
                "片名","主演","时长","评分","票价","余票数量","放映时间"
        ));
    }

    public static void printMovie(Movie movie){
        System.out.println(Util.stringFormatTenLength(
                movie.getName(),
                movie.getActor(),
                "9.0",
                String.valueOf(movie.getTime()),
                String.valueOf(movie.getPrice()),
                String.valueOf(movie.getNumber()),
                SDF.format(movie.getStartTime())
        ));
    }

    public static Date parseDate(String date){
        try {
           return SDF.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("解析失败");
        }
    }
}
