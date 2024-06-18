package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		//Efetuando testes das classes criadas. 
		Department dep = new Department(1, "Comercial");
		Seller seller = new Seller(1, "Ricardo", "Ricardo@gmail.com", new Date(), 3000.0, dep);

		System.out.println("Executado!, dados -> " + seller);
	}

}
