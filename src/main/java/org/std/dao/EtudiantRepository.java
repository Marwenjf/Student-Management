package org.std.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.std.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
  public List<Etudiant> findByNom(String nom);
  public Page<Etudiant> findByNom(String nom,Pageable pageable);
  
  @Query("select e from Etudiant e where e.nom like %:mc%")
  public Page<Etudiant> chercherEtudiants(@Param("mc") String mc,Pageable pageable);
  public Page<Etudiant> findByNomIgnoreCaseContaining(String mc,Pageable pageable);
  
  @Query("select e from Etudiant e where e.dateNaissance > :d1 and e.dateNaissance < :d2")
  public List<Etudiant> chercherEtudiants(@Param("d1") Date d1,@Param("d2") Date d2);
}
