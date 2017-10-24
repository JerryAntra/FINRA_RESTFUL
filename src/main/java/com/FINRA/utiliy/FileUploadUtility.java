package com.FINRA.utiliy;

import com.FINRA.entity.FileMetaData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtility {

    static final String FILE_PATH = System.getProperty("user.dir");

    private FileUploadUtility() {

    }
    public static FileMetaData convertFile(MultipartFile file) {
        FileMetaData fileData = new FileMetaData();
        fileData.setName(file.getOriginalFilename());
        fileData.setPath((new File(FILE_PATH)).getAbsolutePath() + "/files/" + file.getOriginalFilename());
        fileData.setSize(file.getSize());
        return fileData;
    }

    public static void saveFile(MultipartFile file, FileMetaData metaData) throws IOException{
        file.transferTo(new File(metaData.getPath()));
    }
}
