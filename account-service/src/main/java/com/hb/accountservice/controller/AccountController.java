package com.hb.accountservice.controller;

import com.hb.accountservice.model.Account;
import com.hb.accountservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("accounts")
public class AccountController {

    private List<Account> accountList = new ArrayList<>(5);

    @Autowired
    private RestTemplate restTemplate;

    public AccountController() {
        this.accountList.add(new Account(1L, null, 210.0));
        this.accountList.add(new Account(2L, null, 440.0));
        this.accountList.add(new Account(3L, null, 192.0));
    }

    @GetMapping("")
    public List<Account> getAllAccounts() {
        return this.accountList;
    }

    @GetMapping("/{accountId}")
    public Account getAnAccount(@PathVariable Long accountId) {
        Account specifiedAccount =  this.accountList.stream()
                .filter(account -> account.getId().equals(accountId))
                .findAny()
                .orElse(null);

        Map<String, Long> parameters = new HashMap<>();
        parameters.put("customerId", specifiedAccount.getId());

        Customer customer = restTemplate.getForObject("http://localhost:8100/customers/{customerId}", Customer.class, parameters);

        System.out.println("Customer found: " + customer.getName());

        specifiedAccount.setCustomer(customer);

        return specifiedAccount;
    }

}
