package com.lyhcoding.myblog.dto.board;

import com.lyhcoding.myblog.model.board.Board;
import com.lyhcoding.myblog.model.user.User;
import lombok.Getter;
import lombok.Setter;

public class BoardRequest {

    @Getter @Setter
    public static class SaveInDTO{
        private String title;
        private String content;

        public Board toEntity(User user, String thumbnail){
            return Board.builder()
                    .user(user)
                    .title(title)
                    .content(content)
                    .thumbnail(thumbnail)
                    .build();
        }
    }
}
