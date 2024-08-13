package MidtermExam.Group2.service;

import MidtermExam.Group2.dto.CustomerDTO;
import MidtermExam.Group2.entity.Customer;
import MidtermExam.Group2.entity.Status;
import MidtermExam.Group2.mapper.CustomerMapper;
import MidtermExam.Group2.repository.CustomerRepository;
import MidtermExam.Group2.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Test
    void getAllCustomers_ReturnsCustomersTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Customer customer = new Customer();
        CustomerDTO customerDTO = new CustomerDTO();
        Page<Customer> customers = new PageImpl<>(Collections.singletonList(customer));

        when(customerRepository.findAll(pageable)).thenReturn(customers);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        Page<CustomerDTO> result = customerService.getAllCustomers(pageable);

        assertEquals(1, result.getTotalElements());

        verify(customerRepository, times(1)).findAll(pageable);
        verify(customerMapper, times(1)).toDTO(customer);
    }

    @Test
    void getCustomerById_ReturnsCustomerTest() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer();
        CustomerDTO customerDTO = new CustomerDTO();

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        Optional<CustomerDTO> result = customerService.getCustomerById(id);

        assertTrue(result.isPresent());

        verify(customerRepository, times(1)).findById(id);
        verify(customerMapper, times(1)).toDTO(customer);
    }

    @Test
    void createCustomer_SavesAndReturnsCustomerTest() {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = new Customer();

        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.createCustomer(customerDTO);

        assertEquals(customerDTO, result);

        verify(customerMapper, times(1)).toEntity(customerDTO);
        verify(customerRepository, times(1)).save(customer);
        verify(customerMapper, times(1)).toDTO(customer);
    }

    @Test
    void editCustomer_UpdatesAndReturnsCustomerTest() {
        UUID id = UUID.randomUUID();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Updated Name");
        Customer customer = new Customer();

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        Optional<CustomerDTO> result = customerService.editCustomer(id, customerDTO);

        assertTrue(result.isPresent());
        assertEquals("Updated Name", customer.getName());

        verify(customerRepository, times(1)).findById(id);
        verify(customerRepository, times(1)).save(customer);
        verify(customerMapper, times(1)).toDTO(customer);
    }

    @Test
    void changeCustomerStatus_TogglesStatusAndReturnsCustomerTest() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setStatus(Status.ACTIVE);
        CustomerDTO customerDTO = new CustomerDTO();

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        Optional<CustomerDTO> result = customerService.changeCustomerStatus(id);

        assertTrue(result.isPresent());
        assertEquals(Status.INACTIVE, customer.getStatus());

        verify(customerRepository, times(1)).findById(id);
        verify(customerRepository, times(1)).save(customer);
        verify(customerMapper, times(1)).toDTO(customer);
    }

    @Test
    void changeCustomerStatus_CustomerNotFoundTest() {
        UUID id = UUID.randomUUID();

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CustomerDTO> result = customerService.changeCustomerStatus(id);

        assertFalse(result.isPresent());

        verify(customerRepository, times(1)).findById(id);
        verify(customerRepository, never()).save(any(Customer.class));
        verify(customerMapper, never()).toDTO(any(Customer.class));
    }

    @Test
    void deleteCustomer_DeletesCustomerTest() {
        UUID id = UUID.randomUUID();

        doNothing().when(customerRepository).deleteById(id);

        customerService.deleteCustomer(id);

        verify(customerRepository, times(1)).deleteById(id);
    }
}
