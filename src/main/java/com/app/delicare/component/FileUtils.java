package com.app.delicare.component;

import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.utils.MessageString;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUtils {
    public static String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        java.nio.file.Path uploadDir = Paths.get("uploads");
        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    public static SystemResponse uploadImage(List<MultipartFile> files){
        try{
            files = files == null ? new ArrayList<MultipartFile>() : files;
            List<String>  fileImages = new ArrayList<>();
            for (MultipartFile file : files){
                if(file.getSize() == 0){
                    continue;
                }

                if(file.getSize() > 10 * 2024 * 1024){
                    return SystemResponse.builder()
                            .status(HttpStatus.PAYLOAD_TOO_LARGE.value())
                            .message(MessageString.FILE_TO_LARGE)
                            .build();
                }

                String contentType = file.getContentType();
                if(contentType == null || !contentType.startsWith("image/")){
                    return SystemResponse.builder()
                            .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                            .message(MessageString.FILE_VALIDATE_TYPE)
                            .build();
                }

                String filename = storeFile(file);
                fileImages.add(filename);
            }

            return SystemResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message(MessageString.FILE_UPLOAD_SUCCESSFULLY)
                    .data(fileImages)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static SystemResponse uploadImage(MultipartFile file){
        try{
            List<String>  fileImages = new ArrayList<>();
            if(file == null || file.getSize() == 0){
                return SystemResponse.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(MessageString.FILE_NOT_FOUND)
                        .build();
            }

            if(file.getSize() > 10 * 2024 * 1024){
                return SystemResponse.builder()
                        .status(HttpStatus.PAYLOAD_TOO_LARGE.value())
                        .message(MessageString.FILE_TO_LARGE)
                        .build();
            }

            String contentType = file.getContentType();
            if(contentType == null || !contentType.startsWith("image/")){
                return SystemResponse.builder()
                        .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .message(MessageString.FILE_VALIDATE_TYPE)
                        .build();
            }

            String filename = storeFile(file);
            fileImages.add(filename);

            return SystemResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message(MessageString.FILE_UPLOAD_SUCCESSFULLY)
                    .data(fileImages)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
