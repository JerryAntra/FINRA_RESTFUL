package com.FINRA.dao;

import com.FINRA.entity.*;

import java.util.List;

public interface FileMetaDataDAO {
    void save(FileMetaData metaData);

    FileMetaData findById(Integer id);

    List<FileMetaData> loadAllFiles();
}
