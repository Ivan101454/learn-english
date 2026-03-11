package by.ivan101454.viewservice.controller;

import by.ivan101454.viewservice.service.DefaultStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/audio")
@CrossOrigin(origins = "http://localhost:3000")
public class AudioFilesController {

    private final DefaultStorageService defaultStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAudioFiles(
            @RequestParam("audioFile") MultipartFile file
    ) {
        defaultStorageService.store(file);
        return ResponseEntity.ok().build();
    }



}
