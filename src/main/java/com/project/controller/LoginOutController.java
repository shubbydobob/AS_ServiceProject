package com.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.dto.User;
import com.project.service.LoginOutService;

@Controller
public class LoginOutController {

	@Autowired
    private LoginOutService loginOutService;

	
	 @GetMapping("/Login")
	    public String showLoginForm(Model model) {
	        model.addAttribute("user", new User());
	        return "/Login/Login";
	    }
	 @PostMapping("/Login")
	    public String login(@ModelAttribute("user") User user, HttpSession session, Model model) {
	        User authenticatedUser = loginOutService.authenticate(user.getId(), user.getPassword());
	        if (authenticatedUser != null) {
	            session.setAttribute("loggedInUser", authenticatedUser);
	            return "redirect:/";
	        } else {
	            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
	            return "login";
	        }
	    }

	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/login";
	    }
	
}
