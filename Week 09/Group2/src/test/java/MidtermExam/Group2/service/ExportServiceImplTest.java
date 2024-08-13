package MidtermExam.Group2.service;

import MidtermExam.Group2.entity.Customer;
import MidtermExam.Group2.entity.Invoice;
import MidtermExam.Group2.entity.InvoiceProduct;
import MidtermExam.Group2.entity.Product;
import MidtermExam.Group2.repository.InvoiceRepository;
import MidtermExam.Group2.service.impl.ExportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExportServiceImplTest {

    @InjectMocks
    private ExportServiceImpl exportServiceImpl;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Test
    void exportInvoicesToExcel_invoicesFoundTest() throws IOException {
        UUID customerId = UUID.randomUUID();
        int month = 7;
        int year = 2024;

        List<Invoice> mockInvoices = new ArrayList<>();
        Invoice mockInvoice = mock(Invoice.class);

        when(mockInvoice.getId()).thenReturn(UUID.randomUUID());
        when(mockInvoice.getInvoiceDate()).thenReturn(LocalDateTime.now());
        when(mockInvoice.getInvoiceAmount()).thenReturn(BigDecimal.valueOf(1000.00));

        Customer mockCustomer = mock(Customer.class);

        when(mockCustomer.getId()).thenReturn(UUID.randomUUID());
        when(mockCustomer.getName()).thenReturn("Customer A");
        when(mockInvoice.getCustomer()).thenReturn(mockCustomer);

        InvoiceProduct mockInvoiceProduct = mock(InvoiceProduct.class);
        Product mockProduct = mock(Product.class);

        when(mockProduct.getId()).thenReturn(UUID.randomUUID());
        when(mockProduct.getName()).thenReturn("Product A");
        when(mockProduct.getPrice()).thenReturn(BigDecimal.valueOf(100.00));
        when(mockInvoiceProduct.getProduct()).thenReturn(mockProduct);
        when(mockInvoiceProduct.getQuantity()).thenReturn(2);
        when(mockInvoiceProduct.getAmount()).thenReturn(BigDecimal.valueOf(200.00));
        when(mockInvoice.getInvoiceProducts()).thenReturn(List.of(mockInvoiceProduct));

        mockInvoices.add(mockInvoice);

        when(invoiceRepository.findByCustomerAndDate(customerId, month, year)).thenReturn(mockInvoices);

        ByteArrayInputStream result = exportServiceImpl.exportInvoicesToExcel(customerId, month, year);

        assertNotNull(result);

        verify(invoiceRepository).findByCustomerAndDate(customerId, month, year);
    }

    @Test
    void exportInvoicesToExcel_noInvoicesFoundTest() {
        UUID customerId = UUID.randomUUID();
        int month = 7;
        int year = 2024;

        when(invoiceRepository.findByCustomerAndDate(customerId, month, year)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                exportServiceImpl.exportInvoicesToExcel(customerId, month, year));

        assertEquals("No invoices found", exception.getMessage());

        verify(invoiceRepository).findByCustomerAndDate(customerId, month, year);
    }
}
