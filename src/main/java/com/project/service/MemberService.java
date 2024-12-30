package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.MemberDTO;
import com.project.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	
	
	public boolean DoLogin(String userId, String password) {
	    // Fetch the user from the database by userId
	    MemberDTO memberDTO = memberMapper.getUsers(userId);
	    
	    // If the user exists and the password matches
	    if (memberDTO != null && memberDTO.getPassword().equals(password)) {
	        return true;  // Authentication successful
	    }
	    return false;  // Authentication failed
	}
	
	public boolean saveMember(MemberDTO memberDTO) {
	    try {
	        int rowsAffected = memberMapper.insertUser(memberDTO);
	        return rowsAffected > 0;  // Return true if one or more rows were inserted
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;  // Return false if an error occurred
	    }
	}
}
