package com.example.tuwaiqbank.Controller;

import com.example.tuwaiqbank.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {
    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return "Customer added successfully";
    }

    @PutMapping("/update/{ID}")
    public String updateCustomer(@PathVariable int ID, @RequestBody Customer customer) {
        customers.set(ID, customer);
        return "Customer updated successfully";
    }

    @DeleteMapping("/delete/{ID}")
    public String deleteCustomer(@PathVariable int ID) {
        customers.remove(ID);
        return "Customer deleted successfully";
    }

    @PutMapping("/deposit/{ID}/{amount}")
    public String depositMoney(@PathVariable int ID, @PathVariable double amount) {
        double newBalance = customers.get(ID).getBalance() + amount;
        customers.get(ID).setBalance(newBalance);
        return "Money deposited successfully";
    }

    @PutMapping("/withdraw/{ID}/{amount}")
    public String withdrawMoney(@PathVariable int ID, @PathVariable double amount) {
        if (customers.get(ID).getBalance() < amount) {
            return "Insufficient balance";
        }

        double newBalance = customers.get(ID).getBalance() - amount;
        customers.get(ID).setBalance(newBalance);
        return "Money withdrawn successfully";
    }
}
