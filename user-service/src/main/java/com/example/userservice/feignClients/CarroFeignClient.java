package com.example.userservice.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.userservice.entities.Carro;

@FeignClient(name = "car-service") 
@RequestMapping("/cars")
// acabamos de indicar que vamos a acceder a un cliente feign es decir otro microservicio 
// el cual le dimos por nombre carro-service y en la url le indicamos la ruta en la que ese microservicio se ejecuta
// @RequestMapping("/carro") con esto indicamos que vamos a acceder a su ruta principal de controlador
public interface CarroFeignClient {

    @PostMapping()
    public Carro save(@RequestBody Carro carro);

     @GetMapping(path = "/usuarios/query")
    public List<Carro> getAllCarrosByUsuario(@RequestParam("userid") int userId);

}
 