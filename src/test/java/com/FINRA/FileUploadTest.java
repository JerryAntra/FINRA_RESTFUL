package com.FINRA;

import com.FINRA.dao.FileMetaDataDAO;
import com.FINRA.entity.FileMetaData;
import com.FINRA.pojo.FileMetaDataVO;
import com.FINRA.service.ResourceService;
import com.FINRA.service.ResourceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FileUploadTest {

    @Autowired
    private WebApplicationContext wac;


    @Mock
    FileMetaDataDAO mockDAO;

    @InjectMocks
    ResourceServiceImpl service;

    @Autowired
    ResourceServiceImpl resourceService;

    @Autowired
    FileMetaDataDAO dao;

    private MockMvc mockmvc;

    private static  FileMetaData file;

    @Before
    public void iniMockMvc() {
        this.mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @BeforeClass
    public static void ini() {
        file = new FileMetaData();
        file.setSize(1);
        file.setPath("testPath");
        file.setName("testName");
    }

    @Test
    public void LoadFilesByIdTest() {
        when(mockDAO.findById(1)).thenReturn(file);
        Assert.assertTrue(service.findFileById(1) != null);
    }

    @Test
    public void LoadFilesTest() {
        when(mockDAO.loadAllFiles()).thenReturn(Arrays.asList(file));
        Assert.assertTrue(service.loadAllFiles().size() != 0);
    }

    @Test
    @Transactional
    public void FileDAOSaveTest() {
        dao.save(file);
        Assert.assertTrue(file.getId() != null);
    }

    @Test
    public void FileControllerTest() throws Exception{
        List<FileMetaData> list = new ArrayList<FileMetaData>();
        list.add(new FileMetaData());
        when(service.loadAllFiles()).thenReturn(list);
        FileMetaDataVO data = new FileMetaDataVO();
        data.setFileList(list);
        data.setFileList(new ArrayList<FileMetaData>());
        mockmvc.perform(get("/files"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(data)));
    }
}
