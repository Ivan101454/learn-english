package by.ivan101454.viewservice.service;

import by.ivan101454.viewservice.config.StorageProperties;
import by.ivan101454.viewservice.exception.StorageException;
import by.ivan101454.viewservice.exception.StorageFileNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.NoSuchElementException;

@Service
@Log4j2
public class DefaultStorageService implements StorageService {

    private final Path rootLocation;
    private final String defaultAudioFileName;

    public DefaultStorageService(StorageProperties storageProperties) {
        if (storageProperties.getLocation().trim().length() == 0) {
            throw new StorageException("File upload location is empty");
        }
        this.rootLocation = Paths.get(storageProperties.getLocation());
        this.defaultAudioFileName = storageProperties.getDefaultName();
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

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Couldn't read this file: " + fileName
                );
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Couldn't read this file: " + fileName, e);
        }
    }

    @Override
    public void name(String fileName) {
        log.info("Storing file: " + defaultAudioFileName);
        log.info("Storing file: " + fileName);
        String extension = ".webm";
        Path oldName = load(defaultAudioFileName + extension);
        Path newName = load(fileName + extension);
        log.info("Old name: " + oldName);
        log.info("New name: " + newName);
        log.info("Old name: " + Files.exists(oldName));

        if (Files.exists(oldName)) {
            try {
                Files.move(oldName, newName, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log.info("Couldn't move file: {} {}", fileName, e.getMessage());
            }
        } else {
            throw new NoSuchElementException("File does not exist: " + fileName);
        }
    }
}
