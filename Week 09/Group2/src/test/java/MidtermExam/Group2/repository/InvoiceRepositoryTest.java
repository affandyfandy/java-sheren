package MidtermExam.Group2.repository;

import MidtermExam.Group2.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InvoiceProductRepository invoiceProductRepository;

    private Invoice invoice;
    private Customer customer;
    private Product product;
    private InvoiceProduct invoiceProduct;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("Customer A");
        customer.setPhoneNumber("+628123428789");
        customer.setStatus(Status.ACTIVE);
        customerRepository.save(customer);

        product = new Product();
        product.setName("Product A");
        product.setPrice(BigDecimal.valueOf(20.00));
        product.setStatus(Status.ACTIVE);
        productRepository.save(product);

        invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceAmount(BigDecimal.valueOf(0));
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setInvoiceProducts(Collections.emptyList());
        invoiceRepository.save(invoice);

        invoiceProduct = new InvoiceProduct();
        invoiceProduct.setInvoice(invoice);
        invoiceProduct.setProduct(product);
        invoiceProduct.setProductName(product.getName());
        invoiceProduct.setQuantity(1);
        invoiceProduct.setAmount(BigDecimal.valueOf(20.00));
        invoiceProductRepository.save(invoiceProduct);
    }

    @AfterEach
    void tearDown() {
        invoiceProductRepository.deleteAll();
        customerRepository.deleteAll();
        invoiceRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void findAllWithPaginationTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Invoice> invoices = invoiceRepository.findAll(pageable);

        assertThat(invoices).isNotNull();
        assertThat(invoices.getContent()).hasSize(1).contains(invoice);
    }

    @Test
    void findInvoiceByIdTest() {
        Invoice result = invoiceRepository.save(invoice);

        Optional<Invoice> foundInvoice = invoiceRepository.findById(invoice.getId());

        assertThat(foundInvoice).isNotNull().contains(result);
    }

    @Test
    void findByCustomerAndDateTest() {
        Invoice result = invoiceRepository.save(invoice);

        List<Invoice> invoices = invoiceRepository.findByCustomerAndDate(customer.getId(), invoice.getInvoiceDate().getMonthValue(), invoice.getInvoiceDate().getYear());

        assertThat(invoices).isNotEmpty().contains(result);
    }

    @Test
    void findByCustomerIdTest() {
        Invoice result = invoiceRepository.save(invoice);

        List<Invoice> invoices = invoiceRepository.findByCustomerId(customer.getId());

        assertThat(invoices).isNotEmpty().contains(result);
    }

    @Test
    void calculateTotalRevenueByDateTimeTest() {
        Invoice result = invoiceRepository.save(invoice);

        LocalDateTime startDateTime = result.getInvoiceDate().minusDays(1);
        LocalDateTime endDateTime = result.getInvoiceDate().plusDays(1);

        BigDecimal totalRevenue = invoiceRepository.calculateTotalRevenueByDateTime(startDateTime, endDateTime);

        assertThat(totalRevenue).isNotNull().isEqualTo(result.getInvoiceAmount().setScale(2));
    }

    @Test
    void saveInvoiceTest() {
        Invoice newInvoice = new Invoice();
        newInvoice.setCustomer(customer);
        newInvoice.setInvoiceAmount(BigDecimal.valueOf(100.00));
        newInvoice.setInvoiceDate(LocalDateTime.now());
        newInvoice.setInvoiceProducts(Collections.emptyList());

        Invoice savedInvoice = invoiceRepository.save(newInvoice);

        assertThat(savedInvoice).isNotNull();
        assertThat(savedInvoice.getId()).isNotNull();
        assertThat(savedInvoice.getInvoiceAmount()).isEqualByComparingTo(BigDecimal.valueOf(100.00));
    }

    @Test
    void deleteInvoiceTest() {
        UUID invoiceId = invoice.getId();
        invoiceRepository.delete(invoice);

        Optional<Invoice> deletedInvoice = invoiceRepository.findById(invoiceId);

        assertThat(deletedInvoice).isEmpty();
    }
}
