package com.example.userservice.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.entities.Carro;
import com.example.userservice.entities.Moto;
import com.example.userservice.feignClients.CarroFeignClient;
import com.example.userservice.feignClients.MotoFeignClient;
import com.example.userservice.models.UserModel;
import com.example.userservice.repositoryes.IUserRepository;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarroFeignClient carroFeignClient;
    @Autowired
    private MotoFeignClient motoFeignClient;

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

    // los siguientes dos metodos estan usando restemplate para comunicarse con
    // los microservicios de carro y moto para obtener behivulos en funcion del i de
    // usuario
    @SuppressWarnings("unchecked")
    public List<Carro> getCars(int usuarioId) {
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

    // FeignClient ================
    public Carro saveCar(int usuarioId, Carro carro) {
        carro.setUserId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return nuevaMoto;
    }

    // el sigiuente metodo me va a obtener tanto motos y carros por usuario
    public Map<String, Object> getAllVehicles(int usuarioid){
        Map<String, Object> result = new HashMap<>();
        UserModel usuario = this.userRepository.findById(usuarioid).orElse(null);

        if(usuario == null){
            result.put("Mensaje", "Esto usuario no existe");
            return result;
        }else{
            result.put("Usuario",usuario);
            List<Carro> carros = carroFeignClient.getAllCarrosByUsuario(usuarioid);
            if(carros.isEmpty()){
                result.put("Carro,", "El usuairo no tiene carros");
            }else{
                result.put("Carros", carros);
            }
            List<Moto> motos = motoFeignClient.getAllMotosByUsuario(usuarioid);
            if(motos.isEmpty()){
                result.put("Motos", "El usuario no tiene motos");
            }else{
                result.put("Motos", motos);
            }
        }
        return result;
        /* En este método, se utiliza un objeto Map<String, Object> llamado result para almacenar y devolver
           la información de un usuario junto con sus carros y motos (si los tiene).
           El mapa actúa como una estructura de datos clave-valor, donde las claves son cadenas de 
           texto y los valores son objetos. El método busca un usuario por su ID en el repositorio y,
            si se encuentra, se agregan sus detalles al mapa. Luego, se obtienen los carros y motos 
            asociados al usuario utilizando servicios externos (FeignClient)*/
    }
    // ========================================

}

/*
 * La declaración "throws Exception" en el encabezado del método getUsuarioByid
 * indica que el método
 * "getUsuarioByid" puede lanzar una excepción del tipo "Exception".
 */