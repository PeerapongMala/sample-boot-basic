package th.mfu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.*;
import th.mfu.dto.*;
import th.mfu.dto.Mapper.CustomerMapper;
import th.mfu.repository.*;
import th.mfu.security.*;
//import th.mfu.repository.CustomerRepo;

@RestController
public class CustomerCtrl {

    @Autowired
    CustomerMapper custMapper;

    @Autowired
    private CustomerRepo custRepo;

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        if (!custRepo.existsById(id)) {
            return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
        }
        Optional<Customer> customer = custRepo.findById(id);
        CustomerDTO dto = new CustomerDTO();
        custMapper.updateCustomerFromEntity(customer.get(), dto);
        return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/customers/all")
    public ResponseEntity<Collection> getAllCustomer() {
        return new ResponseEntity<Collection>(custRepo.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        if (!custRepo.existsById(id))
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        Optional<Customer> customerEnt = custRepo.findById(id);
        Customer customer = customerEnt.get();
        custMapper.updateCustomerFromDto(customerDTO, customer);
        custRepo.save(customer);
        return new ResponseEntity<String>("Customer updated", HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customer) {
        Customer newCust = new Customer();
        custMapper.updateCustomerFromDto(customer, newCust);
        if (custRepo.findByName(customer.getName()).isEmpty()) {
            custRepo.save(newCust);
            return new ResponseEntity<String>("Customer created", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Customer has already exist.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        custRepo.deleteById(id);
        return new ResponseEntity<String>("Customer deleted", HttpStatus.NO_CONTENT);
    }
}
