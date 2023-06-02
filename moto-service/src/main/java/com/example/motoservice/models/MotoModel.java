package com.example.motoservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "motos")
public class MotoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(nullable = false, unique = true)
    private long id;
    @Column(nullable = false, unique = false)
    private String marca;
    private String modelo;
    private int usuarioId;

    public MotoModel(){
       super();
    }

    public long getId(){
        return this.id;
    }
    public String getMarca(){
        return this.marca;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public String getModelo(){
        return this.modelo;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public int getUsuarioId(){
        return this.usuarioId;
    }
    public void setUsuarioId(int usuarioId){
        this.usuarioId  = usuarioId;
    }
}

