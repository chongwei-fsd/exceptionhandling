package org.example.service;

import org.example.model.Customer;
import org.example.model.Feedback;
import org.example.repository.CustomerRepository;
import org.example.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer){

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll(){

        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id){

        return customerRepository.findById(id);
    }

    @Override
    public Customer update(Customer customer){

        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id)
    {
        customerRepository.deleteById(id);
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

}
