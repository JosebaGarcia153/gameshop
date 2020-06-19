package modelo.dao;

import java.util.ArrayList;

public interface Crudable<P> {
	
	ArrayList<P> getAll();
	P getById(int id) throws Exception;
	
	P create(P pojo) throws Exception;
	P update(P pojo) throws Exception;
	P delete(int id) throws Exception;
}
