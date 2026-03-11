package by.ivan101454.viewservice.repository;

import by.ivan101454.viewservice.entity.Word;

import java.util.List;
import java.util.Optional;

public interface WordRepository {

    List<Word> findAll();

    Word save(Word word);

    Optional<Word> findBySlug(String wordSlug);
}
