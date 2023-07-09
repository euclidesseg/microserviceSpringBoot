package com.example.userservice.controllers;

import java.util.List;
import java.util.Map;
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

import com.example.userservice.entities.Carro;
import com.example.userservice.entities.Moto;
import com.example.userservice.models.UserModel;
import com.example.userservice.services.UserService;

@RestController // indicamos con @RestController que esta clase sera un controlador de spring
@RequestMapping("/users") // RequustMapping se unsa para asiganar una url a una clase o o metodo

public class UserController {

    @Autowired
    private UserService userService; // inyeccion de dependencias

    // obtenemos los usuarios desde nuestro servicio
    @GetMapping()
    public ResponseEntity<List<UserModel>> listarUsuarios() {
        List<UserModel> users = this.userService.getAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // si esta vacia retornara que no hay contenido
        } else {
            return ResponseEntity.ok(users);
        }
    }
    // Notas del Metodo
    /*
     * ResponseEntity es una clase proporcionada por Spring Framework que representa
     * la respuesta HTTP de un controlador.
     * En la línea de código, ResponseEntity<List<UserModel>> indica que el
     * controlador
     * devuelve una respuesta HTTP que contiene una lista de objetos UserModel
     * 
     */

    @GetMapping("/{id}")

    public Optional<UserModel> getUsuario(@PathVariable("id") int id) throws Exception {
        return this.userService.getUsuarioByid(id);
    }
    // Notas debido a que este metodo retorna un optional
    // si no encutra el usuario restornara un optional.empty() que sera nulo
    // por lo que no se controla la excepcion

    @GetMapping("/query")
    // @ query se consultaria de la siguiente manera
    // /usuario/query?prioridad=5
    public ResponseEntity<List<UserModel>> getByPrioridad(@RequestParam("prioridad") int prioridad) {
        List<UserModel> listUsers = this.userService.getByPriodidad(prioridad);
        if (listUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listUsers);
        }
    }

    @PostMapping()
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {
        try {
            UserModel savedUser = this.userService.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) {
        try {
            UserModel savedUser = this.userService.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }

    // estos dos metodos se van a comunicar por restemplate desde el servicio hasta
    // el microservicio de
    // carros y de motos con el fin de traer carros y motos por usuaios desde los
    // dos microservicios restantes

    // obtenermos los carros por id de usuario
    @GetMapping(path = "/carros/{id}")
    public ResponseEntity<List<Carro>> getCarroByUserId(@PathVariable("id") int id) throws Exception {
        Optional<UserModel> usuario = this.userService.getUsuarioByid(id);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Carro> listCarro = this.userService.getCars(id);
        return ResponseEntity.ok(listCarro);
    }

    // obtenemos las motos por id de usuario
    @GetMapping(path = "/motos/{id}")
    public ResponseEntity<List<Moto>> getMotoByUserId(@PathVariable("id") int id) throws Exception{
        Optional<UserModel> usuario = this.userService.getUsuarioByid(id);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Moto> listMotos = this.userService.getMotos(id);

        return ResponseEntity.ok(listMotos);
    }

    // este metodo se encargara de usar el servicio de feignclient para comunicarse con el microservico de carros y guardar un carro
    @PostMapping(path = "/cars/{usuarioId}") // el usuarioId sreia el id del usuaio que asosriaremos al carro
    public ResponseEntity<Carro> saveCar(@PathVariable("usuarioId") int id,  @RequestBody Carro car){
        Carro newCar = this.userService.saveCar(id, car);
        return ResponseEntity.ok(newCar);
    }
    // este metodo se encargara de usar el servicio de feignclient para comunicarse con el microservico de motos y guardar una moto
    @PostMapping(path = "/motos/{usuarioId}")
    public ResponseEntity<Moto> saveMoto(@PathVariable("usuarioId") int id,  @RequestBody Moto moto){
        Moto newMoto = this.userService.saveMoto(id, moto);
        return ResponseEntity.ok(newMoto);
    }

    // Este metodo me obtiene todos loe vehivulos de un usuario tanto motos como carros
    @GetMapping(path = "/query2")
    public ResponseEntity<Map<String, Object>> getMotosAndCarsByUser(@RequestParam("userid") int userid){
        Map<String, Object> result = this.userService.getAllVehicles(userid);
        return ResponseEntity.ok(result);
    }
}
