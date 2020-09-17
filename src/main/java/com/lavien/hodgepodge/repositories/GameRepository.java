package com.lavien.hodgepodge.repositories;

import com.lavien.hodgepodge.models.Game;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

  Optional<Game> getGameByGameCode(String gameCode);
}
