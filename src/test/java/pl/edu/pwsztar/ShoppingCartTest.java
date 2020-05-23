package pl.edu.pwsztar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartTest {

    @Test
    void test() {
        assertTrue(true);
    }

    @DisplayName("Should add product to shopping cart")
    @ParameterizedTest
    @CsvSource({
            "laptop, 3000, 1",
            "TV, 4500, 2",
            "phone, 1000, 4"
    })
    void shouldAddProductToShoppingCart(String productName, int price, int amount) {
        final ShoppingCart shoppingCart = new ShoppingCart();
        final boolean result = shoppingCart.addProducts(productName, price, amount);
        assertTrue(result);
    }

    @DisplayName("Should not add product to shopping cart")
    @ParameterizedTest
    @CsvSource({
            "laptop, 3000, -1",
            "TV, 4500, 800",
            "phone, 1000, 0"
    })
    void shouldNotAddProductToShoppingCart(String productName, int price, int amount) {
        final ShoppingCart shoppingCart = new ShoppingCart();
        final boolean result = shoppingCart.addProducts(productName, price, amount);
        assertFalse(result);
    }

    @DisplayName("Should delete product from shopping cart")
    @ParameterizedTest
    @CsvSource(
            {
                    "laptop, 1",
                    "phone, 4"
            }
    )
    void shouldDeleteProductFromShoppingCart(String productName, int amount) {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProducts("laptop", 3000, 1);
        shoppingCart.addProducts("phone", 1000, 4);

        final boolean result = shoppingCart.deleteProducts(productName, amount);
        assertTrue(result);
    }

    @DisplayName("Should not delete product from shopping cart")
    @ParameterizedTest
    @CsvSource(
            {
                    "laptop, 1",
                    "phone, 4"
            }
    )
    void shouldNotDeleteProductFromShoppingCart(String productName, int amount) {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProducts("laptop", 3000, 1);
        shoppingCart.addProducts("phone", 1000, 2);

        final boolean result = shoppingCart.deleteProducts(productName, amount);
        assertFalse(result);
    }

    @DisplayName("Should return quantity of product from Shopping Cart")
    @ParameterizedTest
    @CsvSource(
            {
                    "laptop,  1",
                    "TV,  2",
                    "phone, 4"
            }
    )
    void shouldReturnQuantityOfProductFromShoppingCart(String productName, int amount) {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProducts("laptop", 3000, 1);
        shoppingCart.addProducts("TV", 4500, 2);
        shoppingCart.addProducts("phone", 3000, 4);

        final int result = shoppingCart.getQuantityOfProduct(productName);

        assertEquals(result, amount);
    }

    @DisplayName("Should return sum of price of all products in shoppng cart")
    @Test
    void shouldReturnSumOfProductsPriceFromShoppingCart() {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProducts("laptop", 3000, 2);
        shoppingCart.addProducts("TV", 4500, 1);

        final int result = shoppingCart.getSumProductsPrices();

        assertEquals(10500, result);
    }

    @DisplayName("Should return price of product from Shopping Cart")
    @ParameterizedTest
    @CsvSource({
            "laptop, 2",
            "TV, 4",
            "phone, 2"
    })
    void shouldReturnPriceOfProductFromShoppingCart(String productName, int price) {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProducts("laptop", 3000, 2);
        shoppingCart.addProducts("TV", 4500, 4);
        shoppingCart.addProducts("phone", 1000, 2);

        final int result = shoppingCart.getProductPrice(productName);

        assertEquals(result, price);
    }

    @DisplayName("Should return names of products from shopping cart")
    @Test
    void shouldReturnNamesOfProductsFromShoppingCart() {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProducts("laptop", 3000, 3);
        shoppingCart.addProducts("TV", 4500, 7);
        shoppingCart.addProducts("phone", 1000, 2);

        List<String> products = new ArrayList<String>();
        products.add("laptop");
        products.add("TV");
        products.add("phone");

        final List<String> result = shoppingCart.getProductsNames();

        assertEquals(products, result);
    }






}
