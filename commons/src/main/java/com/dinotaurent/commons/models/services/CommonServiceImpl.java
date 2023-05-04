package com.dinotaurent.commons.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class CommonServiceImpl<E, R extends CrudAndSortingRepository<E, Long>> implements ICommonService<E> {

    @Autowired
    protected R dao;
    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() {
        return (List<E>) dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        return dao.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

//    @Override
//    @Transactional
//    public void setFoto(byte[] foto) {
//        dao.setFoto(foto);
//    }
}
