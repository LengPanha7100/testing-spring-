package com.example.restapi.service.serviceImp;
import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.AppFile;
import com.example.restapi.service.AppFileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class AppFileServiceImp implements AppFileService {

    private static final Path PATH = Paths.get("src/main/resources/images");

    @Override
    public AppFile upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if (fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".jpeg")){
            fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
            if (!Files.exists(PATH)){
                Files.createDirectories(PATH);
            }
            Files.copy(file.getInputStream(), PATH.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return new AppFile(fileName, file.getContentType(), file.getSize());
        }
        throw new BadRequestException("File must be contain jpg, png, jpeg");
    }

    @Override
    public Resource findFileByFileName(String fileName) throws IOException {
        if (!(fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".jpeg"))){
            throw new BadRequestException("File must be contain jpg, png, jpeg");
        }
        Path path = Paths.get("src/main/resources/images/" + fileName);
        if (Files.notExists(path)){
            throw new BadRequestException("File not founded");
        }
        return new ByteArrayResource(Files.readAllBytes(path));
    }
}
