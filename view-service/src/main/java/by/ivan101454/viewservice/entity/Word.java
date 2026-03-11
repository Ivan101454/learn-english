package by.ivan101454.viewservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    private UUID wordId;
    private String word;
    private String slugWord;
    private String definition;
    private String note;
}
