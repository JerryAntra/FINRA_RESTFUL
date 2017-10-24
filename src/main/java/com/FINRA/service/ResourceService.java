package com.FINRA.service;

import com.FINRA.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ResourceService {
    void save(MultipartFile multipartFile, FileMetaData metaData) throws IOException;

    FileMetaData findFileById(Integer id);

    List<FileMetaData> loadAllFiles();
}
