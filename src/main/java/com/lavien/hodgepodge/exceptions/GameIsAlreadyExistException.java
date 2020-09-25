package com.lavien.hodgepodge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameIsAlreadyExistException extends RuntimeException {

  public GameIsAlreadyExistException() {
    super("Game with the given gamecode is already exist.");
  }

}
