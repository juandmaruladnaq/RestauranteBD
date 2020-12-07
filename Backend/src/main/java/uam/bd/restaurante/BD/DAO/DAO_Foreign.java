package uam.bd.restaurante.BD.DAO;

import java.util.List;

public interface DAO_Foreign<T> extends DAO<T>
{
	List<T> getAllById(String id) throws Exception;
}
