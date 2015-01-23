package com.epam.dao;

import com.epam.entity.Enrollee;
import com.epam.entity.Role;
import com.epam.entity.Score;
import com.epam.entity.Subject;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.*;

public class JdbcEnrolleeDao implements EnrolleeDao {
    private Connection connection = null;
    private DaoHelper daoHelper;

    public JdbcEnrolleeDao(Connection connection) {
        this.connection = connection;
        daoHelper = new DaoHelper();
    }

    @Override
    public List<Enrollee> findAll() {
        List<Enrollee> enrollees = new ArrayList<Enrollee>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ENROLLEE;");
            while (resultSet.next()) {
                Enrollee enrollee = new Enrollee();
                enrollee.setId(resultSet.getInt(1));
                enrollee.setIdUser(resultSet.getInt(2));
                enrollee.setFirstName(resultSet.getString("firstname"));
                enrollee.setLastName(resultSet.getString("lastname"));
                enrollee.setMiddleName(resultSet.getString(5));
                enrollee.setCertificate(resultSet.getDouble(6));
                enrollee.setCertificateNumber(resultSet.getString(7));
                enrollees.add(enrollee);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, statement);
        }
        return enrollees;
    }

    @Override
    public Enrollee findById(Integer id) {
        Enrollee enrollee = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM enrollee WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                enrollee = new Enrollee();
                enrollee.setId(resultSet.getInt(1));
                enrollee.setFirstName(resultSet.getString("firstname"));
                enrollee.setLastName(resultSet.getString("lastname"));
                enrollee.setCertificate(resultSet.getDouble(5));
                enrollee.setCertificateNumber(resultSet.getString(6));
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }
        return enrollee;
    }

    @Override
    public boolean delete(Enrollee entity) {
        boolean isDelete = false;
        PreparedStatement prepareStatement = null;
        PreparedStatement prepareStatement1 = null;
        try {
            connection.setAutoCommit(false);
            prepareStatement = connection.prepareStatement("DELETE FROM enrollee WHERE id=?");
            prepareStatement.setInt(1, entity.getId());
            prepareStatement1 = connection.prepareStatement("DELETE FROM enr_subj WHERE ID_E=? ");
            prepareStatement1.setInt(1, entity.getId());
            prepareStatement.executeUpdate();
            prepareStatement1.executeUpdate();
            connection.commit();
            isDelete = true;
        } catch (Exception e) {
            try {
                connection.rollback();
                throw new DaoException(e);
            } catch (SQLException ex) {
                throw new DaoException(ex);
            }

        } finally {
            daoHelper.close(prepareStatement);
            daoHelper.close(prepareStatement1);
        }
        return isDelete;
    }

    @Override
    public Enrollee create(Enrollee entity) {
        Enrollee enrollee = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO  ENROLLEE(FIRSTNAME, LASTNAME,MIDDLENAME, CERTIFICATE, CERTIFICATENUMBER,ID_USER) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getMiddleName());
            preparedStatement.setDouble(4, entity.getCertificate());
            preparedStatement.setString(5, entity.getCertificateNumber());
            preparedStatement.setInt(6, entity.getIdUser());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }
            enrollee = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(generatedKeys, preparedStatement);
        }
        return enrollee;
    }

    @Override
    public Enrollee update(Enrollee entity) {
        Enrollee enrollee = null;
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement("UPDATE ENROLLEE " +
                    "SET FIRSTNAME=?,LASTNAME=?,CERTIFICATE=?,CERTIFICATENUMBER=? WHERE id=?");
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setDouble(3, entity.getCertificate());
            preparedStatement.setString(4, entity.getCertificateNumber());
            preparedStatement.executeUpdate();
            enrollee = entity;
        } catch (Exception e) {

            throw new DaoException(e);
        } finally {
            daoHelper.close(preparedStatement);
        }
        return enrollee;
    }

    @Override
    public Enrollee findByUser(int id_user) {
        Enrollee enrollee = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM ENROLLEE WHERE ID_USER=?");
            preparedStatement.setInt(1, id_user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                enrollee = new Enrollee();
                enrollee.setId(resultSet.getInt(1));
                enrollee.setIdUser(resultSet.getInt(2));
                enrollee.setFirstName(resultSet.getString("firstname"));
                enrollee.setLastName(resultSet.getString("lastname"));
                enrollee.setMiddleName(resultSet.getString(5));
                enrollee.setCertificate(resultSet.getDouble(6));
                enrollee.setCertificateNumber(resultSet.getString(7));
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }

        return enrollee;

    }

    @Override
    public void insertScore(Integer id, Integer id_subject, Integer id_score) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO ENR_SUBJECT(ID_E, ID_SUBJECT, ID_SCORE) VALUES(?,?,?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id_subject);
            preparedStatement.setInt(3, id_score);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(preparedStatement);
        }

    }
}
