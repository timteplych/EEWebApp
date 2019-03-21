package ru.ttv.eewebapp.jaxrs.service;

import ru.ttv.eewebapp.jaxrs.representation.CategoryRepresentation;
import ru.ttv.eewebapp.model.Category;
import ru.ttv.eewebapp.repository.CategoriesRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Teplykh Timofey  21.03.2019
 */
@Stateless
public class CategoriesService {

    @EJB
    private CategoriesRepository categoryRepository;

    public long addCategory(CategoryRepresentation categoryRepresentation) {
        Category category = new Category(categoryRepresentation.getName());
        category = categoryRepository.merge(category);
        return category.getId();
    }
}
