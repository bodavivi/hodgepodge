package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;
import java.util.List;

public interface GameService {

  Game setUp(List<Alchemist> alchemists);

}
