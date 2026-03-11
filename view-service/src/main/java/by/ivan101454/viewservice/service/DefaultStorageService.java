package by.ivan101454.viewservice.service;

import by.ivan101454.viewservice.config.StorageProperties;
import by.ivan101454.viewservice.exception.StorageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DefaultStorageService implements StorageService {

    private final Path rootLocation;

    public DefaultStorageService(StorageProperties storageProperties) {
        if (storageProperties.getLocation().trim().length() == 0) {
            throw new StorageException("File upload location is empty");
        }
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void store(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException(
                        "Cannot store files with different locations"
                );
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }
}
