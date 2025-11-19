package com.iucosoft.mylinksspringboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface OperationIntf <T extends Serializable, V extends Serializable>{

    T findById(final V id);

    List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAllPaginated(Pageable pageable);

    T save(final T entity);

    List<T> save(final List<T> entities);

    Set<T> save(final Set<T> entities);

    void delete(final T entity);

    void delete(final List<T> entities);

    void deleteById(final V id);

}
