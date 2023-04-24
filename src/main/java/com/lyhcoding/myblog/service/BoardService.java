package com.lyhcoding.myblog.service;

import com.lyhcoding.myblog.dto.board.BoardRequest;
import com.lyhcoding.myblog.model.board.Board;
import com.lyhcoding.myblog.model.board.BoardQueryRepository;
import com.lyhcoding.myblog.model.board.BoardRepository;
import com.lyhcoding.myblog.model.user.User;
import com.lyhcoding.myblog.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardQueryRepository boardQueryRepository;

    public void 글쓰기(BoardRequest.SaveInDTO saveInDTO, Long userId){
        try {
            // 1. 유저 존재 확인
            User userPS = userRepository.findById(userId).orElseThrow(
                    () -> new RuntimeException("유저를 찾을수 없습니다")
            );

            // 2.게시글 쓰기
            boardRepository.save(saveInDTO.toEntity(userPS));
        }catch (Exception e){
            throw new RuntimeException("글쓰기 실패 : "+e.getMessage());
        }
    }

    @Transactional(readOnly = true) // 변경감지 하지마, 고립성(REPEATABLE READ)
    public Page<Board> 글목록보기(int page) { // CSR은 DTO로 변경해서 돌려줘야 함. 지금은 SSR
        // 1. 모든 전략은 Lazy : 이유는 필요할때만 가져오라고
        // 2. 필요할 때는 직접 fetch join을 사용해라
        return boardQueryRepository.findAll(page);
    }
}
