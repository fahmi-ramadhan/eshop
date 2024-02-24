package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product);
        assertEquals("redirect:list", viewName);
        verify(productService).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", productList);
    }

    @Test
    void testEditProductPage() {
        String productId = "eef30a73-dcec-4aa4-84f3-50232889bd79";
        Product product = new Product();
        product.setProductId(productId);
        when(productService.findById(productId)).thenReturn(product);

        String viewName = productController.editProductPage(productId, model);
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPost() {
        String productId = "eef30a73-dced-4aa4-84f3-50232889bd79";
        Product editedProduct = new Product();
        editedProduct.setProductId(productId);

        String viewName = productController.editProductPost(productId, editedProduct);
        assertEquals("redirect:../list", viewName);
        verify(productService).update(editedProduct);
    }

    @Test
    void testDeleteProduct() {
        String productId = "eef30a73-dcef-4aa4-84f3-50232889bd79";
        Product product = new Product();
        product.setProductId(productId);
        when(productService.findById(productId)).thenReturn(product);

        String viewName = productController.deleteProduct(productId);
        assertEquals("redirect:../list", viewName);
        verify(productService).delete(productId);
    }
}
