package com.wswork.carros.repository;

import com.wswork.carros.entity.MarcaEntity;
import com.wswork.carros.entity.ModeloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<ModeloEntity, Long> {

    public List<ModeloEntity> findAllByOrderByNomeAsc();

    @Query("SELECT COUNT(p) > 0 FROM ModeloEntity p WHERE p.nome = :nome")
    public boolean existeModeloBoleana(@Param("nome") String nome);

    @Query("SELECT p FROM ModeloEntity p WHERE p.nome = :nome")
    public ModeloEntity buscarNomeModelo(@Param("nome") String nome);

}
