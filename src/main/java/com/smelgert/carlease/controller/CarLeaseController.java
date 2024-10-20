package com.smelgert.carlease.controller;

import com.smelgert.carlease.entity.Car;
import com.smelgert.carlease.entity.Customer;
import com.smelgert.carlease.service.CarLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CarLeaseController {

    @Autowired
    private CarLeaseService carLeaseService;

    @GetMapping("/test")
    public String getText(){
        return "Hoi Stefan";
    }

    // Fetch cars from the service layer
    @GetMapping("/getCars")
    public List<Car> getCars(){
        return carLeaseService.getAllCars();
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return carLeaseService.addCustomer(customer);
    }

    @PostMapping("/addCar")
    public Car addCar(@RequestBody Car car) {
        return carLeaseService.addCar(car);
    }

    // HTTP PUT request to update customer data
    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        return carLeaseService.updateCustomer(customerId,updatedCustomer);
    }

    // HTTP PUT request to update car data
    @PutMapping("/{carId}")
    public Car updateCar(@PathVariable Long carId, @RequestBody Car updatedCar) {
        return carLeaseService.updateCar(carId, updatedCar);
    }
}
