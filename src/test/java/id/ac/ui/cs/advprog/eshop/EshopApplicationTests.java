package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import jakarta.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EshopApplicationTests {

	@Resource(name="productController")
	private ProductController controller;
	@Test
	void contextLoads() {
		EshopApplication.main(new String[] {});
		assertThat(controller).isNotNull();
	}

}