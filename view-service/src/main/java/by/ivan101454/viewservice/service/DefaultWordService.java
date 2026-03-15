package by.ivan101454.viewservice.service;

import by.ivan101454.viewservice.entity.Word;
import by.ivan101454.viewservice.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultWordService implements WordService {

    private final WordRepository wordRepository;

    @Override
    public List<Word> findAllWords() {
        return wordRepository.findAll();
    }

    @Override
    public Word createWord(String word, String definition, String note) {
        return wordRepository.save(new Word(
                UUID.randomUUID(), word, word, definition, note));
    }

    @Override
    public Optional<Word> findWordBySlugWord(String wordSlug) {
        return wordRepository.findBySlug(wordSlug);
    }
}
