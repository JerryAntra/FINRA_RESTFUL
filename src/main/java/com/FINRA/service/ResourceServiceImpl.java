package com.FINRA.service;

import com.FINRA.dao.*;
import com.FINRA.entity.*;
import com.FINRA.utiliy.FileUploadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final FileMetaDataDAO fileDAO;

    @Autowired
    public ResourceServiceImpl(FileMetaDataDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    @Override
    @Transactional
    public void save(MultipartFile multipartFile, FileMetaData metaData) throws IOException {
        FileUploadUtility.saveFile(multipartFile, metaData);
        fileDAO.save(metaData);
    }

    @Override
    public FileMetaData findFileById(Integer id) {
        return fileDAO.findById(id);
    }

    @Override
    public List<FileMetaData> loadAllFiles() {
        return fileDAO.loadAllFiles();
    }
}
