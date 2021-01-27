package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CustomerControllerTest {

    @Mock
    CustomerService customerService;
    CustomerController controller;
    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new CustomerController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetCustomersList() throws Exception {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc
                .perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDTO customer = CustomerDTO.builder().firstName("Jhon").build();

        when(customerService.findById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Jhon")));


    }
}