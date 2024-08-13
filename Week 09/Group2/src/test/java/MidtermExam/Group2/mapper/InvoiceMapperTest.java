package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.*;
import MidtermExam.Group2.entity.Customer;
import MidtermExam.Group2.entity.Invoice;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InvoiceMapperTest {

    private final InvoiceMapper invoiceMapper = Mappers.getMapper(InvoiceMapper.class);

    @Test
    void toInvoiceListDTOTest() {
        Customer customer = new Customer();
        customer.setName("Customer A");

        Invoice invoice = new Invoice();
        invoice.setId(UUID.randomUUID());
        invoice.setInvoiceAmount(BigDecimal.valueOf(20.00));
        invoice.setCustomer(customer);
        invoice.setInvoiceDate(LocalDateTime.now());

        InvoiceListDTO invoiceListDTO = invoiceMapper.toInvoiceListDTO(invoice);

        assertNotNull(invoiceListDTO);
        assertEquals(invoice.getId(), invoiceListDTO.getId());
        assertEquals(invoice.getInvoiceAmount(), invoiceListDTO.getInvoiceAmount());
        assertEquals(customer.getName(), invoiceListDTO.getCustomerName());
        assertEquals(invoice.getInvoiceDate().toLocalDate(), invoiceListDTO.getInvoiceDate());
    }

    @Test
    void toInvoicesDTOTest() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());

        Invoice invoice = new Invoice();
        invoice.setId(UUID.randomUUID());
        invoice.setInvoiceAmount(BigDecimal.valueOf(40.00));
        invoice.setCustomer(customer);
        invoice.setInvoiceDate(LocalDateTime.now());

        InvoiceDTO invoiceDTO = invoiceMapper.toInvoicesDTO(invoice);

        assertNotNull(invoiceDTO);
        assertEquals(invoice.getId(), invoiceDTO.getId());
        assertEquals(invoice.getInvoiceAmount(), invoiceDTO.getInvoiceAmount());
        assertEquals(customer.getId(), invoiceDTO.getCustomerId());
        assertEquals(invoice.getInvoiceDate().toLocalDate(), invoiceDTO.getInvoiceDate());
    }
}
