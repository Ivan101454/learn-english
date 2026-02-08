package by.ivan101454.viewservice.service;

import by.ivan101454.viewservice.entity.Word;
import by.ivan101454.viewservice.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultWordService implements WordService {

    private final WordRepository wordRepository;

    @Override
    public List<Word> findAllWords() {
        return wordRepository.findAll();
    }
}
