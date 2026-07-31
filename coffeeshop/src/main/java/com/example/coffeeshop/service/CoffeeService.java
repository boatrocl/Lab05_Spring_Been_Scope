package com.example.coffeeshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.coffeeshop.model.Coffee;

@Service
public class CoffeeService {

    private final List<Coffee> coffees = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public CoffeeService() {
        // ข้อมูลตัวอย่างตอนเริ่มแอป
        coffees.add(new Coffee(idCounter.incrementAndGet(), "Espresso", 45.0));
        coffees.add(new Coffee(idCounter.incrementAndGet(), "Latte", 55.0));
    }

    public List<Coffee> getAll() {
        return coffees;
    }

    public Optional<Coffee> getById(int id) {
        return coffees.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
    public List<Coffee> searchByName(String name) {
        return coffees.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public Coffee create(Coffee newCoffee) {
        newCoffee.setId(idCounter.incrementAndGet());
        coffees.add(newCoffee);
        return newCoffee;
    }

    public Optional<Coffee> update(int id, Coffee updated) {
        return getById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            return existing;
        });
    }

    public boolean delete(int id) {
        return coffees.removeIf(c -> c.getId() == id);
    }
}
