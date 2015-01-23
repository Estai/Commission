package com.epam.dao;


public interface DaoCommand {
    public Object execute(DaoManager daoManager);
}
