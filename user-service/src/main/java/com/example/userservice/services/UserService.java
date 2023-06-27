package com.example.userservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.entities.Carro;
import com.example.userservice.entities.Moto;
import com.example.userservice.models.UserModel;
import com.example.userservice.repositoryes.IUserRepository;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private IUserRepository userRepository;

    // los siguientes dos metodos estan usando restemplate para comunicarse con
    // los microservicios de carro y moto
    @SuppressWarnings("unchecked")
    public List<Carro> getCarros(int usuarioId) {
        String url = "http://localhost:4002/cars/usuarios/query?userid=";
        List<Carro> listCarros = restTemplate.getForObject(url + usuarioId, List.class);
        return listCarros;
    }

    @SuppressWarnings("unchecked")
    public List<Moto> getMotos(int usuarioId) {
        String url = "http://localhost:4003/motos/byusuario/query?usuarioid=";
        List<Moto> listMotos = restTemplate.getForObject(url + usuarioId, List.class);
        return listMotos;
    }

    public UserModel save(UserModel user) {
        UserModel userSaved = this.userRepository.save(user);
        return userSaved;
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> listUsers = this.userRepository.findAll();
        return listUsers;
    }

    // public ArrayList<UserModel> gtAllUsers(){
    // return (ArrayList <UserModel>) userRepository.findAll();

    // }

    /*
     * En el segundo ejemplo, se está utilizando el casting (ArrayList<UserModel>)
     * para realizar un cast directo del resultado de userRepository.findAll()
     * a ArrayList<UserModel>
     */

    // optional porque puede ser vacio
    public Optional<UserModel> getUsuarioByid(int id) throws Exception {
        try {
            return userRepository.findById(id);
        } catch (RuntimeException excep) {
            throw new Exception(excep.getMessage()); // error si es string me dara error
        }
    }

    public ArrayList<UserModel> getByPriodidad(int prioridad) {
        return userRepository.findByPrioridad(prioridad);
    }

    public boolean deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

}

/*
 * La declaración "throws Exception" en el encabezado del método getUsuarioByid
 * indica que el método
 * "getUsuarioByid" puede lanzar una excepción del tipo "Exception".
 */