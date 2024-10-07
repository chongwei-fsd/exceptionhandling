package org.example.repository;

import org.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
// save() -- save() method is also equivalent to performing an update
// findOne()
// findById()
// findByEmail()
// findAll()
// count()
// delete()
// deleteById()

}

