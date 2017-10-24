package com.FINRA.pojo;

import com.FINRA.entity.FileMetaData;

import java.util.List;

public class FileMetaDataVO {
    private List<FileMetaData> FileList;

    public void setFileList(List<FileMetaData> list) {
        this.FileList = list;
    }

    public List<FileMetaData> getFileList() {
        return FileList;
    }
}
