package com.example.motoservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.motoservice.models.MotoModel;
import com.example.motoservice.services.MotoService;

@RestController
@RequestMapping("/motos")
public class MotoController {
    @Autowired
    MotoService motoService;

    
    // obtener todas
    @GetMapping()
    public ResponseEntity<ArrayList<MotoModel>> getMotoAll(){
        
        ArrayList<MotoModel> motos = this.motoService.getAllMoto();
        
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.ok(motos);
        }
    }

    // guardar
    @PostMapping()
    public ResponseEntity <MotoModel> saveMoto(@RequestBody MotoModel moto){
        try{
            MotoModel savedMoto = this.motoService.addMoto(moto);
            return ResponseEntity.ok(savedMoto);
        }catch(RuntimeException err){
           return ResponseEntity.notFound().build();
        }
    }

    // por id
    @GetMapping(path = "/{id}")
    public Optional <MotoModel> getById(@PathVariable("id") long id) throws Exception{
        return this.motoService.getById(id);
    }
    


    // por usuario
    @GetMapping(path = "byusuario/query")
       // @ query se consultaria de la siguiente manera
     // /byusuario/query?usuarioid=5
    public ResponseEntity <List<MotoModel>> getByUsuarioId(@RequestParam("usuarioid") int usuarioid){
        List<MotoModel> listMoto = this.motoService.getByUsuarioId(usuarioid);
        if(listMoto.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listMoto);
        }
    }
}
