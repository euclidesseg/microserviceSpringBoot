package com.example.userservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
/* Con entity decimos que es un modelo real y que cada uno de los campos
 * que añadamos sera una columna en la base de datos
*/
@Table(name = "usuarios")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
     /* que el id se genere automaticamente y que se atutoincremente el campo id*/
    private int id;
    private String nombre;
    private String email;
    private int prioridad;



    public int getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public int getPrioridad(){
        return this.prioridad;
    }
    public void setPrioridad(int prioridad){
        this.prioridad = prioridad;
    }
}
