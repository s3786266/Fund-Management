package com.rmit.sept.mspersons.exception;

public class PersonIdException extends RuntimeException{

    private  static final long serialVersionUID = 342423523;

    private Integer value;

    public PersonIdException(String message, Integer value) {
        super(message);
        this.value = value;
    }
}
