package com.example.filesafe.repositories;

import com.example.filesafe.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    Folder findFolderById(long Id);

}
