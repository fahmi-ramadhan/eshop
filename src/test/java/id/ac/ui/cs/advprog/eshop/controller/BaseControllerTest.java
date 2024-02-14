package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class BaseControllerTest {

    @Test
    void homePage() {
        BaseController baseController = new BaseController();
        Model model = mock(Model.class);

        String viewName = baseController.homePage();

        assertEquals("HomePage", viewName);
    }
}
