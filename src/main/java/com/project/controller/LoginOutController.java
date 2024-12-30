package com.project.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.service.MemberService;
import com.project.dto.User;
import com.project.service.LoginOutService;

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
	 
		@PostMapping("/home")
		public String DoLogin(@RequestParam("userId") String userId, @RequestParam("password") String password,
				@RequestParam(value = "rememberId", required = false) Boolean rememberId, Model model,
				RedirectAttributes redirectAttributes) {
// Perform login logic (authentication)
			boolean loginSuccess = memberService.DoLogin(userId, password);

			if (loginSuccess) {
// If authentication is successful, redirect to the home page
				redirectAttributes.addFlashAttribute("message", "로그인 성공!");
				return "redirect:/home"; // Redirect to home page after successful login
			} else {
// If authentication fails, show an error message on the login page
				model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
				return "login"; // Return to login page if authentication fails
			}
		}
	 
		 @GetMapping("/home")
		    public String home(HttpSession session, Model model) {
		        if (session.getAttribute("user") != null) {
		            model.addAttribute("isLoggedIn", true);  // 로그인 상태 확인
		            System.out.println("로그인 상태");
		        } else {
		            model.addAttribute("isLoggedIn", false);  // 로그아웃 상태
		            System.out.println("로그아웃 상태");
		        }
		        return "/home";  // 홈 페이지를 반환
		    }
	 
	 
	 @GetMapping("/naverLoginCallback")
	 public String naverLoginCallback(@RequestParam String code, @RequestParam String state, HttpSession session) {
	     // `code`와 `state` 값을 사용하여 네이버로부터 사용자 정보를 요청
	     User userFromNaver = loginOutService.getUserFromNaver(code, state);

	     if (userFromNaver != null) {
	         session.setAttribute("user", userFromNaver);  // 사용자 정보를 세션에 저장
	         System.out.println("로그인 후 홈 페이지");
	         return "redirect:/home";  // 로그인 후 홈 페이지로 리다이렉트
	     } else {
	    	 System.out.println("로그인 실패 후 로그인 페이지로");
	         return "redirect:/Login";  // 로그인 실패 시 로그인 페이지로 이동
	     }
	 }
	    

	    @GetMapping("/Logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        System.out.println("bbbbbb");
	        return "/home";
	    }
	
}
