package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("---Testando método FindById(Procurar por id)---");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n---Testando método FindByDepartment(Procurar por idDepartment)---");
		Department dep = new Department(1, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		
		for (Seller sell : list) {
			System.out.println(sell);
		}
		
		System.out.println("\n---Testando método FindAll(Procurar tudo)---");
		list = sellerDao.findAll();
		for (Seller sell : list) {
			System.out.println(sell);
		}
		
	}

}
