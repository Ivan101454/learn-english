package by.ivan101454.viewservice.repository;

import by.ivan101454.viewservice.entity.Word;
import by.ivan101454.viewservice.util.ExcelReader;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
}
