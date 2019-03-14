package ru.ttv.eewebapp.repository;

import ru.ttv.eewebapp.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

/**
 * @author Teplykh Timofey  13.03.2019
 */
public abstract class AbstractRepository<T> {

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    protected T getEntity(TypedQuery<T> query) {
        List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public abstract T getById(long id);

    public abstract Collection<T> getAll();

    public T merge(T entity) {
        if (entity == null) {
            return null;
        }
        try {
            return entityManager.merge(entity);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void remove(long id) {
        try {
            Product attached = entityManager.find(Product.class, id);
            if (attached != null) {
                entityManager.remove(attached);
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
