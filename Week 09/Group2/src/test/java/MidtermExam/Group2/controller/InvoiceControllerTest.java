package MidtermExam.Group2.controller;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.*;
import MidtermExam.Group2.service.ExportService;
import MidtermExam.Group2.service.InvoiceService;
import MidtermExam.Group2.service.PdfService;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @MockBean
    private ExportService exportService;

    @MockBean
    private PdfService pdfService;

    private InvoiceDTO invoiceDTO;
    private InvoiceDetailDTO invoiceDetailDTO;
    private InvoiceListDTO invoiceListDTO;
    private UUID invoiceId;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceId = UUID.randomUUID();
        invoiceDTO = new InvoiceDTO(invoiceId, UUID.randomUUID(), BigDecimal.valueOf(1000), LocalDate.of(2024, 9, 13));

        invoiceDetailDTO = new InvoiceDetailDTO();
        invoiceDetailDTO.setInvoiceId(invoiceId);
        invoiceDetailDTO.setInvoiceAmount(BigDecimal.valueOf(1000));
        invoiceDetailDTO.setInvoiceDate(LocalDate.of(2024, 9, 13));

        CustomerDTO customerDTO = new CustomerDTO(UUID.randomUUID(), "Customer A", "+628228828282", "ACTIVE");
        invoiceDetailDTO.setCustomer(customerDTO);

        InvoiceProductWithoutProductIdDTO productDTO = new InvoiceProductWithoutProductIdDTO(invoiceId,
                "Product A", BigDecimal.valueOf(100), 10, BigDecimal.valueOf(1000));

        invoiceDetailDTO.setProducts(Collections.singletonList(productDTO));

        invoiceListDTO = new InvoiceListDTO(invoiceId, invoiceDetailDTO.getInvoiceAmount(), invoiceDetailDTO.getCustomer().getName(), invoiceDetailDTO.getInvoiceDate());

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getAllInvoicesTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<InvoiceListDTO> page = new PageImpl<>(Collections.singletonList(new InvoiceListDTO(invoiceId, invoiceDTO.getInvoiceAmount(), invoiceDetailDTO.getCustomer().getName(), invoiceDTO.getInvoiceDate())), pageable, 1);

        when(invoiceService.getAllInvoices(any(Pageable.class), any(InvoiceSearchCriteria.class))).thenReturn(page);

        mockMvc.perform(get("/api/v1/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(invoiceListDTO.getId().toString()))
                .andExpect(jsonPath("$.content[0].invoiceAmount").value(invoiceListDTO.getInvoiceAmount()))
                .andExpect(jsonPath("$.content[0].customerName").value(invoiceListDTO.getCustomerName()))
                .andExpect(jsonPath("$.content[0].invoiceDate").value(invoiceListDTO.getInvoiceDate().toString()))
                .andDo(print());
    }

    @Test
    void getInvoiceDetailTest() throws Exception {
        when(invoiceService.getInvoiceDetail(invoiceId)).thenReturn(invoiceDetailDTO);

        mockMvc.perform(get("/api/v1/invoices/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.invoiceId").value(invoiceDetailDTO.getInvoiceId().toString()))
                .andExpect(jsonPath("$.invoiceAmount").value(invoiceDetailDTO.getInvoiceAmount()))
                .andExpect(jsonPath("$.invoiceDate").value(invoiceDetailDTO.getInvoiceDate().toString()))
                .andExpect(jsonPath("$.customer.id").value(invoiceDetailDTO.getCustomer().getId().toString()))
                .andExpect(jsonPath("$.customer.name").value(invoiceDetailDTO.getCustomer().getName()))
                .andExpect(jsonPath("$.customer.phoneNumber").value(invoiceDetailDTO.getCustomer().getPhoneNumber()))
                .andExpect(jsonPath("$.customer.status").value(invoiceDetailDTO.getCustomer().getStatus()))
                .andExpect(jsonPath("$.products").isArray())
                .andDo(print());
    }

    @Test
    void getInvoiceDetail_NotFoundTest() throws Exception {
        when(invoiceService.getInvoiceDetail(invoiceId)).thenThrow(new RuntimeException("Invoice not found"));

        mockMvc.perform(get("/api/v1/invoices/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void addInvoiceTest() throws Exception {
        InvoiceDTO requestInvoiceDTO = new InvoiceDTO(invoiceId, invoiceDTO.getCustomerId(), invoiceDTO.getInvoiceAmount(), invoiceDTO.getInvoiceDate());

        when(invoiceService.addInvoice(any(InvoiceDTO.class))).thenReturn(invoiceDTO);

        mockMvc.perform(post("/api/v1/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestInvoiceDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(invoiceDTO.getId().toString()))
                .andExpect(jsonPath("$.customerId").value(invoiceDTO.getCustomerId().toString()))
                .andExpect(jsonPath("$.invoiceAmount").value(invoiceDTO.getInvoiceAmount()))
                .andExpect(jsonPath("$.invoiceDate").value(invoiceDTO.getInvoiceDate().toString()))
                .andDo(print());
    }

    @Test
    void addInvoice_RuntimeExceptionTest() throws Exception {
        when(invoiceService.addInvoice(any(InvoiceDTO.class))).thenThrow(new RuntimeException("Error adding invoice"));

        mockMvc.perform(post("/api/v1/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Error adding invoice"))
                .andDo(print());
    }

    @Test
    void editInvoiceTest() throws Exception {
        InvoiceDTO editedInvoiceDTO = new InvoiceDTO(invoiceId, UUID.randomUUID(), BigDecimal.valueOf(1000), LocalDate.of(2024, 9, 15));
        when(invoiceService.editInvoice(any(UUID.class), any(InvoiceDTO.class))).thenReturn(editedInvoiceDTO);

        mockMvc.perform(put("/api/v1/invoices/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editedInvoiceDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(editedInvoiceDTO.getId().toString()))
                .andExpect(jsonPath("$.customerId").value(editedInvoiceDTO.getCustomerId().toString()))
                .andExpect(jsonPath("$.invoiceAmount").value(editedInvoiceDTO.getInvoiceAmount()))
                .andExpect(jsonPath("$.invoiceDate").value(editedInvoiceDTO.getInvoiceDate().toString()))
                .andDo(print());
    }

    @Test
    void editInvoice_NotFoundTest() throws Exception {
        when(invoiceService.editInvoice(any(UUID.class), any(InvoiceDTO.class))).thenThrow(new RuntimeException("Invoice not found"));

        mockMvc.perform(put("/api/v1/invoices/{id}", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value("Invoice not found"))
                .andDo(print());
    }

    @Test
    void exportInvoicesToExcelTest() throws Exception {
        ByteArrayInputStream excelFile = new ByteArrayInputStream("Excel content".getBytes());
        when(exportService.exportInvoicesToExcel(any(UUID.class), any(Integer.class), any(Integer.class))).thenReturn(excelFile);

        mockMvc.perform(get("/api/v1/invoices/excel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("customerId", UUID.randomUUID().toString())
                        .param("month", "8")
                        .param("year", "2024"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoices.xlsx"))
                .andExpect(content().contentType("application/vnd.ms-excel"))
                .andDo(print());
    }

    @Test
    void exportInvoicesToExcel_IOErrorTest() throws Exception {
        when(exportService.exportInvoicesToExcel(any(UUID.class), any(Integer.class), any(Integer.class))).thenThrow(new IOException("Failed to export invoices to Excel"));

        mockMvc.perform(get("/api/v1/invoices/excel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("customerId", UUID.randomUUID().toString())
                        .param("month", "8")
                        .param("year", "2024"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to export invoices to Excel"))
                .andDo(print());
    }

    @Test
    void generateInvoicePdfTest() throws Exception {
        when(invoiceService.getInvoiceDetail(any(UUID.class))).thenReturn(invoiceDetailDTO);
        when(pdfService.generatePdf(any(InvoiceDetailDTO.class))).thenReturn(new ByteArrayInputStream("PDF content".getBytes()));

        mockMvc.perform(get("/api/v1/invoices/{id}/pdf", invoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf"))
                .andExpect(content().contentType(MediaType.APPLICATION_PDF))
                .andDo(print());
    }

    @Test
    void generateInvoicePdf_InvoiceNotFoundTest() throws Exception {
        when(invoiceService.getInvoiceDetail(invoiceId)).thenThrow(new RuntimeException("Invoice not found"));

        mockMvc.perform(get("/api/v1/invoices/{id}/pdf", invoiceId))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }

    @Test
    void generateInvoicePdf_FailureTest() throws Exception {
        InvoiceDetailDTO invoiceDetail = new InvoiceDetailDTO();

        when(invoiceService.getInvoiceDetail(invoiceId)).thenReturn(invoiceDetail);
        when(pdfService.generatePdf(invoiceDetail)).thenThrow(new RuntimeException("PDF generation failed"));

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/invoices/{id}/pdf", invoiceId))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf"))
                .andExpect(content().contentType(MediaType.APPLICATION_PDF))
                .andReturn()
                .getResponse();

        String content = response.getContentAsString();
        System.out.println("Error Content: " + content);

        assertThat(content).contains("Failed to generate PDF");
    }
}
