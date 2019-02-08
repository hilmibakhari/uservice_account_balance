package com.hb.customerservice.controller;

import com.hb.customerservice.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customerList = new ArrayList<>(5);

    public CustomerController() {
        this.customerList.add(new Customer(1L, "Jacque Chirac", "Paris, France"));
        this.customerList.add(new Customer(2L, "Tadhg Sullivan", "Cork, Ireland"));
        this.customerList.add(new Customer(3L, "Ahmad Maslan", "Johor, Malaysia"));
    }

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return this.customerList;
    }

    @GetMapping("/{customerId}")
    public Customer getACustomer(@PathVariable Long customerId) {
        return this.customerList.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findAny()
                .orElse(null);
    }
}
