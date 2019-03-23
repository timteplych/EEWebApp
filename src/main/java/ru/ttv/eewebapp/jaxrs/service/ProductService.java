package ru.ttv.eewebapp.jaxrs.service;

import ru.ttv.eewebapp.jaxrs.representation.ProductRepresentation;
import ru.ttv.eewebapp.model.Category;
import ru.ttv.eewebapp.model.Product;
import ru.ttv.eewebapp.repository.CategoriesRepository;
import ru.ttv.eewebapp.repository.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Teplykh Timofey  21.03.2019
 */
@Stateless
public class ProductService {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoriesRepository categoryRepository;


    public List<ProductRepresentation> getByCategory(long id) {
        return productRepository.getByCategory(id)
                .stream()
                .map(ProductRepresentation::new)
                .collect(Collectors.toList());
    }

    public List<ProductRepresentation> getAll() {
        return productRepository.getAll()
                .stream()
                .map(ProductRepresentation::new)
                .collect(Collectors.toList());
    }

    public ProductRepresentation getById(long id) {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new NotFoundException(String.format("Product with id %d not found", id));
        }
        return new ProductRepresentation(product);
    }

    public ProductRepresentation getByName(String name) {
        Product product = productRepository.getByName(name);
        if (product == null) {
            throw new NotFoundException(String.format("Product with name %d not found",name));
        }
        return new ProductRepresentation(product);
    }

    public long addProduct(ProductRepresentation productRepr) {
        Category category = categoryRepository.getById(productRepr.getCategory().getId());
        Product product = new Product(productRepr.getName(), productRepr.getPrice(), category);
        product = productRepository.merge(product);
        return product.getId();
    }

    public void removeProduct(long id) {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new NotFoundException(String.format("Product with id %d not found", id));
        }
        productRepository.remove(product.getId());
    }

    public long count() {
        return productRepository.count();
    }
}
