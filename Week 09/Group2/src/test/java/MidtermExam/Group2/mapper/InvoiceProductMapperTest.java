package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.InvoiceProductDTO;
import MidtermExam.Group2.dto.InvoiceProductWithoutProductIdDTO;
import MidtermExam.Group2.entity.Invoice;
import MidtermExam.Group2.entity.InvoiceProduct;
import MidtermExam.Group2.entity.Product;
import MidtermExam.Group2.entity.Status;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceProductMapperTest {

    private final InvoiceProductMapper invoiceProductMapper = Mappers.getMapper(InvoiceProductMapper.class);

    @Test
    void toInvoiceProductDTOTest() {
        Invoice invoice = Invoice.builder()
                .id(UUID.randomUUID())
                .build();
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name("Product A")
                .price(new BigDecimal("20.00"))
                .status(Status.ACTIVE)
                .build();
        InvoiceProduct invoiceProduct = InvoiceProduct.builder()
                .invoice(invoice)
                .product(product)
                .productName(product.getName())
                .quantity(10)
                .amount(new BigDecimal("200.00"))
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();

        InvoiceProductDTO invoiceProductDTO = invoiceProductMapper.toInvoiceProductDTO(invoiceProduct);

        assertNotNull(invoiceProductDTO);
        assertEquals(invoice.getId(), invoiceProductDTO.getInvoiceId());
        assertEquals(product.getId(), invoiceProductDTO.getProductId());
        assertEquals(product.getName(), invoiceProductDTO.getProductName());
        assertEquals(invoiceProduct.getQuantity(), invoiceProductDTO.getQuantity());
        assertEquals(invoiceProduct.getAmount(), invoiceProductDTO.getAmount());
    }

    @Test
    void toInvoiceProductTest() {
        UUID invoiceId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Invoice invoice = Invoice.builder()
                .id(invoiceId)
                .build();
        Product product = Product.builder()
                .id(productId)
                .name("Product A")
                .price(new BigDecimal("20.00"))
                .status(Status.ACTIVE)
                .build();
        InvoiceProductDTO invoiceProductDTO = new InvoiceProductDTO(
                invoiceId,
                productId,
                "Product A",
                10,
                new BigDecimal("200.00")
        );

        InvoiceProduct invoiceProduct = invoiceProductMapper.toInvoiceProduct(invoiceProductDTO, invoice, product);

        assertNotNull(invoiceProduct);
        assertEquals(invoice.getId(), invoiceProduct.getInvoice().getId());
        assertEquals(product.getId(), invoiceProduct.getProduct().getId());
        assertEquals(invoiceProductDTO.getProductName(), invoiceProduct.getProductName());
        assertEquals(invoiceProductDTO.getQuantity(), invoiceProduct.getQuantity());
        assertEquals(invoiceProductDTO.getAmount(), invoiceProduct.getAmount());
    }

    @Test
    void toInvoiceProductWithoutIdDTOTest() {
        Invoice invoice = Invoice.builder()
                .id(UUID.randomUUID())
                .build();
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name("Product A")
                .price(new BigDecimal("20.00"))
                .status(Status.ACTIVE)
                .build();
        InvoiceProduct invoiceProduct = InvoiceProduct.builder()
                .invoice(invoice)
                .product(product)
                .productName(product.getName())
                .quantity(10)
                .amount(new BigDecimal("200.00"))
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();

        InvoiceProductWithoutProductIdDTO dto = invoiceProductMapper.toInvoiceProductWithoutIdDTO(invoiceProduct);

        assertNotNull(dto);
        assertEquals(invoice.getId(), dto.getInvoiceId());
        assertEquals(product.getName(), dto.getProductName());
        assertEquals(invoiceProduct.getQuantity(), dto.getQuantity());
        assertEquals(invoiceProduct.getAmount(), dto.getAmount());
        assertEquals(product.getPrice(), dto.getPrice());
    }

    @Test
    void toInvoiceProductWithoutProductIdDTOTest() {
        UUID invoiceId = UUID.randomUUID();
        Invoice invoice = Invoice.builder()
                .id(invoiceId)
                .build();
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name("Product A")
                .price(new BigDecimal("20.00"))
                .status(Status.ACTIVE)
                .build();
        InvoiceProductWithoutProductIdDTO dto = new InvoiceProductWithoutProductIdDTO(
                invoiceId,
                "Product A",
                new BigDecimal("20.00"),
                10,
                new BigDecimal("200.00")
        );

        InvoiceProduct invoiceProduct = invoiceProductMapper.toInvoiceProduct(dto, invoice, product);

        assertNotNull(invoiceProduct);
        assertEquals(invoice.getId(), invoiceProduct.getInvoice().getId());
        assertEquals(dto.getProductName(), invoiceProduct.getProductName());
        assertEquals(dto.getQuantity(), invoiceProduct.getQuantity());
        assertEquals(dto.getAmount(), invoiceProduct.getAmount());
        assertEquals(dto.getPrice(), invoiceProduct.getProduct().getPrice());
    }
}
