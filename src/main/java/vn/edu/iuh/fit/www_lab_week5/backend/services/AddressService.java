package vn.edu.iuh.fit.www_lab_week5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Address;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.AddressRepository;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    public List<Address> findAll(){
        return addressRepository.findAll();
    }
}
