package br.com.sgps.infrastructure.repository;

import br.com.sgps.infrastructure.entity.CandidatoPersistenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CandidatoPersistenceRepository  extends JpaRepository<CandidatoPersistenteEntity, UUID> {

    @Query("""
            SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END
            FROM CandidatoPersistenteEntity c 
            where c.email = :email and (:id IS NULL or c.id <> :id)
            """)
    boolean existEmailCadastrado(@Param("email") String email, @Param("id") String id);
}
