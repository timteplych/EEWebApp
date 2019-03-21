package ru.ttv.eewebapp.jaxws;

import ru.ttv.eewebapp.model.Category;
import ru.ttv.eewebapp.model.Product;
import ru.ttv.eewebapp.repository.CategoriesRepository;
import ru.ttv.eewebapp.repository.ProductRepository;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.math.BigDecimal;

/**
 * @author Teplykh Timofey  21.03.2019
 */
@WebService(serviceName = "ws/ShopWS")
public class ShopWS {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoriesRepository categoriesRepository;


    @WebMethod
    public String addProduct(@WebParam(name = "name") String name,
                      @WebParam(name = "price") BigDecimal price,
                      @WebParam(name = "categoryId") Long categoryId){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(categoriesRepository.getById(categoryId));
        productRepository.merge(product);
        return String.valueOf(product.getId());
    }
    @WebMethod
    public String deleteProductById(@WebParam(name = "id") Long id){
        productRepository.remove(id);
        return "Product deleted";
    }

    @WebMethod
    public String getProductById(@WebParam(name="id") Long id){
        return productRepository.getById(id).toString();
    }

    @WebMethod
    public String addCategory(@WebParam(name = "name") String name){
        Category category = new Category();
        category.setName(name);
        categoriesRepository.merge(category);
        return String.valueOf(category.getId());
    }

    @WebMethod
    public String getProductByName(@WebParam(name = "name") String name){
        return productRepository.getByName(name).toString();
    }

    @WebMethod
    public String getAllProducts(){
        return productRepository.getAll().toString();
    }

    @WebMethod
    public String getProductsByCategoryId(@WebParam(name = "id") Long id){
        return productRepository.getByCategory(id).toString();
    }

}
