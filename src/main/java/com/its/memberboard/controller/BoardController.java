package com.its.memberboard.controller;

import com.its.memberboard.common.PagingConst;
import com.its.memberboard.dto.BoardDTO;
import com.its.memberboard.dto.CommentDTO;
import com.its.memberboard.service.BoardService;
import com.its.memberboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final CommentService commentService;
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

    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.paging(pageable);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/boardPages/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id,Model model){
        BoardDTO boardDTO = boardService.findById(id);
        boardService.hits(id);
        model.addAttribute("boardDTO",boardDTO);
        List<CommentDTO> commentDTOList = commentService.findByBoardId(id);
        model.addAttribute("commentDTOList",commentDTOList);
        return "/boardPages/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id , Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardDTO", boardDTO);
        return "/boardPages/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.update(boardDTO);
        return "redirect:/board";
    }

    @GetMapping("/search")
    public String search(@RequestParam("q") String q, @RequestParam("choice") Long choice , Model model,
                         @PageableDefault(page = 1) Pageable pageable){

        Page<BoardDTO>boardList = boardService.search(q,choice,pageable);
        model.addAttribute("boardDTOList",boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("q",q);
        model.addAttribute("choice",choice);
        return "/boardPages/search";
    }

}
