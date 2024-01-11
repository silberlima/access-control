package com.slmtecnologia.service.core;

import com.slmtecnologia.config.FileStorageConfig;
import com.slmtecnologia.controller.exceptions.FileStorageException;
import com.slmtecnologia.controller.exceptions.MyFileNotFoundException;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService  {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService (FileStorageConfig fileStorageConfig){
        Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
        this.fileStorageLocation = path;
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new FileStorageException("Could not create the directory!", e);
        }
    }

    public String storeFile(MultipartFile file){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(filename.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence "+filename);
            }
            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch (Exception e){
            throw new FileStorageException("Could not store file!", e);
        }
    }

    public Resource loadFileAsResource(String filename){
        try{
            Path filPath = this.fileStorageLocation.resolve(filename);
            Resource resource = new UrlResource(filPath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                throw new MyFileNotFoundException("File not found!");
            }
        }catch (Exception e){
            throw new MyFileNotFoundException("File not found!", e);
        }
    }



}
