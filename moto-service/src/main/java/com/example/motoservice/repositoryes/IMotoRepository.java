package com.example.motoservice.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.motoservice.models.MotoModel;

@Repository
public interface IMotoRepository extends JpaRepository<MotoModel, Long>{
    
    public List<MotoModel> findByUsuarioId(int  usuarioId);
}
