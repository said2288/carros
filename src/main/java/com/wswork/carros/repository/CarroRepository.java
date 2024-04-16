package com.wswork.carros.repository;

import com.wswork.carros.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Long> {

    @Modifying
    @Query("DELETE FROM CarroEntity c WHERE c.modelo.marca.nomeMarca = :nomeMarca")
    public void deletarCarrosMarca(@Param("nomeMarca") String nomeMarca);

    @Query("SELECT c FROM CarroEntity c WHERE c.id IN :ids")
    public List<CarroEntity> findAllByIds(@Param("ids") List<Long> ids);
}