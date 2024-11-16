package br.com.sysaba.core.commons.service;

import br.com.sysaba.core.exception.DataBaseException;
import br.com.sysaba.core.util.MapperUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface GenericService<T, UUID> {

    JpaRepository<T, UUID> getRepository();

    @Transactional(readOnly = true)
    default T findById(final UUID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new DataBaseException("Erro ao localizar o registro " + id));
    }

    default void delete(final UUID id) {
        findById(id);
        getRepository().deleteById(id);
    }

    @Transactional
    default T save(final T entity) {
        beforeSave();
        return getRepository().save(entity);
    }

    @Transactional
    default T update(final UUID id, final T entity) {
        T result = findById(id);
        BeanUtils.copyProperties(entity, result, "treinamentoId");
        return save(result);
    }

    @Transactional(readOnly = true)
    default Page<T> findAll(final Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    default void beforeSave() {
    }
}
