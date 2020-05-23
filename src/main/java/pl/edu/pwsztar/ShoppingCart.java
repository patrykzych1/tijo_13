package pl.edu.pwsztar;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements ShoppingCartOperation {

    private final List<Product> productsList;

    public ShoppingCart() {
        this.productsList = new ArrayList<Product>();
    }
    public boolean addProducts(String productName, int price, int quantity) {
        if(getAllProductsQuantity() + quantity > ShoppingCart.PRODUCTS_LIMIT || price <= 0 || quantity <= 0)
        {
            return false;
        }

        productName = productName.toLowerCase();

        if(isProductAlreadyAdded(productName)) {
            for(Product product: productsList) {
                if(product.getName().equals(productName) && product.getPrice() != price) {
                    product.setAmount(product.getAmount() + quantity);
                    return true;
                }
            }
        } else {
            productsList.add(new Product(productName, price, quantity));
            return true;
        }

        return false;
    }

    public boolean deleteProducts(String productName, int quantity) {
        if(quantity <= 0){
            return false;
        }

        productName = productName.toLowerCase();

        for(Product product: productsList) {
            if(product.getName().toLowerCase().equals(productName)) {
                if(product.getAmount() > quantity) {
                    product.setAmount(product.getAmount() - quantity);
                    return true;
                } else if(product.getAmount() == quantity) {
                    productsList.remove(product);
                    return true;
                }
            }
        }

        return false;
    }

    public int getQuantityOfProduct(String productName) {
        int result = 0;
        for(Product product: productsList) {
            if(product.getName().toLowerCase().equals(productName.toLowerCase()))
            {
                result += product.getAmount();
            }
        }
        return result;
    }

    public int getSumProductsPrices() {
        int result = 0;
        for(Product product: productsList) {
            result += product.getPrice() * product.getAmount();
        }
        return result;
    }

    public int getProductPrice(String productName) {
        productName = productName.toLowerCase();
        for(Product product: productsList) {
            if(product.getName().toLowerCase().equals(productName.toLowerCase())) {
                return product.getPrice();
            }
        }
        return 0;
    }

    public List<String> getProductsNames() {
        List<String> result = new ArrayList<>();
        for(Product product: productsList)
        {
            result.add(product.getName().toLowerCase());
        }
        return result;
    }

    private boolean isProductAlreadyAdded(String name) {
        return productsList.stream()
                .anyMatch(product -> product.getName().toLowerCase().equals(name.toLowerCase()));
    }
    private double getAllProductsQuantity(){
        return productsList.stream()
                .mapToInt(product -> product.getPrice() * product.getAmount())
                .sum();
    }
}
