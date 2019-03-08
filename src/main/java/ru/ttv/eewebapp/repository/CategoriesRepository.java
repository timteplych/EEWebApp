package ru.ttv.eewebapp.repository;

import ru.ttv.eewebapp.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Named
@ApplicationScoped
public class CategoriesRepository {

    private Map<String, Category> categoryMap = new LinkedHashMap<>();

    public CategoriesRepository() {
        this.add(new Category("1", "Pens"));
        this.add(new Category("2", "Pencils"));
        this.add(new Category("3", "Papers"));
        this.add(new Category("4", "Brackets"));
    }

    public Collection<Category> getAll() {
        return categoryMap.values();
    }

    public Category getById(String id) {
        return categoryMap.get(id);
    }

    public void add(Category category) {
        categoryMap.put(category.getId(), category);
    }

    public void save(Category category) {
        categoryMap.put(category.getId(), category);
    }

    public void delete(Category category) {
        categoryMap.remove(category.getId());
    }
}
