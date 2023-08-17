package com.example.filesafe.services;

import com.example.filesafe.models.Folder;
import com.example.filesafe.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService {
    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository){
        this.folderRepository = folderRepository;
    }

    public Folder getFolderById(Long folderId) {
        return folderRepository.findById(folderId).orElse(null);
    }

}
