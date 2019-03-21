package ru.ttv.eewebapp.webbeans;

import org.primefaces.event.RowEditEvent;
import ru.ttv.eewebapp.model.Category;
import ru.ttv.eewebapp.model.Product;
import ru.ttv.eewebapp.repository.CategoriesRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;


@Named("categories")
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CategoriesBean {

    @Inject
    private CategoriesRepository categoryRepository;

    // наличие такого поля для хранения текущего элемента является стандартным для JSF
    private Category category;

    public Long getId() {
        return category.getId();
    }

    public void setId(long id) {
        category.setId(id);
    }

    public String getName() {
        return category.getName();
    }

    public void setName(String name) {
        category.setName(name);
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<Category> getCategoryList() {
        return categoryRepository.getAll();
    }

    public String editAction(Category category) {
        this.category = category; // сохраняем продукт для редактирования
        return "/categories.xhtml?faces-redirect=true"; // возвращаем адрес страницы на которую переходим для редактирования
    }

    public void deleteAction(Category category) {
        categoryRepository.remove(category.getId());
    }

    public String saveProduct() {
        categoryRepository.merge(category);
        return "/index.xhtml?faces-redirect=true"; // после сохранения продукта возвращаемся на главную страницу
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", String.valueOf(((Product) event.getObject()).getId()) );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((Product) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        Category categoryAdd = new Category("title");
        categoryRepository.merge(categoryAdd);
        FacesMessage msg = new FacesMessage("New Category added", String.valueOf(categoryAdd.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
