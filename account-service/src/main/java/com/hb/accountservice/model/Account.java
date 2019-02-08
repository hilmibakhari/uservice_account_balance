package com.hb.accountservice.model;

public class Account {
    private Long id;
    private Customer customer;
    private Double balance;

    public Account(Long id, Customer customer, Double balance) {
        this.id = id;
        this.customer = customer;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
