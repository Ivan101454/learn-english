package by.ivan101454.viewservice.controller;

import by.ivan101454.viewservice.entity.Word;
import by.ivan101454.viewservice.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/words")
@CrossOrigin(origins = "http://localhost:3000")
public class RestWordsController {

    private final WordService wordService;

    @GetMapping
    public List<Word> getWords() {
        return wordService.findAllWords();
    }

}
