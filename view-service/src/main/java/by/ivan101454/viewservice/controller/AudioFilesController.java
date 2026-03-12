package by.ivan101454.viewservice.controller;

import by.ivan101454.viewservice.service.DefaultStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {

        Resource file = defaultStorageService.loadAsResource(fileName);

        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; fileName=\"" + file.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "audio/webm")
                .body(file);

    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAudioFiles(
            @RequestParam("audioFile") MultipartFile file
    ) {
        defaultStorageService.store(file);
        return ResponseEntity.ok().build();
    }

}
