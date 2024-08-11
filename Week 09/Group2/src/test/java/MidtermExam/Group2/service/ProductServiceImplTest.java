package MidtermExam.Group2.service;

import MidtermExam.Group2.dto.ProductDTO;
import MidtermExam.Group2.entity.Product;
import MidtermExam.Group2.entity.Status;
import MidtermExam.Group2.mapper.ProductMapper;
import MidtermExam.Group2.repository.ProductRepository;
import MidtermExam.Group2.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductServiceImpl.class})
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductMapper productMapper;

    private ProductDTO productDTO;
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Test Product");
        product.setPrice(new BigDecimal("10.00"));
        product.setStatus(Status.ACTIVE);

        productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName("Test Product Updated");
        productDTO.setPrice(BigDecimal.valueOf(100.0));
        productDTO.setStatus(product.getStatus().toString());
    }

    @Test
    void getAllProducts_ReturnsProductsTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(Collections.singletonList(product));

        when(productRepository.findAll(pageable)).thenReturn(products);
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        Page<ProductDTO> result = productService.getAllProducts(pageable);

        assertEquals(1, result.getTotalElements());

        verify(productRepository, times(1)).findAll(pageable);
        verify(productMapper, times(1)).toDTO(product);
    }

    @Test
    void getProductById_ReturnsProductTest() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        Optional<ProductDTO> result = productService.getProductById(product.getId());

        assertTrue(result.isPresent());

        verify(productRepository, times(1)).findById(product.getId());
        verify(productMapper, times(1)).toDTO(product);
    }

    @Test
    void createProduct_SavesAndReturnsProductTest() {
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.createProduct(productDTO);

        assertEquals(productDTO, result);

        verify(productMapper, times(1)).toEntity(productDTO);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).toDTO(product);
    }

    @Test
    void testUpdateProduct_SuccessTest() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        Optional<ProductDTO> updatedProductDTO = productService.updateProduct(product.getId(), productDTO);

        assertTrue(updatedProductDTO.isPresent());
        assertEquals(productDTO.getName(), updatedProductDTO.get().getName());
        assertEquals(productDTO.getPrice(), updatedProductDTO.get().getPrice());

        verify(productRepository, times(1)).findById(product.getId());
        verify(productMapper, times(1)).toEntity(productDTO);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).toDTO(product);
    }

    @Test
    void testUpdateProduct_ProductNotFoundTest() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        Optional<ProductDTO> result = productService.updateProduct(product.getId(), productDTO);

        assertFalse(result.isPresent());

        verify(productRepository, times(1)).findById(product.getId());
        verify(productMapper, never()).toEntity(productDTO);
        verify(productRepository, never()).save(product);
        verify(productMapper, never()).toDTO(product);
    }

    @Test
    void toggleProductStatus_TogglesStatusAndReturnsProductTest() {
        product.setStatus(Status.ACTIVE);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        Optional<ProductDTO> result = productService.toggleProductStatus(product.getId());

        assertTrue(result.isPresent());
        assertEquals(Status.INACTIVE, product.getStatus());

        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).toDTO(product);
    }

    @Test
    void toggleProductStatus_ProductNotFoundTest() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        Optional<ProductDTO> result = productService.toggleProductStatus(product.getId());

        assertFalse(result.isPresent());

        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository,never()).save(product);
        verify(productMapper, never()).toDTO(product);
    }

    @Test
    void deleteProduct_DeletesProductTest() {
        doNothing().when(productRepository).deleteById(product.getId());

        productService.deleteProduct(product.getId());

        verify(productRepository, times(1)).deleteById(product.getId());
    }

    @Test
    void testImportProductsFromCsv_SuccessTest() throws IOException {
        String csvContent = "Test Product,10.00,ACTIVE\nAnother Product,20.00,INACTIVE";
        MultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", csvContent.getBytes());

        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);

        productService.importProductsFromCsv(file);

        verify(productRepository, times(2)).save(any(Product.class));
    }

    @Test
    void testImportProductsFromCsv_FailDueToIOExceptionTest() throws IOException {
        MultipartFile file = mock(MultipartFile.class);

        when(file.getInputStream()).thenThrow(new IOException("Mock IOException"));

        assertThrows(IOException.class, () -> productService.importProductsFromCsv(file));

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testSearchProducts_WithNameAndStatusTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByNameContainingIgnoreCaseAndStatus(anyString(), any(Status.class), any(Pageable.class)))
                .thenReturn(productPage);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        Page<ProductDTO> result = productService.searchProducts("Test", Status.ACTIVE, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(productDTO.getName(), result.getContent().get(0).getName());

        verify(productRepository, times(1)).findByNameContainingIgnoreCaseAndStatus("Test", Status.ACTIVE, pageable);
    }

    @Test
    void testSearchProducts_WithNameOnlyTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByNameContainingIgnoreCase(anyString(), any(Pageable.class)))
                .thenReturn(productPage);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        Page<ProductDTO> result = productService.searchProducts("Test", null, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(productDTO.getName(), result.getContent().get(0).getName());

        verify(productRepository, times(1)).findByNameContainingIgnoreCase("Test", pageable);
    }

    @Test
    void testSearchProducts_WithStatusOnlyTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByStatus(any(Status.class), any(Pageable.class)))
                .thenReturn(productPage);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        Page<ProductDTO> result = productService.searchProducts(null, Status.ACTIVE, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(productDTO.getName(), result.getContent().get(0).getName());

        verify(productRepository, times(1)).findByStatus(Status.ACTIVE, pageable);
    }

    @Test
    void testSearchProducts_WithoutFiltersTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findAll(any(Pageable.class))).thenReturn(productPage);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        Page<ProductDTO> result = productService.searchProducts(null, null, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(productDTO.getName(), result.getContent().get(0).getName());

        verify(productRepository, times(1)).findAll(pageable);
    }
}
