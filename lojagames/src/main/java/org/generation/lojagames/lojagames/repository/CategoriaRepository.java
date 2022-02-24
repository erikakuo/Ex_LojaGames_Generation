package org.generation.lojagames.lojagames.repository;

import java.util.List;

import org.generation.lojagames.lojagames.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel,Long>{
public  List <CategoriaModel>findAllByDescricaoContainingIgnoreCase(String descricao);

}
