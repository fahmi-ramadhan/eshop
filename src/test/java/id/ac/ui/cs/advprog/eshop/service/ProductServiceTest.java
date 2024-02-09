package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
    @InjectMocks
    ProductRepository productRepository = new ProductRepository();

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("e020e0a3-40c7-441f-a8a3-164681fe4792");
        product.setProductName("Sampo Cap Kuda");
        product.setProductQuantity(420);
        productService.create(product);

        List<Product> savedProducts = productService.findAll();
        Product savedProduct = savedProducts.get(0);

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        List<Product> savedProducts = productService.findAll();
        assertEquals(savedProducts.size(), 0);
    }

    
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product1.setProductName("Sampo Cap Udin");
        product1.setProductQuantity(100);
        productService.create(product1);

        Product product2 = new Product();
        product2.setProductId("eb3bbd50-ef1f-48b4-abb1-439c63e7ce8d");
        product2.setProductName("Sampo Cap Ucok");
        product2.setProductQuantity(50);
        productService.create(product2);

        List<Product> savedProducts = productService.findAll();
        assertEquals(2, savedProducts.size());
        Product savedProduct = savedProducts.get(0);
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = savedProducts.get(1);
        assertEquals(product2.getProductId(), savedProduct.getProductId());
    }

    @Test
    void testCreateAndFindById() {
        Product product = new Product();
        product.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product.setProductName("Sampo Cap Udin");
        product.setProductQuantity(100);
        productService.create(product);

        Product foundProduct = productService.findById("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testFindByIdIfNotFound() {
        assertThrows(ProductNotFoundException.class, () -> {
            productService.findById("non-existing-id");
        });
    }

    @Test
    void testCreateAndEdit() {
        Product product = new Product();
        product.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product.setProductName("Sampo Cap Udin");
        product.setProductQuantity(100);
        productService.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        editedProduct.setProductName("Sampo Cap Udin");
        editedProduct.setProductQuantity(200);
        Product updatedProduct = productService.update(editedProduct);

        assertEquals(editedProduct.getProductId(), updatedProduct.getProductId());
        assertEquals(editedProduct.getProductName(), updatedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), updatedProduct.getProductQuantity());
    }

    @Test
    void testEditIfNotFound() {
        Product editedProduct = new Product();
        editedProduct.setProductId("non-existing-id");
        editedProduct.setProductName("Sampo Cap Udin");
        editedProduct.setProductQuantity(200);
        assertThrows(ProductNotFoundException.class, () -> {
            productService.update(editedProduct);
        });
    }

    @Test
    void testCreateAndDelete() {
        Product product = new Product();
        product.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product.setProductName("Sampo Cap Udin");
        product.setProductQuantity(100);
        productService.create(product);

        Product deletedProduct = productService.delete("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        assertEquals(product.getProductId(), deletedProduct.getProductId());
        assertEquals(product.getProductName(), deletedProduct.getProductName());
        assertEquals(product.getProductQuantity(), deletedProduct.getProductQuantity());
    }

    @Test
    void testDeleteIfNotFound() {
        assertThrows(ProductNotFoundException.class, () -> {
            productService.delete("non-existing-id");
        });
    }
}
