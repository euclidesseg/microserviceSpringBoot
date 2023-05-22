package com.example.userservice.controllers;

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
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.models.UserModel;
import com.example.userservice.services.UserService;


 @RestController // indicamos con @RestController que esta clase sera un controlador de spring
 @RequestMapping("/users") // RequustMapping se unsa para asiganar una url a una clase o o metodo

 public class UserController {
    
     @Autowired
     private UserService userService; //inyeccion de dependencias


     // obtenemos los usuarios desde nuestro servicio
     @GetMapping()
     public ResponseEntity <List<UserModel>> listarUsuarios(){
         List<UserModel> users = this.userService.getAllUsers();
        
         if(users.isEmpty()){
                       return ResponseEntity.noContent().build(); // si esta vacia retornara que no hay contenido
               }
                else{
                       return ResponseEntity.ok(users);
               }
           }
            // Notas del Metodo
            /* ResponseEntity es una clase proporcionada por Spring Framework que representa la respuesta HTTP de un controlador. 
             * En la línea de código, ResponseEntity<List<UserModel>> indica que el controlador 
             * devuelve una respuesta HTTP que contiene una lista de objetos UserModel

         */

     @GetMapping("/{id}")
     public Optional <UserModel> getUsuario(@PathVariable("id") int id){
        return   this.userService.getUsuarioByid(id);
     }
     // Notas debido a que este metodo retorna un optional 
     // si no encutra el usuario restornara un optional.empty() que sera nulo


     
     @GetMapping("/query")
     // @ query se consultaria de la siguiente manera
     // /usuario/query?prioridad=5
     public ResponseEntity <List<UserModel>> getByPrioridad(@RequestParam("prioridad") int prioridad){
          List<UserModel> listUsers = this.userService.getByPriodidad(prioridad);
          if(listUsers.isEmpty()){
             return ResponseEntity.noContent().build();
          }
          else{
              return ResponseEntity.ok(listUsers);
          }
     }

    

     @PostMapping()
     public  ResponseEntity <UserModel> saveUser(@RequestBody UserModel user){
         try{
             UserModel savedUser = this.userService.save(user);
             return ResponseEntity.ok(savedUser);
         }catch(Exception err){
             return  ResponseEntity.notFound().build();
         }
     }

     @PutMapping()
     public  ResponseEntity <UserModel> updateUser(@RequestBody UserModel user){
        try{
            UserModel savedUser = this.userService.save(user);
            return ResponseEntity.ok(savedUser);
        }catch(Exception err){
            return  ResponseEntity.notFound().build();
        }
    }

 }
