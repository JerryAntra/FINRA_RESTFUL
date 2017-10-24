package com.FINRA;

import com.FINRA.dao.FileMetaDataDAO;
import com.FINRA.dao.FileMetaDataDAOImpl;
import com.FINRA.entity.FileMetaData;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.sql.DriverManager;

@SpringBootTest
public class FileMetaDataDBConnectionTest {


    EntityManager em;

    @Test
    @Transactional
    @Rollback(true)
    public void saveDAOTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "jdbc:derby:target/derbydb;user=;password=");
        em = emf.createEntityManager();
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setName("testFile");
        fileMetaData.setPath("testPath");
        fileMetaData.setSize(0);
        em.persist(fileMetaData);
        em.flush();
        Integer id = fileMetaData.getId();
        em.remove(fileMetaData);
        Assert.assertTrue(id != null);
    }

}
