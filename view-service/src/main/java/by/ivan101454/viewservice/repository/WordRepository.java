package by.ivan101454.viewservice.repository;

import by.ivan101454.viewservice.entity.Word;

import java.util.List;

public interface WordRepository {

    List<Word> findAll();
}
