package ru.ttv.eewebapp.webbeans;

import ru.ttv.eewebapp.interceptors.UserActionLogger;
import ru.ttv.eewebapp.model.Product;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Teplykh Timofey  16.03.2019
 */
@Named("shoppingCart")
@Stateless
public class ShoppingCart {

    private List<Product> productList = new ArrayList<>();

    @Interceptors({UserActionLogger.class})
    public void addToCart(Product product){
        productList.add(product);
        FacesMessage msg = new FacesMessage("Product added", String.valueOf(product.getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Product> getAll(){
        return productList;
    }

    @Interceptors({UserActionLogger.class})
    public void deleteFromCart(Product product){
        productList.remove(product);
    }
}
