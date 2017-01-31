package com.jjambbongg.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jjambbongg.springmvc.model.Board;
import com.jjambbongg.springmvc.service.BoardService;
import com.jjambbongg.springmvc.util.PageNavigator;

@Controller
@RequestMapping("/")
@PropertySource(value={"classpath:board.properties"})
public class AppController {
	
	@Autowired
	BoardService service;
	
	@Autowired
	MessageSource messageSource;

	@Value("#{'${board.rowNumPerPage}'}")
	private int rowNumPerPageList;
	
	@RequestMapping(value={"/", "/list"}, method=RequestMethod.GET)
	public String listBoard(@RequestParam (value="pageNum", defaultValue = "1") String pageNum, ModelMap model) {
		List<Board> board = service.findAllList(rowNumPerPageList, Integer.parseInt(pageNum));
		int totalCount = service.totalCount();
		String pageStr = new PageNavigator().getPageNavigator(totalCount, rowNumPerPageList, rowNumPerPageList, Integer.parseInt(pageNum));
		model.addAttribute("board", board);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageStr", pageStr);
		model.addAttribute("pageNum", Integer.parseInt(pageNum));
		model.addAttribute("rowNum", rowNumPerPageList);
		return "list";
	}
	
	@RequestMapping(value={"/new"}, method=RequestMethod.GET)
	public String newBoard(ModelMap model) {
		Board board = new Board();
		model.addAttribute("board", board);
		model.addAttribute("edit", false);
		return "write";
	}
	
	@RequestMapping(value={"/new"}, method=RequestMethod.POST)
	public String saveBoard(@Valid Board board, BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			return "write";
		}
		
		if(board.getContents()=="") {
			FieldError contentsError = new FieldError("board", "contents", messageSource.getMessage("NotNull.board.contents", new String[]{board.getContents()}, Locale.getDefault()));
			result.addError(contentsError);
			return "write";
		}
		
		service.saveBoard(board);
		model.addAttribute("success",  "Registered successfully");
        return "success";
	}
	
	@RequestMapping(value={"/edit-{no}-board-{pageNum}"}, method=RequestMethod.GET)
	public String editBoard(@PathVariable int no, 
							@PathVariable String pageNum, 
							ModelMap model) {
		if(pageNum==null || pageNum=="") {
			pageNum = "1";
		}
        Board board = service.findBoardByNo(no);
        model.addAttribute("board", board);
        model.addAttribute("edit", true);
        model.addAttribute("pageNum", pageNum);
        return "write";		
	}
	
	@RequestMapping(value={"/edit-{no}-board-{pageNum}"}, method=RequestMethod.POST)
	public String updateBoard(@Valid Board board, BindingResult result, ModelMap model,
							  @PathVariable int no, @PathVariable String pageNum) {
		if(result.hasErrors()) {
			return "write";
		}
		if(pageNum==null || pageNum=="") {
			pageNum = "1";
		}
		service.updateBoard(board);
		model.addAttribute("success", "Modified successfully");
		model.addAttribute("pageNum", pageNum);
		return "success";
	}
	
	@RequestMapping(value={"/delete-{no}-board"}, method=RequestMethod.GET)
	public String deleteBoard(@PathVariable int no) {
		service.deleteBoardByNo(no);
		return "redirect:/list";
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
			return new PropertySourcesPlaceholderConfigurer();
	}
}
