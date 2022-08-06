package com.example.demo.service.interfaces;

import com.example.demo.model.FileModel;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileService {

    FileModel create(FileModel file);
    String saveFile(String fileName, MultipartFile multipartFile) throws IOException;

    Resource getFileAsResource(String fileCode) throws IOException;
}
