package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.CustomerDTO;
import MidtermExam.Group2.entity.Customer;
import MidtermExam.Group2.entity.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void toDTOTest() {
        UUID id = UUID.randomUUID();
        Customer customer = Customer.builder()
                .id(id)
                .name("Customer A")
                .phoneNumber("+628172827492")
                .status(Status.ACTIVE)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        assertNotNull(customerDTO);
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getName(), customerDTO.getName());
        assertEquals(customer.getPhoneNumber(), customerDTO.getPhoneNumber());
        assertEquals(customer.getStatus().name(), customerDTO.getStatus());
    }

    @Test
    void toEntityTest() {
        UUID id = UUID.randomUUID();
        CustomerDTO customerDTO = new CustomerDTO(
                id,
                "Customer A",
                "+628172827492",
                "ACTIVE"
        );

        Customer customer = customerMapper.toEntity(customerDTO);

        assertNotNull(customer);
        assertEquals(customerDTO.getId(), customer.getId());
        assertEquals(customerDTO.getName(), customer.getName());
        assertEquals(customerDTO.getPhoneNumber(), customer.getPhoneNumber());
        assertEquals(Status.ACTIVE, customer.getStatus());
    }
}
