package br.com.bugtracker.model.parser;

import br.com.bugtracker.dto.UsersDto;
import br.com.bugtracker.model.Users;

public class UsersParser {

	 public static UsersParser get(){
	        return  new UsersParser();
	    }

	    public UsersDto dto(Users entity){
	        UsersDto dto = new UsersDto();
	        
	        dto.setName(entity.getName());
	        dto.setEmail(entity.getEmail());
	        dto.setCompanyPosition(entity.getCompanyPosition());
	        dto.setRole(entity.getRole());	        
	       
	        return dto;
	    }
	    
	    public Users entity(UsersDto dto) {
	    	Users entity = new Users();
	    	
	    	entity.setName(dto.getName());
	    	entity.setEmail(dto.getEmail());
	    	entity.setCompanyPosition(dto.getCompanyPosition());
	    	entity.setRole(dto.getRole());
	    	
	    	return entity;
	    }
}
