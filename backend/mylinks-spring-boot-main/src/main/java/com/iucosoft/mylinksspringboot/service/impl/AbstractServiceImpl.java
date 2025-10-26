package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.service.OperationIntf;

import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Transactional
public abstract class AbstractServiceImpl <T extends Serializable, ID extends Serializable> implements OperationIntf<T, ID> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    @Transactional(readOnly = true)
    public T findById(final ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getRepository().findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return Lists.newArrayList(getRepository().findAll(sort));
    }

    @Override
    public Page<T> findAllPaginated(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public T save(final T entity) {
        return getRepository().save(entity);
    }

    @Override
    public List<T> save(final List<T> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    public Set<T> save(final Set<T> entities) {
        return new LinkedHashSet<>(getRepository().saveAll(entities));
    }

    @Override
    public void delete(final T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void delete(List<T> entities) {
        getRepository().deleteAll(entities);
    }

    @Override
    public void deleteById(final ID entityId) {
        getRepository().deleteById(entityId);
    }
}
