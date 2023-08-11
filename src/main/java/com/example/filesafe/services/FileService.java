package com.example.filesafe.services;

import com.example.filesafe.models.File;
import com.example.filesafe.models.Folder;
import com.example.filesafe.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public void saveFile(MultipartFile multipartFile, Folder folder) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String fileType = multipartFile.getContentType();
        byte[] fileData = multipartFile.getBytes();


        File file = new File(fileName, fileType, fileData, folder);
        fileRepository.save(file);
    }
    public Optional<File> getFileById(long fileId){
        return fileRepository.findById(fileId);
    }
}
