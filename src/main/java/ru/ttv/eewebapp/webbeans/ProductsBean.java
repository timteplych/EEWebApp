package ru.ttv.eewebapp.webbeans;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import ru.ttv.eewebapp.model.Product;
import ru.ttv.eewebapp.repository.CategoriesRepository;
import ru.ttv.eewebapp.repository.ProductRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collection;

@ManagedBean(name = "products")
@SessionScoped
public class ProductsBean {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoriesRepository categoriesRepository;

    // наличие такого поля для хранения текущего элемента является стандартным для JSF
    private Product product;

    public long getId() {
        return product.getId();
    }

    public void setId(long id) {
        product.setId(id);
    }

    public String getName() {
        return product.getName();
    }

    public void setName(String name) {
        product.setName(name);
    }

    public BigDecimal getPrice() {
        return product.getPrice();
    }

    public void setPrice(BigDecimal price) {
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
        productRepository.remove(product.getId());
    }

    public String saveProduct() {
        productRepository.merge(product);
        return "/index.xhtml?faces-redirect=true"; // после сохранения продукта возвращаемся на главную страницу
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", String.valueOf(((Product) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((Product) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        // Add one new car to the table:
        Product productAdd = new Product();
        productAdd.setPrice(new BigDecimal(0));
        productAdd.setName("title");
        productAdd.setCategory(categoriesRepository.getById(0));
        productRepository.merge(productAdd);
        FacesMessage msg = new FacesMessage("New Car added", String.valueOf(productAdd.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
