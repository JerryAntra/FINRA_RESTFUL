package com.FINRA.dao;

import com.FINRA.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FileMetaDataDAOImpl implements FileMetaDataDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(FileMetaData metaData) {
        em.persist(metaData);
    }

    @Override
    public FileMetaData findById(Integer id) {
        return em.find(FileMetaData.class, id);
    }

    @Override
    public List<FileMetaData> loadAllFiles() {
        return em.createQuery("select f from FileMetaData f", FileMetaData.class).getResultList();
    }
}
