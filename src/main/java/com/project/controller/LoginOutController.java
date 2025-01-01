package com.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.service.LoginOutService;
import com.project.service.MemberService;

@Controller
public class LoginOutController {

	@Autowired
	private LoginOutService loginOutService;

	@Autowired
	private MemberService memberService;

	@GetMapping("/Login")
	public String Login() {
		return "/Login/Login";
	}

	@GetMapping("/Logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("bbbbbb");
		return "/home";
	}

//	@GetMapping("/naverLoginCallback")
//	public String naverLoginCallback(@RequestParam String code, @RequestParam String state, HttpSession session) {
//		// `code`와 `state` 값을 사용하여 네이버로부터 사용자 정보를 요청
//		User userFromNaver = loginOutService.getUserFromNaver(code, state);
//
//		if (userFromNaver != null) {
//			session.setAttribute("user", userFromNaver); // 사용자 정보를 세션에 저장
//			System.out.println("로그인 후 홈 페이지");
//			return "redirect:/home"; // 로그인 후 홈 페이지로 리다이렉트
//		} else {
//			System.out.println("로그인 실패 후 로그인 페이지로");
//			return "redirect:/Login"; // 로그인 실패 시 로그인 페이지로 이동
//		}
//	}

}
