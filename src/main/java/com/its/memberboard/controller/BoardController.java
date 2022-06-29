package com.its.memberboard.controller;

import com.its.memberboard.dto.BoardDTO;
import com.its.memberboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/")
    public String main(){
        return "/boardPages/main";
    }
    @GetMapping("/save")
    public String saveForm(){
        return "/boardPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.save(boardDTO);
        return "/boardPages/main";
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardDTOList",boardDTOList);
        return "/boardPages/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id,Model model){
        BoardDTO boardDTO = boardService.findById(id);
        boardService.hits(id);
        model.addAttribute("boardDTO",boardDTO);
        return "/boardPages/detail";
    }
}
