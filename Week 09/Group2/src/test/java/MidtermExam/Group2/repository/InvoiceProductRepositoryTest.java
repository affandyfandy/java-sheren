package MidtermExam.Group2.repository;

import MidtermExam.Group2.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class InvoiceProductRepositoryTest {

    @Autowired
    InvoiceProductRepository invoiceProductRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProductRepository productRepository;

    private Invoice invoice;
    private Product product;
    private InvoiceProduct invoiceProduct;
    private Customer customer;

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
    }

    @AfterEach
    void tearDown() {
        invoiceProductRepository.deleteAll();
        customerRepository.deleteAll();
        invoiceRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void findByIdTest() {
        invoiceProductRepository.save(invoiceProduct);

        InvoiceProductId id = new InvoiceProductId(invoice.getId(), product.getId());
        Optional<InvoiceProduct> foundInvoiceProduct = invoiceProductRepository.findById(id);

        assertThat(foundInvoiceProduct).isPresent();
        assertThat(foundInvoiceProduct.get().getInvoice().getId()).isEqualTo(invoice.getId());
        assertThat(foundInvoiceProduct.get().getProduct().getId()).isEqualTo(product.getId());
        assertThat(foundInvoiceProduct.get().getProductName()).isEqualTo("Product A");
        assertThat(foundInvoiceProduct.get().getQuantity()).isEqualTo(1);
        assertThat(foundInvoiceProduct.get().getAmount()).isEqualByComparingTo(BigDecimal.valueOf(20.00));
    }

    @Test
    void deleteInvoiceProductTest() {
        invoiceProductRepository.save(invoiceProduct);

        InvoiceProductId id = new InvoiceProductId(invoice.getId(), product.getId());
        invoiceProductRepository.deleteById(id);

        Optional<InvoiceProduct> foundInvoiceProduct = invoiceProductRepository.findById(id);
        assertThat(foundInvoiceProduct).isNotPresent();
    }

    @Test
    void calculateTotalAmountByInvoiceIdTest() {
        invoiceProductRepository.save(invoiceProduct);

        BigDecimal totalAmount = invoiceProductRepository.calculateTotalAmountByInvoiceId(invoice.getId());

        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isEqualByComparingTo(BigDecimal.valueOf(20.00));
    }
}
