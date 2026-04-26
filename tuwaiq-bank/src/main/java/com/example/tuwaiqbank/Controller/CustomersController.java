package com.example.tuwaiqbank.Controller;

import com.example.tuwaiqbank.ApiResponse.ApiResponse;
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
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse("Customer added successfully");
    }

    @PutMapping("/update/{ID}")
    public ApiResponse updateCustomer(@PathVariable int ID, @RequestBody Customer customer) {
        customers.set(ID, customer);
        return new ApiResponse("Customer updated successfully");
    }

    @DeleteMapping("/delete/{ID}")
    public ApiResponse deleteCustomer(@PathVariable int ID) {
        customers.remove(ID);
        return new ApiResponse("Customer deleted successfully");
    }

    @PutMapping("/deposit/{ID}/{amount}")
    public ApiResponse depositMoney(@PathVariable int ID, @PathVariable double amount) {
        double newBalance = customers.get(ID).getBalance() + amount;
        customers.get(ID).setBalance(newBalance);
        return new ApiResponse("Money deposited successfully");
    }

    @PutMapping("/withdraw/{ID}/{amount}")
    public ApiResponse withdrawMoney(@PathVariable int ID, @PathVariable double amount) {
        if (customers.get(ID).getBalance() < amount) {
            return new ApiResponse("Insufficient balance");
        }

        double newBalance = customers.get(ID).getBalance() - amount;
        customers.get(ID).setBalance(newBalance);
        return new ApiResponse("Money withdrawn successfully");
    }
}
