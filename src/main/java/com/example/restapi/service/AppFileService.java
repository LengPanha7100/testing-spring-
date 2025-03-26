package com.example.restapi.service;

import com.example.restapi.model.AppFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AppFileService {
    AppFile upload(MultipartFile file) throws IOException;

    Resource findFileByFileName(String fileName) throws IOException;
}
