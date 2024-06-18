package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {

	void insert (Seller sellInsert);
	void update (Seller sellUpdate);
	void deleteById (Integer id);
	Department findById (Integer id);
	List<Seller> findAll();
}
