package com.dinotaurent.commons.models.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class CommonServiceImpl<E, R extends CrudAndSortingRepository<E, Long>> implements ICommonService<E> {

    @Autowired
    protected R dao;
    @Override
    @Transactional
    public List<E> findAll() {
        return (List<E>) dao.findAll();
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    @Transactional
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
}
