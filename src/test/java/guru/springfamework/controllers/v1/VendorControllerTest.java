package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.controllers.RestResponseEntityExceptionHandler;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;


public class VendorControllerTest {

    @Mock
    VendorService vendorService;
    VendorController controller;
    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        controller = new VendorController(vendorService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();

    }

    @Test
    public void getAllVendors() throws Exception {

        VendorListDTO vendors = new VendorListDTO(Arrays.asList(new VendorDTO(), new VendorDTO(), new VendorDTO()));

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc
                .perform(get(VendorController.VENDOR_BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(3)));
    }

    @Test
    public void findById() throws Exception {

        VendorDTO input = VendorDTO.builder().name("Vendor").build();

        when(vendorService.getVendorById(anyLong())).thenReturn(input);

        mockMvc.perform(get(VendorController.VENDOR_BASE_URL + 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.name", equalTo("Vendor")));
    }

    @Test
    public void createNewVendor() throws Exception {

        VendorDTO input = VendorDTO
                .builder()
                .name("Vendor")
                .vendorUrl(VendorController.VENDOR_BASE_URL + 1)
                .build();

        given(vendorService.createNewVendor(input)).willReturn(input);
        
        mockMvc.perform(post(VendorController.VENDOR_BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Vendor")));

    }

    @Test
    public void updateVendor() {
    }

    @Test
    public void deleteByID() {
    }
}