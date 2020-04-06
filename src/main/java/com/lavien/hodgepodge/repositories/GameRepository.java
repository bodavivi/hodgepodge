package com.lavien.hodgepodge.repositories;

import com.lavien.hodgepodge.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

}
