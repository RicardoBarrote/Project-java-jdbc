package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.dbException;
import dataBase.dbFunction;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller sellInsert) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller sellUpdate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT Seller.*, Department.name as NomeDepartamento " 
			+ "FROM Seller INNER JOIN Department "
			+ "ON Seller.idDepartment = Department.idDepartment "
			+ "WHERE Seller.idSeller = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
				return seller;
			}
			return null;
		} 
		catch (SQLException e) {
			throw new dbException(e.getMessage());
		}
		finally {
			dbFunction.closeResultSet(rs);
			dbFunction.closeStatement(ps);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		
		Department dep = new Department();
		dep.setId(rs.getInt("idDepartment"));
		dep.setName(rs.getString("NomeDepartamento"));
		return dep;
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		
		Seller seller = new Seller();
		seller.setId(rs.getInt("idSeller"));
		seller.setName(rs.getString("name"));
		seller.setEmail(rs.getString("email"));
		seller.setBirthDate(rs.getDate("birthDate"));
		seller.setBaseSalary(rs.getDouble("baseSalary"));
		seller.setDepartment(dep);
		return seller;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT Seller.*, Department.name as NomeDepartamento " 
							+ "FROM Seller INNER JOIN Department "
							+ "ON Seller.idDepartment = Department.idDepartment "
							+ "WHERE Department.idDepartment = ?");
			
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("idDepartment"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("idDepartment"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			
			return list;
			
		}
		catch (SQLException e) {
			throw new dbException(e.getMessage());
		}
		finally {
			dbFunction.closeResultSet(rs);
			dbFunction.closeStatement(ps);
		}
	}

}
