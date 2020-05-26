package com.Repository;

import java.sql.ResultSet;
import java.util.List;

public interface IRepository<T> {

    default List<T> getData(){
        return null;
    }
    default T get(String index) {
        System.out.println("ERROR [Default call in DatabaseManipulation interface function]");
        return null;
    }
    T parseElement(ResultSet resultSet);
    T insert(T entity);
    T update(T entity);
    boolean delete(T entity);
}
