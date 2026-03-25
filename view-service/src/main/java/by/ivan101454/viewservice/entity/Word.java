package by.ivan101454.viewservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "word")
@Table(name = "word", schema = "english")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private UUID wordId;
    @Column(name = "word")
    private String word;
    @Column(name = "slug_word", unique = true)
    private String slugWord;
    @Column(name = "definition")
    private String definition;
    @Column(name = "note")
    private String note;
    @ManyToMany(mappedBy = "words")
    private List<WordsCollection> collections;

    public void addToCollection(WordsCollection collection) {
        collections.add(collection);
    }

    public void removeFromCollection(WordsCollection collection) {
        collections.remove(collection);
    }
}
