package com.eduardo.stock_manager.GlobalExceptionHandler;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }

}
