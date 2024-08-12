package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.ProductDTO;
import MidtermExam.Group2.entity.Product;
import MidtermExam.Group2.entity.Status;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Test
    void toDTOTest() {
        UUID id = UUID.randomUUID();
        Product product = Product.builder()
                .id(id)
                .name("Product A")
                .price(new BigDecimal("10.00"))
                .status(Status.ACTIVE)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();

        ProductDTO productDTO = productMapper.toDTO(product);

        assertNotNull(productDTO);
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getStatus().name(), productDTO.getStatus());
    }

    @Test
    void toEntityTest() {
        UUID id = UUID.randomUUID();
        ProductDTO productDTO = new ProductDTO(
                id,
                "Product A",
                new BigDecimal("10.00"),
                "ACTIVE"
        );

        Product product = productMapper.toEntity(productDTO);

        assertNotNull(product);
        assertEquals(productDTO.getId(), product.getId());
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getPrice(), product.getPrice());
        assertEquals(Status.ACTIVE, product.getStatus());
    }
}
