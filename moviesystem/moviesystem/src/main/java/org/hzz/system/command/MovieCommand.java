package org.hzz.system.command;

import org.hzz.system.BusinessMovieService;

import java.util.Optional;

public enum MovieCommand {
    SHOW("1"){
        @Override
        public void operation(BusinessMovieService businessMovieService) {
            businessMovieService.showMovie();
        }
    },
    ADD("2"){
        @Override
        public void operation(BusinessMovieService businessMovieService) {
            businessMovieService.addMovie();
        }
    },
    REMOVE("3"){
        @Override
        public void operation(BusinessMovieService businessMovieService) {
            businessMovieService.removeMovie();
        }
    },
    CHANGE("4"){
        @Override
        public void operation(BusinessMovieService businessMovieService) {
            businessMovieService.changeMovie();
        }
    },
    EXIT("5"){
        @Override
        public void operation(BusinessMovieService businessMovieService) {
            // do nothing
        }
    };

    private final String code;
     MovieCommand(String code){
        this.code = code;
    }

    public abstract void operation(BusinessMovieService businessMovieService);

    public static Optional<MovieCommand> getCommandByCode(String code){
        for (MovieCommand v:
             MovieCommand.values()) {
            if(v.code.equals(code)) return Optional.of(v);
        }
        System.out.println("录入指令有误,请重新选择...");
        return Optional.empty();
    }
}
