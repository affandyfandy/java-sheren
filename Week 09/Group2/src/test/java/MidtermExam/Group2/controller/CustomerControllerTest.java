package MidtermExam.Group2.controller;

import MidtermExam.Group2.dto.CustomerDTO;
import MidtermExam.Group2.service.CustomerService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerDTO customerDTO;
    private UUID customerId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerId = UUID.randomUUID();
        customerDTO = new CustomerDTO(customerId, "Customer A", "+628123822839", "ACTIVE");
    }

    @Test
    void getAllCustomersTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CustomerDTO> page = new PageImpl<>(Collections.singletonList(customerDTO), pageable, 1);

        when(customerService.getAllCustomers(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(customerDTO.getId().toString()))
                .andExpect(jsonPath("$.content[0].name").value(customerDTO.getName()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(customerDTO.getPhoneNumber()))
                .andExpect(jsonPath("$.content[0].status").value(customerDTO.getStatus()))
                .andDo(print());
    }

    @Test
    void getCustomerByIdTest() throws Exception {
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customerDTO));

        mockMvc.perform(get("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customerDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(customerDTO.getName()))
                .andExpect(jsonPath("$.phoneNumber").value(customerDTO.getPhoneNumber()))
                .andExpect(jsonPath("$.status").value(customerDTO.getStatus()))
                .andDo(print());
    }

    @Test
    void createCustomerTest() throws Exception {
        when(customerService.createCustomer(any(CustomerDTO.class))).thenReturn(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Customer B\", \"phoneNumber\": \"+628373829290\", \"status\": \"ACTIVE\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customerDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(customerDTO.getName()))
                .andExpect(jsonPath("$.phoneNumber").value(customerDTO.getPhoneNumber()))
                .andExpect(jsonPath("$.status").value(customerDTO.getStatus()))
                .andDo(print());
    }

    @Test
    void editCustomerTest() throws Exception {
        CustomerDTO updatedCustomerDTO = new CustomerDTO(customerId, "Customer B Updated", "+628373829295", "ACTIVE");

        when(customerService.editCustomer(any(UUID.class), any(CustomerDTO.class))).thenReturn(Optional.of(updatedCustomerDTO));

        mockMvc.perform(put("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Customer B Updated\", \"phoneNumber\": \"+628373829295\", \"status\": \"ACTIVE\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.name").value("Customer B Updated"))
                .andExpect(jsonPath("$.phoneNumber").value("+628373829295"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andDo(print());
    }

    @Test
    void changeCustomerStatusTest() throws Exception {
        CustomerDTO inactiveCustomerDTO = new CustomerDTO(customerId, "Customer A", "+628123822839", "INACTIVE");

        when(customerService.changeCustomerStatus(any(UUID.class))).thenReturn(Optional.of(inactiveCustomerDTO));

        mockMvc.perform(patch("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customerDTO.getId().toString()))
                .andExpect(jsonPath("$.status").value("INACTIVE"))
                .andDo(print());
    }

    @Test
    void changeCustomerStatus_NotFoundTest() throws Exception {
        when(customerService.changeCustomerStatus(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(patch("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found."))
                .andDo(print());
    }

    @Test
    void deleteCustomerTest() throws Exception {
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customerDTO));
        doNothing().when(customerService).deleteCustomer(customerId);

        mockMvc.perform(delete("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer record deleted successfully."))
                .andDo(print());
    }

    @Test
    void deleteCustomer_NotFoundTest() throws Exception {
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found."))
                .andDo(print());
    }
}
