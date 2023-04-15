package com.dinotaurent.commons.models.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CrudAndSortingRepository<T,ID> extends CrudRepository<T,ID>, PagingAndSortingRepository<T,ID> {
}
