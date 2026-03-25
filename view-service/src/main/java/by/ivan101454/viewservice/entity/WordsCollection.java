package by.ivan101454.viewservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "collection")
@Table(name = "collection", schema = "english")
public class WordsCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private UUID collectionId;
    @Column(name = "collection_name")
    private String collectionName;

    @ManyToMany
    @JoinTable(
            name = "words_collections",
            joinColumns = @JoinColumn(name = "collectionId"),
            inverseJoinColumns = @JoinColumn(name = "wordId")
    )
    private List<Word> words;

}
