package by.ivan101454.viewservice.repository;

import by.ivan101454.viewservice.entity.Word;
import by.ivan101454.viewservice.util.ExcelReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryWordRepository implements WordRepository {

    private final List<Word> words = Collections.synchronizedList(new LinkedList<>());

    public InMemoryWordRepository() {
        ExcelReader excelReader = new ExcelReader();
        words.addAll(excelReader.readExcelFile());
    }

    @Override
    public List<Word> findAll() {
        return Collections.unmodifiableList(words);
    }

    @Override
    public Word save(Word word) {
        String slugWord = word.getSlugWord();
        String newSlugWord = slugWord;
        long count = 1;
        count = words.stream().filter(w -> Objects.equals(w.getSlugWord()
                        .replaceAll("-\\d+$", ""), slugWord))
                .count();

        if (count > 1) {
            newSlugWord = slugWord + "-" + (count + 1);

        }

        word.setSlugWord(newSlugWord);
        words.add(word);
        return word;
    }

    @Override
    public Optional<Word> findBySlug(String wordSlug) {
        return words.stream().filter(w -> Objects.equals(w.getSlugWord(), wordSlug)).findFirst();
    }
}
