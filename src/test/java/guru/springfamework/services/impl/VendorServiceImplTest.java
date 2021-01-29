package guru.springfamework.services.impl;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    public static final long ID = 1L;
    public static final String VENDOR_NAME = "Vendor";

    @Mock
    VendorRepository vendorRepository;
    VendorMapper vendorMapper;
    VendorServiceImpl vendorService;
    Vendor vendor;
    VendorDTO input;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        vendorMapper = VendorMapper.INSTANCE;

        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);

        vendor = Vendor
                .builder()
                .id(ID)
                .name(VENDOR_NAME)
                .build();

        input = VendorDTO
                .builder()
                .name(VENDOR_NAME)
                .build();
    }

    @Test
    public void createNewVendor() {

        Vendor vendor = vendorMapper.DtoToVendor(input);
        vendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO output = vendorService.createNewVendor(input);

        assertEquals(output.getVendorUrl(), VendorController.VENDOR_BASE_URL + vendor.getId());
        assertEquals(output.getName(), VENDOR_NAME);
        verify(vendorRepository, times(1)).save(any(Vendor.class));
    }

    @Test
    public void getVendorById() {

        when(vendorRepository.findById(ID)).thenReturn(Optional.of(vendor));

        VendorDTO output = vendorService.getVendorById(ID);

        assertEquals(output.getVendorUrl(), VendorController.VENDOR_BASE_URL + ID);
        assertEquals(output.getName(), VENDOR_NAME);
        verify(vendorRepository, times(1)).findById(anyLong());
    }


    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowExceptionWhenGetVendorByIdNotPresent() {

        when(vendorRepository.findById(ID)).thenReturn(Optional.of(vendor));

        VendorDTO output = vendorService.getVendorById(2L);
    }


    @Test
    public void getAllVendors() {

        Vendor first = Vendor.builder().id(1L).name("Foo").build();
        Vendor second = Vendor.builder().id(2L).name("Boo").build();
        Vendor third = Vendor.builder().id(3L).name("Too").build();

        List<Vendor> vendors = Arrays.asList(first, second, third);

        when(vendorRepository.findAll()).thenReturn(vendors);

        VendorListDTO output = vendorService.getAllVendors();

        assertEquals(output.getVendors().size(), 3);
        verify(vendorRepository, times(1)).findAll();
    }

    @Test
    public void putVendor() {

        VendorDTO newInput = VendorDTO
                .builder()
                .name("UpdatedName")
                .build();

        when(vendorRepository.findById(ID)).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorMapper.DtoToVendor(newInput));

        VendorDTO output = vendorService.updateVendor(ID, newInput);

        assertEquals(output.getName(),newInput.getName());
    }

    @Test
    public void patchVendor() {
    }

    @Test
    public void deleteVendorById() {
        vendorService.deleteVendorById(ID);
        verify(vendorRepository,times(1)).deleteById(anyLong());

    }
}