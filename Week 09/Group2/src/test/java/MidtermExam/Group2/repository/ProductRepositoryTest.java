package MidtermExam.Group2.repository;

import MidtermExam.Group2.entity.Product;
import MidtermExam.Group2.entity.Status;
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
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setName("Product A");
        product.setPrice(BigDecimal.valueOf(20.00));
        product.setStatus(Status.ACTIVE);
        productRepository.save(product);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void findByIdTest() {
        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Product A");

        foundProduct.ifPresent(p -> System.out.println("Found product: " + p));
    }

    @Test
    void saveProductTest() {
        Product savedProd = new Product();
        savedProd.setName("Product B");
        savedProd.setPrice(BigDecimal.valueOf(30.00));
        savedProd.setStatus(Status.ACTIVE);
        Product result = productRepository.save(savedProd);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("Product B");
        assertThat(result.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(30.00));
        assertThat(result.getStatus()).isEqualTo(Status.ACTIVE);

        System.out.println("Saved product: " + result);
    }

    @Test
    void updateProductTest() {
        product.setName("Product A Update");
        product.setPrice(BigDecimal.valueOf(50.00));
        Product updatedProduct = productRepository.save(product);

        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getId()).isEqualTo(product.getId());
        assertThat(updatedProduct.getName()).isEqualTo("Product A Update");
        assertThat(updatedProduct.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(50.00));

        System.out.println("Updated product: " + updatedProduct);
    }

    @Test
    void deleteProductTest() {
        UUID productId = product.getId();
        productRepository.deleteById(productId);

        Optional<Product> deletedProd = productRepository.findById(productId);
        assertThat(deletedProd).isNotPresent();

        System.out.println("Deleted product with ID: " + productId);
    }

    @Test
    void findByNameContainingIgnoreCaseTest() {
        Product product1 = new Product();
        product1.setName("Test Product One");
        product1.setPrice(BigDecimal.valueOf(49.99));
        product1.setStatus(Status.ACTIVE);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Another Test Product");
        product2.setPrice(BigDecimal.valueOf(59.99));
        product2.setStatus(Status.ACTIVE);
        productRepository.save(product2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> foundProducts = productRepository.findByNameContainingIgnoreCase("test", pageable);

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.getContent().size()).isEqualTo(2);

        foundProducts.forEach(p -> System.out.println("Found product by name: " + p));
        }

    @Test
    void findByStatusTest() {
        Product product = new Product();
        product.setName("Status Test Product");
        product.setPrice(BigDecimal.valueOf(69.99));
        product.setStatus(Status.INACTIVE);
        productRepository.save(product);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> foundProducts = productRepository.findByStatus(Status.INACTIVE, pageable);

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.getContent().size()).isEqualTo(1);

        foundProducts.forEach(p -> System.out.println("Found Product by Status: " + p));
    }

    @Test
    void findByNameContainingIgnoreCaseAndStatusTest() {
        Product product = new Product();
        product.setName("Test Status Product");
        product.setPrice(BigDecimal.valueOf(79.99));
        product.setStatus(Status.ACTIVE);
        productRepository.save(product);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> foundProducts = productRepository.findByNameContainingIgnoreCaseAndStatus("status", Status.ACTIVE, pageable);

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.getContent().size()).isEqualTo(1);

        foundProducts.forEach(p -> System.out.println("Found Product by Name and Status: " + p));
    }
}
