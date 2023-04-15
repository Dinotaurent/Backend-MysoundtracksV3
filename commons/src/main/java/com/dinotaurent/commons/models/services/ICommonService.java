package com.dinotaurent.commons.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICommonService<E> {

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    Optional<E> findById(Long id);

    E save(E entity);

    void deleteById(Long id);
}
