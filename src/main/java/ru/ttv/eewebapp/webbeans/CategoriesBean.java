package ru.ttv.eewebapp.webbeans;

import org.primefaces.event.RowEditEvent;
import ru.ttv.eewebapp.model.Category;
import ru.ttv.eewebapp.model.Product;
import ru.ttv.eewebapp.repository.CategoriesRepository;
import ru.ttv.eewebapp.repository.ProductRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Collection;

@ManagedBean(name = "categories")
@SessionScoped
public class CategoriesBean {
    @Inject
    private CategoriesRepository categoryRepository;

    // наличие такого поля для хранения текущего элемента является стандартным для JSF
    private Category category;

    public String getId() {
        return category.getId();
    }

    public void setId(String id) {
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
        categoryRepository.delete(category);
    }

    public String saveProduct() {
        categoryRepository.save(category);
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
        Category categoryAdd = new Category("","title");
        categoryRepository.add(categoryAdd);
        FacesMessage msg = new FacesMessage("New Car added", categoryAdd.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
