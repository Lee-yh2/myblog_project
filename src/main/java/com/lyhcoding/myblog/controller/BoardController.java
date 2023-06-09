package com.lyhcoding.myblog.controller;

import com.lyhcoding.myblog.core.auth.MyUserDetails;
import com.lyhcoding.myblog.dto.board.BoardRequest;
import com.lyhcoding.myblog.model.board.Board;
import com.lyhcoding.myblog.model.board.BoardRepository;
import com.lyhcoding.myblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // RestAPI 주소 설계 규칙에서 자원에는 복수를 붙인다. boards 정석
    @GetMapping({"/", "/board"})
    public String main(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            Model model
    ){
        Page<Board> boardPG = boardService.글목록보기(page, keyword);
        model.addAttribute("boardPG", boardPG);
        model.addAttribute("keyword", keyword);
        return "board/main";
    }

    @GetMapping("/s/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @PostMapping("/s/board/save")
    public String save(BoardRequest.SaveInDTO saveInDTO, @AuthenticationPrincipal MyUserDetails myUserDetails){
        boardService.글쓰기(saveInDTO, myUserDetails.getUser().getId());
        return "redirect:/";
    }

    @GetMapping( "/board/{id}")
    public String detail(@PathVariable Long id, Model model){
        Board board = boardService.게시글상세보기(id);
        model.addAttribute("board", board);
        return "board/detail"; // RequestDispatcher => request 덮어쓰기 기술
    }

    @PostMapping("/s/board/{id}/delete")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        boardService.게시글삭제(id, myUserDetails.getUser().getId());
        return "redirect:/";
    }
}
