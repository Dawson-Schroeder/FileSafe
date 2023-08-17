package com.example.filesafe.controllers;

import com.example.filesafe.models.Folder;
import com.example.filesafe.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folders")
public class FolderController {
    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService){
        this.folderService = folderService;
    }

    @GetMapping("/{folderId}")
    public String showFilesInFolder(@PathVariable Long folderId, Model model){
        Folder folder = folderService.getFolderById(folderId);
        model.addAttribute("folder", folder);
        return "folder_files";
    }
}
