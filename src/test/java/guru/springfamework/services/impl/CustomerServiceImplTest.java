package guru.springfamework.services.impl;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    private final Long ID = 1L;

    CustomerServiceImpl customerService;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Mock
    CustomerRepository customerRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }


    @Test
    public void createNewCustomer() {

        CustomerDTO customerDTO = CustomerDTO
                .builder()
                .firstName("Endios")
                .lastName("Deloro")
                .build();

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO savedCustomer = customerService.createNewCustomer(customerDTO);

        assertEquals(customerDTO.getFirstName(), savedCustomer.getFirstName());

        assertEquals("/api/v1/customers/1",savedCustomer.getCustomerUrl());
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
        Customer customer = Customer.builder().id(ID).firstName("Michale").build();

        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals("Michale", customerDTO.getFirstName());
    }

    @Test
    public void testWrongIdReturnsNull() {
        Customer customer = Customer.builder().id(ID).build();

        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(2L);

        assertNull(customerDTO);
    }


}