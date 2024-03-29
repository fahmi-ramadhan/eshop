package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {}
    
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product.setProductName("Sampo Cap Udin");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product1.setProductName("Sampo Cap Udin");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("eb3bbd50-ef1f-48b4-abb1-439c63e7ce8d");
        product2.setProductName("Sampo Cap Ucok");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateAndFindById() {
        Product product = new Product();
        product.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product.setProductName("Sampo Cap Udin");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        assertThrows(ProductNotFoundException.class, () -> {
            productRepository.findById("non-existing-id");
        });
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testFindByIdIfNotFound() {
        assertThrows(ProductNotFoundException.class, () -> {
            productRepository.findById("non-existing-id");
        });
    }

    @Test
    void testCreateAndEdit() {
        Product product = new Product();
        product.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product.setProductName("Sampo Cap Udin");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        editedProduct.setProductName("Sampo Cap Udin");
        editedProduct.setProductQuantity(200);
        Product updatedProduct = productRepository.update(editedProduct);

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
            productRepository.update(editedProduct);
        });
    }

    @Test
    void testCreateAndDelete() {
        Product product = new Product();
        product.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        product.setProductName("Sampo Cap Udin");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product deletedProduct = productRepository.delete("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        assertEquals(product.getProductId(), deletedProduct.getProductId());
        assertEquals(product.getProductName(), deletedProduct.getProductName());
        assertEquals(product.getProductQuantity(), deletedProduct.getProductQuantity());
    }

    @Test
    void testDeleteIfNotFound() {
        assertThrows(ProductNotFoundException.class, () -> {
            productRepository.delete("non-existing-id");
        });
    }
}
