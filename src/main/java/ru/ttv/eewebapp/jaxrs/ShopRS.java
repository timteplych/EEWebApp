package ru.ttv.eewebapp.jaxrs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ru.ttv.eewebapp.jaxrs.representation.CategoryRepresentation;
import ru.ttv.eewebapp.jaxrs.representation.ProductRepresentation;
import ru.ttv.eewebapp.jaxrs.service.CategoriesService;
import ru.ttv.eewebapp.jaxrs.service.ProductService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * @author Teplykh Timofey  21.03.2019
 */
@Path("/")
@Api(value = "ProductCrudRestService", description = "JAX-RS and Swagger Demo")
public class ShopRS  {

    @EJB
    private ProductService productService;

    @EJB
    private CategoriesService categoriesService;

    @POST
    @Path("products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    @ApiOperation(value = "Add new product")
    @RolesAllowed("admin")
    public Response addProduct(@ApiParam(value = "New product to add") ProductRepresentation product){
        long id = productService.addProduct(product);
        return Response.accepted(String.format("New product with id %d created", id)).build();
    }

    @DELETE
    @Path("/products/{id}")
    @ApiOperation(value = "Delete product")
    @RolesAllowed("admin")
    public Response deleteProduct(@ApiParam(name = "id", value = "Product id to delete") @PathParam("id") long id) {
        productService.removeProduct(id);
        return Response.accepted().build();
    }

    @POST
    @Path("categories")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    @ApiOperation(value = "Add new category")
    @RolesAllowed("admin")
    public Response addCategory(@ApiParam(value = "New category to add") CategoryRepresentation category){
        long id = categoriesService.addCategory(category);
        return Response.accepted(String.format("New category with id %d created", id)).build();
    }

    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get product by id")
    public ProductRepresentation getProductByID(@ApiParam(name = "id", value = "Product id to get") @PathParam("id") long id){
        return productService.getById(id);
    }

    @GET
    @Path("/products/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get product by name")
    public ProductRepresentation getProductByName(@ApiParam(name = "name", value = "Product name to get") @PathParam("name") String name){
        return productService.getByName(name);
    }

    @GET
    @Path("/products/all")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all products")
    public Collection<ProductRepresentation> getAllProducts() {
        return productService.getAll();
    }

    @GET
    @Path("/products/cat/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get product by id")
    public Collection<ProductRepresentation> getProductByCategoryId(@ApiParam(name = "id", value = "Category id to get") @PathParam("id") long id){
        return productService.getByCategory(id);
    }
}
