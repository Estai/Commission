package com.epam.dao;

public class DaoException extends RuntimeException {
    private Throwable e;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message);
        this.e = cause;
    }

    public DaoException(Exception e) {
        this.e = e;
    }

    public Throwable getE() {
        return e;
    }

    @Override
    public String toString() {
        return "DaoException" +
                " " + e +
                "";
    }
}
