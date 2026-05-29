package com.eduardo.stock_manager.GlobalExceptionHandler;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(String message) {
        super(message);
    }

}
