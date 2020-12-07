package uam.bd.restaurante.BD.DAO;

import java.util.List;

public interface DAO<T> 
{	
    List<T> getAll() throws Exception;

    T getBy(String id) throws Exception;

    int save(T t) throws Exception;

    int update(T t) throws Exception;

    int delete(T t) throws Exception;
}
