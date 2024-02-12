package org.hibernate.HibernateProject_Productdb_QueryInterface;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ClassProductService {
	public EntityManager entityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("productdbQueryinterface");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
	public void addProduct(Product product) {

		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(product);

		entityTransaction.commit();
		entityManager.close();

	}
	public List<Product> findProductById(int  productId) {
		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String query = "SELECT p FROM Product p WHERE p.productId=:pId";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter("pId", productId);
		
		List<Product> list = query1.getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return list;
	}
	public List<Product> findProductByName(String ProductName) {
		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		String query = "SELECT p FROM Product p WHERE p.ProductName=:pName";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter("pName", ProductName);
		
		List<Product> list = query1.getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return list;
	}
	public List<Product> findProductBetweenPrices(int min_price , int max_price) {
		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String query = "SELECT p FROM Product p WHERE p.productPrice Between :min AND :max";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter("min", min_price);
		query1.setParameter("max", max_price);
		
		List<Product> list = query1.getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return list;
	}
	public List<Product> findAllProductByCategory(String productCategory) {
		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String query = "SELECT p FROM Product p WHERE p.productCategory=:pCategory";
		Query query1 = entityManager.createQuery(query);
		query1.setParameter("pCategory", productCategory);
		
		List<Product> list = query1.getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return list;
	}
	public void updateProductStatus(int productId) {
		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Product p =entityManager.find(Product.class, productId);
		if(p.getProductStatus().equalsIgnoreCase("In-Stock")) {
			Query q=entityManager.createQuery("UPDATE Product p set p.productStatus=:pStat where p.productId=:pId");
			q.setParameter("pStat", "Not Available");
			q.setParameter("pId", productId);
			int res=q.executeUpdate();
			
			System.out.println("Updated Successfully "+res+" rows affected");
		}
		else {
			System.out.println("Out-Of-Stock");
		}
	

		entityTransaction.commit();
		entityManager.close();
	}
	public void updateProductPriceByName(String ProductName , int productPrice) {
		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		
			Query q=entityManager.createQuery("UPDATE Product p set p.productPrice=:pPrice where p.ProductName=:pName");
			q.setParameter("pPrice", productPrice);
			q.setParameter("pName", ProductName);
			int res=q.executeUpdate();
			System.out.println("Updated Successfully "+res+" rows affected");
		
		
		
		entityTransaction.commit();
		entityManager.close();
	}
	public void deleteProductByBrand(String productBrand) {
		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		String query ="DELETE FROM Product p WHERE p.productBrand=:pBrand";
		Query query2 = entityManager.createQuery(query);
		query2.setParameter("pBrand", productBrand);
		int res = query2.executeUpdate();
		System.out.println("Updated Successfully "+res+" rows affected");
		
		
		entityTransaction.commit();
		entityManager.close();
	}
	public void deleteProductByCategory(String productCategory) {
		EntityManager entityManager = entityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		String query ="DELETE FROM Product p WHERE p.productCategory=:pCategory";
		Query query2 = entityManager.createQuery(query);
		query2.setParameter("pCategory", productCategory);
		int res = query2.executeUpdate();
		
		System.out.println("Updated Successfully "+res+" rows affected");
		entityTransaction.commit();
		entityManager.close();
	}
}
