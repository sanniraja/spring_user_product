package org.jsp.USER_PRODUCT.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.USER_PRODUCT.UserProductConfig;
import org.jsp.USER_PRODUCT.dao.ProductDao;
import org.jsp.USER_PRODUCT.dao.UserDao;
import org.jsp.USER_PRODUCT.dto.Product;
import org.jsp.USER_PRODUCT.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserProductController
{
	static Scanner s=new Scanner(System.in);
	private static UserDao userDao;
	private static ProductDao productDao;
	
	static
	{
		ApplicationContext context=new AnnotationConfigApplicationContext(UserProductConfig.class);
		userDao=context.getBean(UserDao.class);
		productDao=context.getBean(ProductDao.class);
	}

	public static void main(String[] args)
	{
		System.out.println("1 save user");
		System.out.println("2 update user");
		System.out.println("3 verify user by phone and password");
		System.out.println("4 save product by user id");
		System.out.println("5 view product by user id");
		System.out.println("6 view product by brand");
		System.out.println("7 view product by category");
		System.out.println("8 view user by id");
		
		switch(s.nextInt())
		{
		case 1:
		{
			saveUser();
			break;
		}
		case 2:
		{
			updateUser();
			break;
		}case 3:
		{
			verifyUserByPhoneAndPassword() ;
			break;
		}case 4:
		{
			saveProductByUserId() ;
			break;
		}case 5:
		{
			viewProductByUserId();
			break;
		}
		case 6:
		{
			viewProductByBrand();
			break;
		}
		case 7:
		{
			viewProductByCategory() ;
			break;
		}
		case 8:
		{
			viewUserById();
			break;
		}
		}
	}
	public static void saveUser()
	{
		System.out.println("enter name phone email password");
		User u=new User();
		u.setName(s.next());
		u.setPhone(s.nextLong());
		u.setEmail(s.next());
		u.setPassword(s.next());
		u=userDao.saveUser(u);
		System.out.println("User saved with id :" + u.getId());
	}
	public static void updateUser()
	{
		System.out.println("enter user id");
		int id=s.nextInt();
		System.out.println("enter name phone email password");
		User u=new User();
		u.setId(id);
		u.setName(s.next());
		u.setPhone(s.nextLong());
		u.setEmail(s.next());
		u.setPassword(s.next());
		u=userDao.updateUser(u);
		System.out.println("user update with id :" + u.getId());
	}
	public static void verifyUserByPhoneAndPassword() 
	{
		System.out.println("enter phone and password");
		long phone=s.nextLong();
		String password=s.next();
		User u=new User();
		u=userDao.verifyUserByPhoneAndPassword(phone, password);
		if(u!=null)
		{
			System.out.println("ID :"+u.getId());
			System.out.println("Name :"+u.getName());
			System.out.println("Phone :"+u.getPhone());
			System.out.println("Email :"+u.getEmail());
			System.out.println("Password :"+u.getPassword());
		}
		else
		{
			System.out.println("Enter wrong phone and password");
		}
	}
	public static void saveProductByUserId() 
	{
		System.out.println("enter user id");
		int id=s.nextInt();
		System.out.println("enter name brand category description image cost");
		Product p=new Product();
		p.setName(s.next());
		p.setBrand(s.next());
		p.setCategory(s.next());
		p.setDescription(s.next());
		p.setImage(s.next());
		p.setCost(s.nextDouble());
		p=productDao.saveProductByUserId(p, id);
		System.out.println("Product saved with id :"+p.getId());
	}
	public static void viewProductByUserId()
	{
		System.out.println("Enter User id");
		int id=s.nextInt();
		List<Product> prods=productDao.findProductByUserId(id);
		if(prods.size()>0)
		{
			for(Product p: prods)
			{				
				System.out.println("ID :"+p.getId());
				System.out.println("Name"+p.getName());
				System.out.println("Brand :"+p.getBrand());
				System.out.println("Category "+p.getCategory());
				System.out.println(" Description :"+p.getDescription());
				System.out.println("Image "+p.getImage());
				System.out.println("Cost :"+p.getCost());
			}
		}
		else
		{
			System.err.println("Invalid Credential");
		}
	}
	public static void viewProductByBrand()
	{
		System.out.println("enter brand");
		String brand=s.next();
		List<Product> prods=productDao.findProductByBrand(brand);
		if(prods.size()>0)
		{
			for(Product p:prods)
			{
				System.out.println("ID :"+p.getId());
				System.out.println("Name"+p.getName());
				System.out.println("Brand :"+p.getBrand());
				System.out.println("Category "+p.getCategory());
				System.out.println(" Description :"+p.getDescription());
				System.out.println("Image "+p.getImage());
				System.out.println("Cost :"+p.getCost());
			}
		}
		else
		{
			System.out.println("Enter Invalid Credential");
		}
	}
	public static void viewProductByCategory() 
	{
		System.out.println("Enter category");
		String category=s.next();
		List<Product> prods=productDao.findProductByCategory(category);
		if(prods.size()>0)
		{
			for(Product p:prods)
			{
				System.out.println("ID :"+p.getId());
				System.out.println("Name"+p.getName());
				System.out.println("Brand :"+p.getBrand());
				System.out.println("Category "+p.getCategory());
				System.out.println(" Description :"+p.getDescription());
				System.out.println("Image "+p.getImage());
				System.out.println("Cost :"+p.getCost());	
			}
		}
		else
		{
			System.err.println("enter invalid credentials");
		}
	}
	public static void viewUserById()
	{
		System.out.println("enter id");
		int id=s.nextInt();
		User u=new User();
		u=userDao.viewUserbyID(id);
		if(u!=null)
		{
			System.out.println("ID :"+u.getId());
			System.out.println("Name :"+u.getName());
			System.out.println("Phone :"+u.getPhone());
			System.out.println("Email :"+u.getEmail());
			System.out.println("Password :"+u.getPassword());
		}
		else
		{
			System.out.println("Enter invalid Credentials");
		}
	
	}

}
