package com.rmit.sept.msaccounts.exception;

public class AccountNumberException extends RuntimeException  {

    private  static final long serialVersionUID = 342423523;

    private Integer value;

    public AccountNumberException(String message, Integer value) {
        super(message);
        this.value = value;
    }
}
