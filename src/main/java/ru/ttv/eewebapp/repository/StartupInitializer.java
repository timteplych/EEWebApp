package ru.ttv.eewebapp.repository;

import ru.ttv.eewebapp.model.Category;
import ru.ttv.eewebapp.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Teplykh Timofey  13.03.2019
 */
@Singleton
@Startup
public class StartupInitializer {
    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoriesRepository categoryRepository;

    @PostConstruct
    public void init() {
        List<Category> categories = new ArrayList<>();
        if (categoryRepository.getAll().isEmpty()) {
            categories.add(categoryRepository.merge(new Category("Calendars and Planners")));
            categories.add(categoryRepository.merge(new Category("Calculators")));
            categories.add(categoryRepository.merge(new Category("Scissors and Paper Trimmers")));
        }

        if (productRepository.getAll().isEmpty()) {
            productRepository.merge(new Product("Pen", new BigDecimal(50), categories.get(0)));
            productRepository.merge(new Product("Pencil", new BigDecimal(150), categories.get(0)));
            productRepository.merge(new Product("Textbook", new BigDecimal(201), categories.get(0)));
            productRepository.merge(new Product("Paper", new BigDecimal(500), categories.get(0)));
            productRepository.merge(new Product("Pen", new BigDecimal(50), categories.get(0)));
            productRepository.merge(new Product("Eraser", new BigDecimal(150), categories.get(0)));
            productRepository.merge(new Product("Marker", new BigDecimal(200), categories.get(1)));
            productRepository.merge(new Product("Sticks", new BigDecimal(500), categories.get(1)));
            productRepository.merge(new Product("Brash", new BigDecimal(50), categories.get(1)));
            productRepository.merge(new Product("Pencil", new BigDecimal(150), categories.get(1)));
            productRepository.merge(new Product("Textbook", new BigDecimal(200), categories.get(2)));
            productRepository.merge(new Product("Paper", new BigDecimal(500), categories.get(2)));
            productRepository.merge(new Product("Pen", new BigDecimal(50), categories.get(2)));
            productRepository.merge(new Product("Pencil", new BigDecimal(150), categories.get(2)));
            productRepository.merge(new Product("Textbook", new BigDecimal(200), categories.get(2)));
            productRepository.merge(new Product("Paper", new BigDecimal(500), categories.get(2)));
        }
    }
}
