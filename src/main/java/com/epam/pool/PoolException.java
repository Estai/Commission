package com.epam.pool;

public class PoolException extends RuntimeException {
    public PoolException() {
    }

    public PoolException(String message) {
        super(message);
    }

    public PoolException(Throwable cause) {
        super(cause);
    }

    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
