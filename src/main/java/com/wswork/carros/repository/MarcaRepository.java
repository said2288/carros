package com.wswork.carros.repository;


import com.wswork.carros.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {

    public List<MarcaEntity> findAllByOrderByNomeMarcaAsc();

    @Query("SELECT COUNT(p) > 0 FROM MarcaEntity p WHERE p.nomeMarca = :nomeMarca")
    public boolean existeMarcaBoleana(@Param("nomeMarca") String nomeMarca);

    @Query("SELECT p FROM MarcaEntity p WHERE p.nomeMarca = :nomeMarca")
    public MarcaEntity buscarNomeMarca(@Param("nomeMarca") String nomeMarca);

}