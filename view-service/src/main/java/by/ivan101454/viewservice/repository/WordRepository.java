package by.ivan101454.viewservice.repository;

import by.ivan101454.viewservice.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WordRepository extends JpaRepository<Word, UUID> {

    Word findBySlug(String slug);
}
