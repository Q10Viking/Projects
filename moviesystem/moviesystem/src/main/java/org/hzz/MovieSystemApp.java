package org.hzz;

import org.hzz.system.MovieSystem;
import org.hzz.system.impl.MovieSystemImpl;

public class MovieSystemApp {
    private MovieSystem ms;
    public MovieSystemApp(MovieSystem ms){
        this.ms = ms;
    }
    public static void main(String[] args) {
        MovieSystemApp movieSystemApp = new MovieSystemApp(new MovieSystemImpl());
        movieSystemApp.start();
    }

    private void start(){
        ms.showMain();
    }
}
