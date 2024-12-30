package com.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO getUsers(String userId); // 사용자 정보 조회

	int insertUser(MemberDTO memberDTO); // 사용자 정보 삽입
	// int를 쓰는 이유는 데이터베이스에 사용자 정보를 삽입한 후 반환하는 값.

	MemberDTO findUserById(String userId); // 사용자 정보를 조회하는 메서드
}
