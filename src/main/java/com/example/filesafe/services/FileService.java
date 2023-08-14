package com.example.filesafe.services;

import com.example.filesafe.models.File;
import com.example.filesafe.models.Folder;
import com.example.filesafe.repositories.FileRepository;
import com.example.filesafe.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    private FileRepository fileRepository;
    private FolderRepository folderRepository;

    @Autowired
    public FileService(FileRepository fileRepository, FolderRepository folderRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
    }

    public void saveFile(MultipartFile multipartFile, Long folderId) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String fileType = multipartFile.getContentType();
        byte[] fileData = multipartFile.getBytes();

        Folder folder = folderRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Folder not found"));

        File file = new File(fileName, fileType, fileData, folder);
        fileRepository.save(file);
    }
    public Optional<File> getFileById(long fileId){
        return fileRepository.findById(fileId);
    }
}
