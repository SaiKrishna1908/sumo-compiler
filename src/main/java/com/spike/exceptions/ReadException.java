package com.spike.exceptions;

public class ReadException extends  RuntimeException {
  String message;

  public ReadException(String message) {
      super(message);
      this.message = message;
  }
}