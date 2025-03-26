package com.example.restapi.controller;

import com.example.restapi.service.AppFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/file")
public class AppFileController {
    private final AppFileService appFileService;

    public AppFileController(AppFileService appFileService) {
        this.appFileService = appFileService;
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(appFileService.upload(file));
    }

    @GetMapping
    public ResponseEntity<?> findFileByFileName(@RequestParam String fileName) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(appFileService.findFileByFileName(fileName));
    }






}
