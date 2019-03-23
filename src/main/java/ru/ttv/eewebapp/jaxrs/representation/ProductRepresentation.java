package ru.ttv.eewebapp.jaxrs.representation;

import ru.ttv.eewebapp.model.Product;

import java.math.BigDecimal;

/**
 * @author Teplykh Timofey  21.03.2019
 */
public class ProductRepresentation {
    private long id;

    private String name;

    private BigDecimal price;

    private CategoryRepresentation category;

    public ProductRepresentation() {
    }

    public ProductRepresentation(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        category = new CategoryRepresentation(product.getCategory());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryRepresentation getCategory() {
        return category;
    }

    public void setCategory(CategoryRepresentation category) {
        this.category = category;
    }
}
