package model.dao;

import dataBase.dbFunction;
import model.dao.implement.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(dbFunction.getConnection());
	}
}
