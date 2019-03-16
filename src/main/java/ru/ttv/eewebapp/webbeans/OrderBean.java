package ru.ttv.eewebapp.webbeans;

import org.primefaces.event.RowEditEvent;
import ru.ttv.eewebapp.model.Order;
import ru.ttv.eewebapp.repository.OrderRepository;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named("orders")
@Stateless
public class OrderBean {

    @Inject
    OrderRepository orderRepository;

    private Order order;

    public long getId() {
        return order.getId();
    }

    public void setId(long id) {
        order.setId(id);
    }

    public String getDescription() {
        return order.getDescription();
    }

    public void setDescription(String description) {
        order.setDescription(description);
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Collection<Order> getOrderList() {
        return orderRepository.getAll();
    }

    public String editAction(Order order) {
        this.order = order; // сохраняем продукт для редактирования
        return "/categories.xhtml?faces-redirect=true"; // возвращаем адрес страницы на которую переходим для редактирования
    }

    public void deleteAction(Order order) {
        orderRepository.remove(order.getId());
    }

    public String saveOrder() {
        orderRepository.merge(order);
        return "/index.xhtml?faces-redirect=true"; // после сохранения продукта возвращаемся на главную страницу
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Order Edited", String.valueOf(((Order) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((Order) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        Order orderAdd = new Order();
        orderRepository.merge(orderAdd);
        FacesMessage msg = new FacesMessage("New Order added", String.valueOf(orderAdd.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
