package ru.ttv.eewebapp.repository;

import ru.ttv.eewebapp.model.Order;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OrderRepository extends AbstractRepository<Order> {

    @Override
    public Order getById(long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public Collection<Order> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> from = query.from(Order.class);
        query.select(from);
        return entityManager.createQuery(query).getResultList();
    }
}
