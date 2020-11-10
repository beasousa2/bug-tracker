package br.com.bugtracker.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.bugtracker.dao.UsersDao;
import br.com.bugtracker.dto.UsersDto;
import br.com.bugtracker.model.Users;
import br.com.bugtracker.model.parser.UsersParser;


@RequestScoped
public class UsersService {

	@Inject
		UsersDao dao;
	
	 public List<UsersDto> listAllUsers(){
	        return dao.listAllUsers().stream().map(UsersParser.get()::dto).collect(Collectors.toList());
	    }
	    
	 @Transactional(rollbackOn = Exception.class)
	 public void insert(@Valid UsersDto dto) {
	   	Users user = UsersParser.get().entity(dto);
	   	dao.insertUser(user);
	 }
}
