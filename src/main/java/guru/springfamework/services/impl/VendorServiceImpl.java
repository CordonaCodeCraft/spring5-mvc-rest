package guru.springfamework.services.impl;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.services.ResourceNotFoundException;
import guru.springfamework.services.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }


    @Override
    public VendorDTO createNewVendor(VendorDTO input) {

        Vendor vendor = vendorMapper.DtoToVendor(input);

        Vendor entity = vendorRepository.save(vendor);

        return buildDtoOutput(entity);
    }


    @Override
    public VendorDTO getVendorById(Long id) {

        Vendor entity = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return buildDtoOutput(entity);
    }

    @Override
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorRepository
                .findAll()
                .stream()
                .map(this::buildDtoOutput)
                .collect(Collectors.toList()));
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO input) {

        Vendor entity = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        Vendor updatedEntity = vendorMapper.DtoToVendor(input);
        updatedEntity.setId(entity.getId());

        Vendor savedEntity = vendorRepository.save(updatedEntity);

        return buildDtoOutput(savedEntity);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO input) {
        return null;
    }

    @Override
    public Void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
        return null;
    }

    private VendorDTO buildDtoOutput(Vendor entity) {
        VendorDTO output = vendorMapper.vendorToDto(entity);
        output.setVendorUrl(VendorController.VENDOR_BASE_URL + entity.getId());
        return output;
    }

}
