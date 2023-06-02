package com.example.userservice.repositoryes;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userservice.models.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Integer> {
    // <UsuarioModel, Long> indica el tipo de identidad que se va a manejar en este
    // repositorio
    // Longo se refiere al tipo de datos del identificador unico de mi tabla
    // UsuarioModel

    public ArrayList<UserModel> findByPrioridad(int prioridad);
    // Debido a que este es un metodo diferente a los que usuarlmente proporciona la
    // libreria jpa
    // declaramos como abstracto para usarlo en los servicios

    // Importante a la hora de de declarar el metodo abstracto poner
    // como debe ser el findBy

    /*
     * Al extender JpaRepository, la interfaz IUserRepository hereda todos
     * los métodos predeterminados de JpaRepository y crudRepository que es la
     * interfaz de la caual hereda Jpa.
     * Los métodos incluyen findAll(), findById(), save(),
     * deleteById(), etc. Estos métodos se utilizan para realizar
     * operaciones CRUD en la tabla de base de datos que se corresponde
     * con la entidad UsuarioModel.
     * ademas Jpa implementa un metodo para realizar paginacion desde la db
     * 
     */
}

/*
 * NOtas: en uns peticion post yo creo la instancia del modelo y mediante el
 * controlador y el servicio
 * llega hasta el repositorio, springmvc se encarga de deserializar el json y
 * los agrega a mi modelo de datos usando los setters
 * JPA tiene metodos especiales para persistir los datos en el caso de una
 * peticion post
 * jpa usa los getter de mi modelo para obtener los datos que ya se establecen
 * mediante stters, y manda esos datos a la db
 * 
 * de forma parecida pasa con un metodo get primero jpa utiliza los metodos
 * setter para etablacer los valores desde la db
 * y en el metodo que yo halla configurado utilizo finAll u otro metodo de
 * consulta para obtejer los valores mediante getter
 */