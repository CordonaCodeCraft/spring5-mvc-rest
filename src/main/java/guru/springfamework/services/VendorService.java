package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VendorService {

    VendorDTO createNewVendor(VendorDTO input);

    VendorDTO getVendorById(Long id);

   VendorListDTO getAllVendors();

    VendorDTO updateVendor(Long id, VendorDTO input);

    VendorDTO patchVendor(Long id, VendorDTO input);

    Void deleteVendorById(Long id);


}
