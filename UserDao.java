package org.jsp.USER_PRODUCT.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.USER_PRODUCT.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao 
{
	@Autowired
	private EntityManager manager;
	public User saveUser(User user)
	{
		EntityTransaction transaction=manager.getTransaction();
		manager.persist(user);
		transaction.begin();
		transaction.commit();
		return user;
	}
	public User updateUser(User user)
	{
		EntityTransaction transaction=manager.getTransaction();
		manager.merge(user);
		transaction.begin();
		transaction.commit();
		return user;
	}
	public User verifyUserByPhoneAndPassword(long phone, String password)
	{
		Query q=manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try
		{
			return (User) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	public User viewUserbyID(int id)
	{
		Query q=manager.createQuery("select u from User u where u.id=?1");
		q.setParameter(1, id);
		try
		{
			return (User) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}

}
