package com.FINRA.controller;

import com.FINRA.entity.FileMetaData;
import com.FINRA.exception.BadInputException;
import com.FINRA.pojo.FileMetaDataVO;
import com.FINRA.utiliy.FileUploadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.FINRA.service.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class FileController {

    @Autowired
    private  ResourceService resourceService;

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadFile(@PathParam(value = "file") MultipartFile file) throws BadInputException, IllegalStateException, IOException{
        if(file == null)
            throw new BadInputException("empty input");
        resourceService.save(file, FileUploadUtility.convertFile(file));
        return new ResponseEntity(HttpStatus.OK);
    }

    
    @GetMapping(value = "/files")
    @ResponseBody
    public FileMetaDataVO loadAllFiles() {
        FileMetaDataVO fileVO = new FileMetaDataVO();
        fileVO.setFileList(resourceService.loadAllFiles());
        return fileVO;
    }

    
    @GetMapping(value = "/files/{id}")
    @ResponseBody
    public FileMetaDataVO loadFileById(@PathVariable("id") Integer id) {
        FileMetaDataVO fileVO = new FileMetaDataVO();
        fileVO.setFileList(new ArrayList<FileMetaData>(Arrays.asList(resourceService.findFileById(id))));
        return fileVO;
    }


    @ExceptionHandler(value = BadInputException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String showError(BadInputException e) {
        return e.getMessage();
    }
}
