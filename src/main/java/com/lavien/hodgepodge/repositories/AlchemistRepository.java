package com.lavien.hodgepodge.repositories;

import com.lavien.hodgepodge.models.Alchemist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlchemistRepository extends CrudRepository<Alchemist, Long> {

}
