package com.example.filesafe.repositories;

import com.example.filesafe.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    Folder findFolderById(long Id);
}
