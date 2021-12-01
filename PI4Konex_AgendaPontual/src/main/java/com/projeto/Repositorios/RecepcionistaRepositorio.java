package com.projeto.Repositorios;


import com.projeto.Entidades.Recepcionista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionistaRepositorio extends JpaRepository<Recepcionista, Integer> {

  
}
