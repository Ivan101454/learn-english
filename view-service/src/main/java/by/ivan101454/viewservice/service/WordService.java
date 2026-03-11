package by.ivan101454.viewservice.service;

import by.ivan101454.viewservice.entity.Word;

import java.util.List;
import java.util.Optional;

public interface WordService {

    List<Word> findAllWords();

    Word createWord(String word, String definition, String note);

    Optional<Word> findWordBySlugWord(String wordSlug);
}
