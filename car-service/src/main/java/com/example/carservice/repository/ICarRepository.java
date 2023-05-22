package com.example.carservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carservice.models.CarModel;

public interface ICarRepository extends JpaRepository<CarModel, Integer>{

    public abstract List <CarModel> findByUserId(int userId);
}
