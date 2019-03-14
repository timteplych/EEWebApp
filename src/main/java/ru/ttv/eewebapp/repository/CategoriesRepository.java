package ru.ttv.eewebapp.repository;

import ru.ttv.eewebapp.model.Category;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Collection;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CategoriesRepository extends AbstractRepository<Category> {

    @Override
    public Category getById(long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Category> getAll() {
        return entityManager.createQuery("select c from Category c").getResultList();
    }
}
