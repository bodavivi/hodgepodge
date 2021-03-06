package com.lavien.hodgepodge.repositories;

import com.lavien.hodgepodge.models.Alchemist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlchemistRepository extends JpaRepository<Alchemist, Long> {

}
