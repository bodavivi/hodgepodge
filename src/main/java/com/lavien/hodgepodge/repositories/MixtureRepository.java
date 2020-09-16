package com.lavien.hodgepodge.repositories;

import com.lavien.hodgepodge.models.Mixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MixtureRepository extends JpaRepository<Mixture, Long> {

}
