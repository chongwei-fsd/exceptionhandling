package org.example.controller;

import jakarta.validation.Valid;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Customer;
import org.example.model.Feedback;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> allCustomers(){
        List<Customer>customerList=customerService.findAll();
        if(customerList.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return new  ResponseEntity<> (customerList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveCustomer(@Valid @RequestBody Customer customer){
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long customerId, @RequestBody Customer customer){

        Customer checkCustomer = customerService.findById(customerId).map(_customer ->{
            _customer.setName(customer.getName());
            _customer.setEmail(customer.getEmail());
            _customer.setPhone(customer.getPhone());

            return customerService.save(_customer);
        }).orElseThrow(() -> new ResourceNotFoundException());

        return new ResponseEntity<>(checkCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer (@PathVariable("id") Long customerId){

        Customer checkCustomer = customerService.findById(customerId).map(_customer->{
            customerService.deleteById(_customer.getId());
            return _customer;
        }).orElseThrow(ResourceNotFoundException::new);

        String response = String.format("%s deleted successfully", checkCustomer.getName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> countCustomer (){

        long count = customerService.count();

        if(count <= 0)
            return new ResponseEntity<>("No customer found.", HttpStatus.NOT_FOUND);

        String response = String.format("Total customers: %d", count);

        Map<String, Object> totalCustomers = new HashMap<String, Object>();
        totalCustomers.put("total", count);

        return new ResponseEntity<>(totalCustomers, HttpStatus.OK);
    }

}