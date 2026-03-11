package by.ivan101454.viewservice.controller;

import by.ivan101454.viewservice.entity.Word;
import by.ivan101454.viewservice.payload.NewWordPayload;
import by.ivan101454.viewservice.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/create")
    public String getNewWordPage() {
        return "catalogue/words/new_word";
    }

    @PostMapping("/create")
    public String createWord(NewWordPayload newWordPayload) {
        Word word = wordService.createWord(
                newWordPayload.word(), newWordPayload.definition(),
                newWordPayload.note()
        );
        return "redirect:/catalogue/words/%s".formatted(word.getSlugWord());
    }

    @GetMapping("/{slugWord}")
    public String getWord(@PathVariable("slugWord") String wordSlug, Model model) {
        model.addAttribute("word", wordService.findWordBySlugWord(wordSlug).orElseThrow());
        return "catalogue/words/word";
    }

}
