package com.smelgert.carlease.service;

import java.util.Optional;
import java.util.List;

import com.smelgert.carlease.entity.Car;
import com.smelgert.carlease.entity.Customer;
import com.smelgert.carlease.repository.CarRepository;
import com.smelgert.carlease.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarLeaseService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Method to add a new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Method to update a customer's data
    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            Customer existingCustomer = customerOpt.get();

            // Update the fields with the new data
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setStreet(updatedCustomer.getStreet());
            existingCustomer.setHouseNumber(updatedCustomer.getHouseNumber());
            existingCustomer.setZipcode(updatedCustomer.getZipcode());
            existingCustomer.setCity(updatedCustomer.getCity());
            existingCustomer.setEmailAddress(updatedCustomer.getEmailAddress());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            existingCustomer.setCars(updatedCustomer.getCars());

            // Save the updated customer back to the database
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }

    // Method to change car data
    public Car updateCar(Long carId, Car car){
        Optional<Car> carOpt = carRepository.findById(carId);

        if (carOpt.isPresent()) {
            Car existingCar = carOpt.get();

            // Update the fields with the new data
            existingCar.setMake(car.getMake());
            existingCar.setModel(car.getModel());
            existingCar.setVersion(car.getVersion());
            existingCar.setNumberOfDoors(car.getNumberOfDoors());
            existingCar.setCo2Emission(car.getCo2Emission());
            existingCar.setGrossPrice(car.getGrossPrice());
            existingCar.setNettPrice(car.getNettPrice());

            // Save the updated customer back to the database
            return carRepository.save(existingCar);
        } else {
            throw new RuntimeException("Car not found with ID: " + carId);
        }
    }

    // Method to add a new car
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Method to lease a car to a customer
    public Car leaseCarToCustomer(Long customerId, Car car) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            car.setCustomer(customer); // Associate the car with the customer
            return carRepository.save(car); // Save the car with the customer relation
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    // Method to get all cars leased by a specific customer
    public List<Car> getCarsByCustomer(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            return customer.getCars(); // Return the cars leased by this customer
        } else {
            throw new RuntimeException("Customer not found");
        }
    }
}
