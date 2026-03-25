package by.ivan101454.viewservice.repository;

import by.ivan101454.viewservice.entity.WordsCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WordsCollectionRepository extends JpaRepository<WordsCollection, UUID> {
}
