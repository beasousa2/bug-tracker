package br.com.bugtracker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.bugtracker.model.Users;

@RequestScoped
public class UsersDao {

	@PersistenceContext
	EntityManager entity;
	
	@Transactional
	private void insertOrUpdate(String querySql, Users user) {
		Query query = entity.createNamedQuery(querySql);
		
		query.setParameter("id", user.getId());
		query.setParameter("name", user.getName());
		query.setParameter("email", user.getEmail());
		query.setParameter("companyPosition", user.getCompanyPosition());
		query.setParameter("role", user.getRole());
		query.executeUpdate();		
	}
	
	public List<Users> listAllUsers() {
		String query = "USER_LIST";
		List<Users> listResult;
		TypedQuery<Users> typedQuery = entity.createNamedQuery(query, Users.class);
		
		try {
			listResult = typedQuery.getResultList();
		} catch (NoResultException e) {
			listResult = new ArrayList<Users>();
		}
		
		return listResult;
	}
	
	public Users listUserById(Long id) {
		String query = "USER_BY_ID";
		Users user;
		TypedQuery<Users> typedQuery = entity.createNamedQuery(query, Users.class);
		typedQuery.setParameter("id", id);
		
		try {
			 user = typedQuery.getSingleResult();
		} catch (NoResultException e) {
			 user = null;
		}
		
		return user;
	}
	
	@Transactional
	public Long insertUser(Users user) {
		String query = "INSERT_USER";
		insertOrUpdate(query, user);
		user.persistAndFlush();
		return user.getId();
	}
	
	@Transactional
	public void updateUser(Users user) {
		String query = "UPDATE_USER";
		insertOrUpdate(query, user);		
	}
	
}
