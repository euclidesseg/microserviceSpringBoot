package com.example.carservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carservice.models.CarModel;
import com.example.carservice.repository.ICarRepository;

@Service
public class CarService {
    @Autowired
    ICarRepository carRepository;


    // guardar un carro
    public CarModel saveCar(CarModel car){
        CarModel carSaved = this.carRepository.save(car);
        return  carSaved;
    }

    // obtener los carros 
    public List<CarModel>getAllCars(){
        List<CarModel> listCars = this.carRepository.findAll();
        return listCars;
    }

    // obtener carro por id
    public Optional <CarModel>  getCarByid(int id){
        return this.carRepository.findById(id);
    }

    // optener carro por id de usuario
    public List<CarModel> getByUserId(int userId){
        return this.carRepository.findByUserId(userId);
    }
    
    // update carr
    public CarModel updateCar(CarModel car){
        CarModel carUpdated = this.carRepository.save(car);
        return carUpdated;
    }
}
