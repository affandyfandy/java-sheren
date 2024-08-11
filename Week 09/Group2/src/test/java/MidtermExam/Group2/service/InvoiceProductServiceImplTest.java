package MidtermExam.Group2.service;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.entity.*;
import MidtermExam.Group2.mapper.InvoiceProductMapper;
import MidtermExam.Group2.repository.InvoiceProductRepository;
import MidtermExam.Group2.repository.InvoiceRepository;
import MidtermExam.Group2.repository.ProductRepository;
import MidtermExam.Group2.service.impl.InvoiceProductServiceImpl;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceProductServiceImplTest {

    @InjectMocks
    private InvoiceProductServiceImpl invoiceProductServiceImpl;

    @Mock
    private InvoiceProductRepository invoiceProductRepository;

    @Mock
    private InvoiceProductMapper invoiceProductMapper;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private ProductRepository productRepository;

    private InvoiceProductDTO invoiceProductDTO;
    private InvoiceProduct invoiceProduct;
    private Invoice invoice;
    private Product product;
    private UUID invoiceId;
    private UUID productId;

    @BeforeEach
    void setUp() {
        invoiceId = UUID.randomUUID();
        productId = UUID.randomUUID();

        invoice = new Invoice();
        invoice.setId(invoiceId);
        invoice.setInvoiceAmount(BigDecimal.ZERO);
        invoice.setCreatedTime(LocalDateTime.now());

        product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100));
        product.setStatus(Status.ACTIVE);

        invoiceProductDTO = new InvoiceProductDTO();
        invoiceProductDTO.setInvoiceId(invoiceId);
        invoiceProductDTO.setProductId(productId);
        invoiceProductDTO.setQuantity(2);

        invoiceProduct = new InvoiceProduct();
        invoiceProduct.setId(new InvoiceProductId(invoiceId, productId));
        invoiceProduct.setInvoice(invoice);
        invoiceProduct.setProduct(product);
        invoiceProduct.setProductName("Test Product");
        invoiceProduct.setQuantity(2);
        invoiceProduct.setAmount(BigDecimal.valueOf(200));
    }

    @Test
    void getAllInvoiceProductsTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<InvoiceProduct> page = new PageImpl<>(Collections.singletonList(invoiceProduct));

        when(invoiceProductRepository.findAll(pageable)).thenReturn(page);
        when(invoiceProductMapper.toInvoiceProductDTO(invoiceProduct)).thenReturn(invoiceProductDTO);

        Page<InvoiceProductDTO> result = invoiceProductServiceImpl.getAllInvoiceProducts(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());

        verify(invoiceProductRepository, times(1)).findAll(pageable);
    }

    @Test
    void addInvoiceProductTest() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceProductMapper.toInvoiceProduct(any(InvoiceProductDTO.class), any(Invoice.class), any(Product.class)))
                .thenReturn(invoiceProduct);
        when(invoiceProductRepository.save(any(InvoiceProduct.class))).thenReturn(invoiceProduct);
        when(invoiceProductMapper.toInvoiceProductDTO(any(InvoiceProduct.class))).thenReturn(invoiceProductDTO);

        InvoiceProductDTO result = invoiceProductServiceImpl.addInvoiceProduct(invoiceProductDTO);

        assertNotNull(result);
        assertEquals(invoiceId, result.getInvoiceId());

        verify(invoiceProductRepository, times(1)).save(any(InvoiceProduct.class));
    }

    @Test
    void addInvoiceProduct_ProductNotActiveTest() {
        product.setStatus(Status.INACTIVE);

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invoiceProductServiceImpl.addInvoiceProduct(invoiceProductDTO);
        });

        assertEquals("Product is not active", exception.getMessage());

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void addInvoiceProduct_AlreadyExistsTest() {
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceProductRepository.findById(new InvoiceProductId(invoiceId, productId)))
                .thenReturn(Optional.of(invoiceProduct));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invoiceProductServiceImpl.addInvoiceProduct(invoiceProductDTO);
        });

        assertEquals("Invoice Product already exists", exception.getMessage());

        verify(invoiceProductRepository, times(1)).findById(new InvoiceProductId(invoiceId, productId));
    }

    @Test
    void editInvoiceProductTest() {
        when(invoiceProductRepository.findById(any(InvoiceProductId.class))).thenReturn(Optional.of(invoiceProduct));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(invoiceProductRepository.save(any(InvoiceProduct.class))).thenReturn(invoiceProduct);
        when(invoiceProductMapper.toInvoiceProductDTO(any(InvoiceProduct.class))).thenReturn(invoiceProductDTO);

        InvoiceProductDTO result = invoiceProductServiceImpl.editInvoiceProduct(invoiceProductDTO, invoiceId, productId);

        assertNotNull(result);
        assertEquals(invoiceId, result.getInvoiceId());

        verify(invoiceProductRepository, times(1)).save(any(InvoiceProduct.class));
    }

    @Test
    void deleteInvoiceProductTest() {
        when(invoiceProductRepository.findById(any(InvoiceProductId.class))).thenReturn(Optional.of(invoiceProduct));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        invoiceProductServiceImpl.deleteInvoiceProduct(invoiceId, productId);

        verify(invoiceProductRepository, times(1)).deleteById(new InvoiceProductId(invoiceId, productId));
    }

    @Test
    void deleteInvoiceProduct_NotFoundTest() {
        when(invoiceProductRepository.findById(any(InvoiceProductId.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invoiceProductServiceImpl.deleteInvoiceProduct(invoiceId, productId);
        });

        assertEquals("Invoice Product not found", exception.getMessage());
    }
}
