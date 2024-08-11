package MidtermExam.Group2.controller;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.service.InvoiceProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = InvoiceProductController.class)
class InvoiceProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceProductService invoiceProductService;

    private InvoiceProductDTO invoiceProductDTO;
    private ObjectMapper objectMapper;
    private UUID invoiceId;
    private UUID productId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        invoiceId = UUID.randomUUID();
        productId = UUID.randomUUID();
        invoiceProductDTO = new InvoiceProductDTO(invoiceId, productId, "Product A", 1, BigDecimal.valueOf(20.00));
    }

    @Test
    void getAllInvoiceProductsTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<InvoiceProductDTO> page = new PageImpl<>(Collections.singletonList(invoiceProductDTO), pageable, 1);

        when(invoiceProductService.getAllInvoiceProducts(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/v1/invoice-products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].invoiceId").value(invoiceProductDTO.getInvoiceId().toString()))
                .andExpect(jsonPath("$.content[0].productId").value(invoiceProductDTO.getProductId().toString()))
                .andExpect(jsonPath("$.content[0].productName").value(invoiceProductDTO.getProductName()))
                .andExpect(jsonPath("$.content[0].quantity").value(invoiceProductDTO.getQuantity()))
                .andExpect(jsonPath("$.content[0].amount").value(invoiceProductDTO.getAmount()))
                .andDo(print());
    }

    @Test
    void addInvoiceProductTest() throws Exception {
        when(invoiceProductService.addInvoiceProduct(any(InvoiceProductDTO.class))).thenReturn(invoiceProductDTO);

        mockMvc.perform(post("/api/v1/invoice-products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceProductDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.invoiceId").value(invoiceId.toString()))
                .andExpect(jsonPath("$.productId").value(productId.toString()))
                .andExpect(jsonPath("$.productName").value(invoiceProductDTO.getProductName()))
                .andExpect(jsonPath("$.quantity").value(invoiceProductDTO.getQuantity()))
                .andExpect(jsonPath("$.amount").value(invoiceProductDTO.getAmount()))
                .andDo(print());
    }

    @Test
    void addInvoiceProduct_RuntimeExceptionTest() throws Exception {
        when(invoiceProductService.addInvoiceProduct(any(InvoiceProductDTO.class))).thenThrow(new RuntimeException("Error adding invoice product"));

        Map<String, String> response = new HashMap<>();
        response.put("errors", "Error adding invoice product");

        mockMvc.perform(post("/api/v1/invoice-products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceProductDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors").value("Error adding invoice product"))
                .andDo(print());
    }

    @Test
    void editInvoiceProductTest() throws Exception {
        InvoiceProductDTO editedInvoiceProductDTO = new InvoiceProductDTO(invoiceId, productId, "Product A", 3, BigDecimal.valueOf(20.00));
        when(invoiceProductService.editInvoiceProduct(any(InvoiceProductDTO.class), any(UUID.class), any(UUID.class))).thenReturn(editedInvoiceProductDTO);

        mockMvc.perform(put("/api/v1/invoice-products/{invoiceId}/{productId}", invoiceId, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editedInvoiceProductDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.invoiceId").value(invoiceId.toString()))
                .andExpect(jsonPath("$.productId").value(productId.toString()))
                .andExpect(jsonPath("$.productName").value(editedInvoiceProductDTO.getProductName()))
                .andExpect(jsonPath("$.quantity").value(editedInvoiceProductDTO.getQuantity()))
                .andExpect(jsonPath("$.amount").value(editedInvoiceProductDTO.getAmount()))
                .andDo(print());
    }

    @Test
    void editInvoiceProduct_RuntimeExceptionTest() throws Exception {
        when(invoiceProductService.editInvoiceProduct(any(InvoiceProductDTO.class), any(UUID.class), any(UUID.class))).thenThrow(new RuntimeException("Error editing invoice product"));

        Map<String, String> response = new HashMap<>();
        response.put("errors", "Error editing invoice product");

        mockMvc.perform(put("/api/v1/invoice-products/{invoiceId}/{productId}", invoiceId, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceProductDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors").value("Error editing invoice product"))
                .andDo(print());
    }

    @Test
    void deleteInvoiceProductTest() throws Exception {
        doNothing().when(invoiceProductService).deleteInvoiceProduct(any(UUID.class), any(UUID.class));

        mockMvc.perform(delete("/api/v1/invoice-products/{invoiceId}/{productId}", invoiceId, productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteInvoiceProduct_RuntimeExceptionTest() throws Exception {
        doThrow(new RuntimeException("Error deleting invoice product")).when(invoiceProductService).deleteInvoiceProduct(any(UUID.class), any(UUID.class));

        Map<String, String> response = new HashMap<>();
        response.put("errors", "Error deleting invoice product");

        mockMvc.perform(delete("/api/v1/invoice-products/{invoiceId}/{productId}", invoiceId, productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
