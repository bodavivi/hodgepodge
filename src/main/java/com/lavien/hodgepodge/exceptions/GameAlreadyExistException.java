package com.lavien.hodgepodge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameAlreadyExistException extends RuntimeException {

  public GameAlreadyExistException() {
    super("Game is already exist with the given gamecode.");
  }

}
