package com.project.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.dto.MemberDTO;
import com.project.service.MemberService;


@Controller
public class HomeController {
	 
	@Autowired
    private MemberService memberService;

	
	 @GetMapping("reservation")
	    public String Reservation() {
	        // 예약 폼으로 이동
	        return "/reservation/reservation"; // reservation.jsp와 같은 뷰 파일로 연결
	    }
	 
	 @GetMapping("Sign")
	 public String Sign() {
		 return "/Sign/Sign";
	 }
	 
	 @PostMapping("Sign")
	 public String signUp(@ModelAttribute MemberDTO memberDTO, Model model) {
		    // 회원가입 서비스 호출
		    boolean isSuccess = memberService.saveMember(memberDTO);

		    if (isSuccess) {
		        model.addAttribute("message", "회원가입이 완료되었습니다.");
		        System.out.println("회원가입 성공");
		        return "redirect:/home";  // 회원가입 완료 후 로그인 페이지로 리다이렉트
		    } else {
		        model.addAttribute("error", "회원가입에 실패했습니다. 다시 시도해주세요.");
		        System.out.println("회원가입 실패");
		        return "Sign/Sign"; // 실패 시 회원가입 페이지로 돌아감
		    }
		}
}

