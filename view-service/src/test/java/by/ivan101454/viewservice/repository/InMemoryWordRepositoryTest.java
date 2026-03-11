package by.ivan101454.viewservice.repository;

import by.ivan101454.viewservice.entity.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryWordRepositoryTest {

    private InMemoryWordRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryWordRepository();
    }

    @Test
    void shouldFindWordBySlug() {
        //given
        String slug = "flex";

        //when
        Optional<Word> findWord = repository.findBySlug(slug);

        //then
        assertTrue(findWord.isPresent());

    }

    @Test
    void saveWordWithTheNewWordPlug_shouldAssignSlug() {

        //given
        Word newWord = new Word(
                UUID.randomUUID(), "hello", "hello", "привет", null
        );
        String checkWord = "hello-3";

        //when
        repository.save(newWord);
        repository.save(newWord);
        repository.save(newWord);

        //then
        assertTrue(repository.findBySlug(checkWord).isPresent());

    }
}