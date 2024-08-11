package MidtermExam.Group2.controller;

import MidtermExam.Group2.dto.ProductDTO;
import MidtermExam.Group2.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)

class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    private ProductDTO productDTO;
    private UUID productId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productId = UUID.randomUUID();
        productDTO = new ProductDTO(productId, "Product A", BigDecimal.valueOf(60.00), "ACTIVE");
    }

    @Test
    void getAllProductsTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductDTO> page = new PageImpl<>(Collections.singletonList(productDTO), pageable, 1);

        when(productService.getAllProducts(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(productDTO.getId().toString()))
                .andExpect(jsonPath("$.content[0].name").value(productDTO.getName()))
                .andExpect(jsonPath("$.content[0].price").value(productDTO.getPrice()))
                .andExpect(jsonPath("$.content[0].status").value(productDTO.getStatus()))
                .andDo(print());
    }

    @Test
    void getProductByIdTest() throws Exception {
        when(productService.getProductById(productId)).thenReturn(Optional.of(productDTO));

        mockMvc.perform(get("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":\"" + productId.toString() + "\",\"name\":\"Product A\",\"price\":60.00,\"status\":\"ACTIVE\"}"))
                .andDo(print());
    }

    @Test
    void createProductTest() throws Exception {
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Product A\", \"price\": \"60.00\", \"status\": \"ACTIVE\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(productDTO.getId().toString()))
                .andExpect(content().json("{\"id\":\"" + productId.toString() + "\",\"name\": \"Product A\", \"price\": 60.00, \"status\": \"ACTIVE\"}"))
                .andDo(print());
    }

    @Test
    void createProduct_ValidationErrorTest() throws Exception {
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Product A\", \"price\": \"60.00\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());
    }

    @Test
    void updateProductTest() throws Exception {
        ProductDTO updatedProductDTO = new ProductDTO(productId, "Product A Update", BigDecimal.valueOf(80.00), "ACTIVE");

        when(productService.updateProduct(any(UUID.class), any(ProductDTO.class))).thenReturn(Optional.of(updatedProductDTO));

        mockMvc.perform(put("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\":\"Product A Update\",\"price\":80,\"status\":\"ACTIVE\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{\"id\":\"" + productId.toString() + "\",\"name\":\"Product A Update\",\"price\":80,\"status\":\"ACTIVE\"}"))
                .andDo(print());
    }

    @Test
    void toggleProductStatusTest() throws Exception {
        ProductDTO inactiveProductDTO = new ProductDTO(productId, "Product A", BigDecimal.valueOf(60.00), "INACTIVE");

        when(productService.toggleProductStatus(any(UUID.class))).thenReturn(Optional.of(inactiveProductDTO));

        mockMvc.perform(patch("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{\"id\":\"" + productId.toString() + "\",\"name\":\"Product A\",\"price\":60,\"status\":\"INACTIVE\"}"))
                .andDo(print());
    }

    @Test
    void deleteProductTest() throws Exception {
        mockMvc.perform(delete("/api/v1/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Product successfully deleted"))
                .andDo(print());
    }

    @Test
    void importProductsFromCsvTest() throws Exception {
        String csvContent = "Test Product,10.00,ACTIVE\nAnother Product,20.00,INACTIVE";
        MultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", csvContent.getBytes());

        mockMvc.perform(multipart("/api/v1/products/import")
                        .file((MockMultipartFile) file))
                .andExpect(status().isOk())
                .andExpect(content().string("Products imported successfully"))
                .andDo(print());
    }

    @Test
    void importProductsFromCsv_FailureTest() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "invalid content".getBytes());

        doThrow(new IOException("Failed to import products")).when(productService).importProductsFromCsv(any());

        mockMvc.perform(multipart("/api/v1/products/import")
                        .file((MockMultipartFile) file))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to import products"))
                .andDo(print());
    }

    @Test
    void searchProductsTest() throws Exception {
        Page<ProductDTO> productPage = new PageImpl<>(Collections.singletonList(productDTO));
        when(productService.searchProducts(any(), any(), any(Pageable.class))).thenReturn(productPage);

        mockMvc.perform(get("/api/v1/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Product A")
                        .param("status", "ACTIVE")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "name,asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].name").value("Product A"))
                .andExpect(jsonPath("$.content[0].status").value("ACTIVE"))
                .andDo(print());
    }
}
