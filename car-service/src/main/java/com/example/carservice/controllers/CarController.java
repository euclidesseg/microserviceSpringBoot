package com.example.carservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carservice.models.CarModel;
import com.example.carservice.services.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
    
    @Autowired
    CarService carService;
    
    // guardar carro
    @PostMapping
    public ResponseEntity <CarModel> addCaR(@RequestBody CarModel car){
        CarModel carSaved =  this.carService.saveCar(car);
        if(carSaved == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carSaved);
    }

    // actualizar carros
    @PutMapping()
    public ResponseEntity <CarModel> updateCar(@RequestBody CarModel car){
        CarModel carSaved =  this.carService.saveCar(car);
        if(carSaved == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carSaved);
    }

    // obtener cars
    @GetMapping()
    public ResponseEntity<List<CarModel>> addCar(){

        List<CarModel> listCars = this.carService.getAllCars();

        if(listCars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listCars);
    }

    // obtener carro por id
    @GetMapping("/{id}")
    public Optional <CarModel> getCar(@PathVariable("id") int id){
        return this.carService.getCarByid(id);
    }


    // buscar por id del usuario 
    @GetMapping("/usuarios/{userId}")
    public ResponseEntity<List<CarModel>> getCarByUserId(@PathVariable("userId") int userId){
        List<CarModel>  listCars= this.carService.getByUserId(userId);
        if(listCars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{

            return ResponseEntity.ok(listCars);
        }
    }

}
