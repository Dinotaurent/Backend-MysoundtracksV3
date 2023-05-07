package com.dinotaurent.commons.models.services;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CrudAndSortingRepository<T,ID> extends CrudRepository<T,ID>, PagingAndSortingRepository<T,ID> {

    List<T> findByNombreContaining(String termino);
}
