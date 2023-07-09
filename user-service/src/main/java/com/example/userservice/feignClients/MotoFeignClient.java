package com.example.userservice.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.userservice.entities.Moto;

@FeignClient(name = "moto-service", url = "http://localhost:4003")
@RequestMapping("/motos")
public interface MotoFeignClient {

    @PostMapping()
    public Moto save(@RequestBody Moto moto);

    @GetMapping(path = "byusuario/query")
    public List<Moto> getAllMotosByUsuario(@RequestParam("usuarioid") int usuarioid);

}
