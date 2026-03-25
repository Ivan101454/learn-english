package by.ivan101454.viewservice.mapper;

import by.ivan101454.viewservice.dto.WordDto;
import by.ivan101454.viewservice.entity.Word;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WordMapper {

    WordMapper INSTANCE = Mappers.getMapper(WordMapper.class );

    WordDto wordToWordDto(Word word);
}
