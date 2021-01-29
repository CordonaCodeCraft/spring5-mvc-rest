package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VendorController.VENDOR_BASE_URL)
public class VendorController {

    private final VendorService vendorService;

    public static final String VENDOR_BASE_URL = "/api/v1/vendors/";

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public VendorDTO findById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO input) {
        return vendorService.createNewVendor(input);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO input) {
        return vendorService.updateVendor(id, input);
    }

    @DeleteMapping("{id}")
    public Void deleteByID(@PathVariable Long id) {
        return vendorService.deleteVendorById(id);
    }


}
