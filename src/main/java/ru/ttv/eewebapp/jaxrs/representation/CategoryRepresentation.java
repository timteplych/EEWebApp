package ru.ttv.eewebapp.jaxrs.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ttv.eewebapp.model.Category;

/**
 * @author Teplykh Timofey  21.03.2019
 */
public class CategoryRepresentation {

    private long id;

    @JsonProperty(required = false)
    private String name;

    public CategoryRepresentation() {
    }

    public CategoryRepresentation(Category category) {
        id = category.getId();
        name = category.getName();
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
}
