package org.jsp.USER_PRODUCT.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.USER_PRODUCT.dto.Product;
import org.jsp.USER_PRODUCT.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao 
{
	@Autowired
	private EntityManager manager;
	
	public Product saveProductByUserId(Product product,int user_id)
	{
		EntityTransaction transaction=manager.getTransaction();
		User u=new User();
		u=manager.find(User.class,user_id);
		if(u!=null)
		{
			u.getProducts().add(product);
			product.setUser(u);
			manager.persist(product);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}
	
	public List<Product> findProductByUserId(int id)
	{
		Query q=manager.createQuery("select p from Product p where p.user.id=?1");
		q.setParameter(1, id);
		return q.getResultList();
	}
	public List<Product> findProductByBrand(String brand)
	{
		Query q=manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}
	
	public List<Product> findProductByCategory(String category)
	{
		Query q=manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}

}
