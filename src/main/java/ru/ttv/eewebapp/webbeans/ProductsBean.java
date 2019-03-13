package ru.ttv.eewebapp.webbeans;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import ru.ttv.eewebapp.model.Product;
import ru.ttv.eewebapp.repository.ProductRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Collection;

@ManagedBean(name = "products")
@SessionScoped
public class ProductsBean {

    @Inject
    private ProductRepository productRepository;

    // наличие такого поля для хранения текущего элемента является стандартным для JSF
    private Product product;

    public String getId() {
        return product.getId();
    }

    public void setId(String id) {
        product.setId(id);
    }

    public String getName() {
        return product.getName();
    }

    public void setName(String name) {
        product.setName(name);
    }

    public int getPrice() {
        return product.getPrice();
    }

    public void setPrice(int price) {
        product.setPrice(price);
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Collection<Product> getProductList() {
        return productRepository.getAll();
    }

    public String editAction(Product product) {
        this.product = product; // сохраняем продукт для редактирования
        return "/product.xhtml?faces-redirect=true"; // возвращаем адрес страницы на которую переходим для редактирования
    }

    public void deleteAction(Product product) {
        productRepository.delete(product);
    }

    public String saveProduct() {
        productRepository.save(product);
        return "/index.xhtml?faces-redirect=true"; // после сохранения продукта возвращаемся на главную страницу
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((Product) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Product) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        // Add one new car to the table:
        Product productAdd = new Product("","title",0);
        productRepository.add(productAdd);
        FacesMessage msg = new FacesMessage("New Car added", productAdd.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
