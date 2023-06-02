package com.example.motoservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.motoservice.models.MotoModel;
import com.example.motoservice.repositoryes.IMotoRepository;

@Service
public class MotoService {
    @Autowired
    IMotoRepository motoRepository;

    public MotoModel addMoto(MotoModel moto){
        return this.motoRepository.save(moto);
    }
    public ArrayList<MotoModel> getAllMoto(){
        return (ArrayList<MotoModel>) motoRepository.findAll();
    }

    public Optional <MotoModel> getById(long id)throws Exception{
        try{
            return this.motoRepository.findById(id);
        }catch(RuntimeException err){
            throw new Exception(err.getMessage());
        }
    }

    public List <MotoModel> getByUsuarioId(int usuarioId){
        return this.motoRepository.findByUsuarioId(usuarioId);
    }

    public boolean deleteMoto(long id){
        try{
            this.motoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            System.out.println("sucesio un error" + err);
            return false;
        }
    }
}
