package MidtermExam.Group2.service;

import MidtermExam.Group2.criteria.InvoiceSearchCriteria;
import MidtermExam.Group2.dto.InvoiceDTO;
import MidtermExam.Group2.dto.InvoiceDetailDTO;
import MidtermExam.Group2.dto.InvoiceListDTO;
import MidtermExam.Group2.entity.Customer;
import MidtermExam.Group2.entity.Invoice;
import MidtermExam.Group2.entity.Status;
import MidtermExam.Group2.mapper.InvoiceMapper;
import MidtermExam.Group2.repository.CustomerRepository;
import MidtermExam.Group2.repository.InvoiceRepository;
import MidtermExam.Group2.repository.InvoiceSpecification;
import MidtermExam.Group2.service.impl.InvoiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceServiceImpl;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @Mock
    private CustomerRepository customerRepository;

    private Invoice invoice;
    private InvoiceDTO invoiceDTO;
    private Customer customer;
    private UUID invoiceId;
    private UUID customerId;

    @BeforeEach
    void setUp() {
        invoiceId = UUID.randomUUID();
        customerId = UUID.randomUUID();
        customer = new Customer();
        customer.setId(customerId);
        customer.setStatus(Status.ACTIVE);

        invoice = new Invoice();
        invoice.setId(invoiceId);
        invoice.setCustomer(customer);
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setInvoiceAmount(BigDecimal.valueOf(100));
        invoice.setCreatedTime(LocalDateTime.now());

        invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoiceId);
        invoiceDTO.setCustomerId(customerId);
        invoiceDTO.setInvoiceDate(LocalDate.now());
        invoiceDTO.setInvoiceAmount(BigDecimal.valueOf(100));
    }

    @Test
    void getAllInvoicesTest() {
        InvoiceSearchCriteria criteria = new InvoiceSearchCriteria();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Invoice> invoicePage = new PageImpl<>(Collections.singletonList(invoice));

        when(invoiceRepository.findAll(any(InvoiceSpecification.class), eq(pageable))).thenReturn(invoicePage);
        when(invoiceMapper.toInvoiceListDTO(invoice)).thenReturn(new InvoiceListDTO());

        Page<InvoiceListDTO> result = invoiceServiceImpl.getAllInvoices(pageable, criteria);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());

        verify(invoiceRepository, times(1)).findAll(any(InvoiceSpecification.class), eq(pageable));
    }

    @Test
    void getInvoiceByIdTest() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceMapper.toInvoicesDTO(invoice)).thenReturn(invoiceDTO);

        InvoiceDTO result = invoiceServiceImpl.getInvoiceById(invoiceId);

        assertNotNull(result);
        assertEquals(invoiceId, result.getId());

        verify(invoiceRepository, times(1)).findById(invoiceId);
    }

    @Test
    void addInvoiceTest() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(invoiceMapper.toInvoices(invoiceDTO)).thenReturn(invoice);
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        when(invoiceMapper.toInvoicesDTO(invoice)).thenReturn(invoiceDTO);

        InvoiceDTO result = invoiceServiceImpl.addInvoice(invoiceDTO);

        assertNotNull(result);
        assertEquals(invoiceId, result.getId());

        verify(customerRepository, times(1)).findById(customerId);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void addInvoice_CustomerNotFoundTest() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invoiceServiceImpl.addInvoice(invoiceDTO);
        });

        assertEquals("Customer not found", exception.getMessage());

        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void editInvoiceTest() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        when(invoiceMapper.toInvoicesDTO(invoice)).thenReturn(invoiceDTO);

        InvoiceDTO result = invoiceServiceImpl.editInvoice(invoiceId, invoiceDTO);

        assertNotNull(result);
        assertEquals(invoiceId, result.getId());

        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(customerRepository, times(1)).findById(customerId);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void getInvoiceDetailTest() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceMapper.toInvoiceDetailDTO(invoice)).thenReturn(new InvoiceDetailDTO());

        InvoiceDetailDTO result = invoiceServiceImpl.getInvoiceDetail(invoiceId);

        assertNotNull(result);

        verify(invoiceRepository, times(1)).findById(invoiceId);
    }
}
