package com.chrisvzimager.chrisvzimager.services;

/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file, String user_id, String album_name);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
