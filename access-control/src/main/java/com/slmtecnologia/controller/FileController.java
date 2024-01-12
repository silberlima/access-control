package com.slmtecnologia.controller;

import com.slmtecnologia.model.dto.UploadFileResponse;
import com.slmtecnologia.service.core.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Tag(name = "File Endpoint")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private Logger logger = Logger.getLogger(FileController.class.getName());

    private final FileStorageService service;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@NotNull @Valid @RequestParam("file") MultipartFile file){
        var filename = service.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/file/downloadFile/")
                .path(filename)
                .toUriString();
        return new UploadFileResponse(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFile")
    public List<UploadFileResponse> uploadMultipleFile(@RequestParam("files") MultipartFile[] files){

        return Arrays.asList(files)
                .stream()
                .map(this::uploadFile)
                .toList();

    }

    @GetMapping("/downloadFile/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request){

        Resource resource = service.loadFileAsResource(filename);
        String contentType = "";

        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        }catch (Exception e){
            logger.info("Could not determine file type!");
        }

        if(contentType.isBlank()){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\""+resource.getFilename()+ "\"")
                .body(resource);
    }

}
