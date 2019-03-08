package ru.ttv.eewebapp.repository;

import ru.ttv.eewebapp.model.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Named
@ApplicationScoped
public class OrderRepository {

    private Map<String, Order> orderMap = new LinkedHashMap<>();

    public OrderRepository() {
        this.add(new Order("1", "Vasya's order"));
        this.add(new Order("2", "Olya's order"));
        this.add(new Order("3", "Dima's order"));
        this.add(new Order("4", "Max's order"));
    }

    public Collection<Order> getAll() {
        return orderMap.values();
    }

    public Order getById(String id) {
        return orderMap.get(id);
    }

    public void add(Order order) {
        orderMap.put(order.getId(), order);
    }

    public void save(Order order) {
        orderMap.put(order.getId(), order);
    }

    public void delete(Order order) {
        orderMap.remove(order.getId());
    }
}
