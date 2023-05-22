package com.example.userservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userservice.models.UserModel;
import com.example.userservice.repositoryes.IUserRepository;


@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public UserModel save(UserModel user) {
        UserModel userSaved = this.userRepository.save(user);
        return userSaved;
    }

    public List<UserModel> getAllUsers(){
        List<UserModel> listUsers = this.userRepository.findAll();
        return  listUsers;
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
    public Optional<UserModel> getUsuarioByid(int id) {
        return userRepository.findById(id);
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
