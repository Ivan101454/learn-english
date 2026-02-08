package by.ivan101454.viewservice.controller;

import by.ivan101454.viewservice.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/words")
public class WordsController {

    private final WordService wordService;

    @GetMapping("/list")
    public String getWordsList(Model model) {
        model.addAttribute("words", wordService.findAllWords());
        return "catalogue/words/list";
    }
}
