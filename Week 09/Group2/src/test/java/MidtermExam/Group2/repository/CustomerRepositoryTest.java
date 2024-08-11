package MidtermExam.Group2.repository;

import MidtermExam.Group2.entity.Customer;
import MidtermExam.Group2.entity.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("Customer A");
        customer.setPhoneNumber("+628123428789");
        customer.setStatus(Status.ACTIVE);
        customerRepository.save(customer);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void findAllCustomersTest() {
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).isNotNull();
        assertThat(customers.size()).isGreaterThan(0);

        customers.forEach(c -> System.out.println("Found customer: " + c));
    }

    @Test
    void findByIdTest() {
        Optional<Customer> foundCustomer = customerRepository.findById(customer.getId());
        assertThat(foundCustomer).isPresent();
        assertThat(foundCustomer.get().getName()).isEqualTo("Customer A");

        foundCustomer.ifPresent(c -> System.out.println("Found customer: " + c));
    }

    @Test
    void saveCustomerTest() {
        Customer savedCust = new Customer();
        savedCust.setName("Customer B");
        savedCust.setPhoneNumber("+62812345678");
        savedCust.setStatus(Status.ACTIVE);
        Customer result = customerRepository.save(savedCust);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("Customer B");
        assertThat(result.getPhoneNumber()).isEqualTo("+62812345678");
        assertThat(result.getStatus()).isEqualTo(Status.ACTIVE);

        System.out.println("Saved customer: " + result);
    }

    @Test
    void updateCustomerTest() {
        customer.setName("Updated Customer");
        Customer updatedCustomer = customerRepository.save(customer);

        assertThat(updatedCustomer.getName()).isEqualTo("Updated Customer");
        assertThat(updatedCustomer.getId()).isEqualTo(customer.getId());

        System.out.println("Updated customer: " + updatedCustomer);
    }

    @Test
    void deleteCustomerTest() {
        UUID customerId = customer.getId();
        customerRepository.deleteById(customerId);

        Optional<Customer> deletedCust = customerRepository.findById(customerId);
        assertThat(deletedCust).isNotPresent();

        System.out.println("Deleted customer with ID: " + customerId);
    }
}
