package guru.springfamework.services.impl;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    private final Long ID = 1L;

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(customers.size(), customerDTOS.size());
    }

    @Test
    public void testFindById() {
        Customer customer = Customer.builder().id(ID).build();

        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.findById(ID);

//        assertEquals(ID, customerDTO.getId());
    }

    @Test
    public void testWrongIdReturnsNull() {
        Customer customer = Customer.builder().id(ID).build();

        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.findById(2L);

        assertNull(customerDTO);
    }
}