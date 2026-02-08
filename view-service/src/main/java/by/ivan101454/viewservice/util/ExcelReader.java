package by.ivan101454.viewservice.util;

import by.ivan101454.viewservice.entity.Word;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ExcelReader {

    public List<Word> readExcelFile() {

        List<Word> words = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream("/words.xlsx");
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            var sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            for (var row : sheet) {
                // пропускаем пустые строки
                if (row == null) continue;
                // пропускаем строки с пустыми словами
                if (row.getCell(0) == null) continue;

                Cell cell0 = row.getCell(0);
                Cell cell1 = row.getCell(1);
                Cell cell2 = row.getCell(2);
                Word word = new Word();

                if (cell0 != null) {
                    word.setWord(formatter.formatCellValue(cell0));
                    word.setSlugWord(formatter.formatCellValue(cell0));
                }
                if (cell1 != null) {
                    word.setDefinition(formatter.formatCellValue(cell1));
                }
                if (cell2 != null) {
                    word.setNote(formatter.formatCellValue(cell2));
                }
                word.setWordId(UUID.randomUUID());
                words.add(word);
                log.info(words.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }
}
