package com.iktpreobuka.elektronskidnevnik1.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

//import java.io.IOException;
import java.net.MalformedURLException;
//import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class LogController {

    private static final String LOG_FILE_PATH = "C:/Users/Vrn/OneDrive/Desktop/SPRING TOOLS/elektronski_dnevnik1/logs/spring-boot-logging.log";

    @GetMapping("/download-log")
    public ResponseEntity<Resource> downloadLogFile() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 

        Resource resource;
        try {
            Path filePath = Paths.get(LOG_FILE_PATH);
            resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}