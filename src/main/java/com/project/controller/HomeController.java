package com.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dto.MemberDTO;
import com.project.service.MemberService;

@Controller
public class HomeController {

	@Autowired
	private MemberService memberService;

	@PostMapping("/home")
	public String DoLogin(@RequestParam("userId") String userId, @RequestParam("password") String password,
			@RequestParam(value = "rememberId", required = false) Boolean rememberId, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		// Fetch the user from the database using memberService
		MemberDTO memberDTO = memberService.findUserById(userId);

		// If the user exists and the password matches
		if (memberDTO != null && memberDTO.getPassword().equals(password)) {
			// Store the MemberDTO object in the session
			session.setAttribute("user", memberDTO);

			redirectAttributes.addFlashAttribute("message", "로그인 성공!");
			return "redirect:/home"; // Redirect to home after successful login
		} else {
			// If authentication fails, show an error message
			model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
			return "Login"; // Return to login page
		}
	}

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		if (session.getAttribute("user") != null) {
			model.addAttribute("isLoggedIn", true); // 로그인 상태 확인
		} else {
			model.addAttribute("isLoggedIn", false); // 로그아웃 상태
		}
		return "/home"; // 홈 페이지를 반환
	}

	@GetMapping("reservation")
	public String reservation(HttpSession session, Model model) {
		// 세션에서 로그인한 사용자 정보 가져오기
		String userName = (String) session.getAttribute("userName");

		// userName이 null이면 로그인되지 않은 상태일 수 있음
		if (userName != null) {
			model.addAttribute("userName", userName); // 모델에 사용자 이름 추가
		} else {
			model.addAttribute("userName", "Guest"); // 로그인되지 않은 경우 기본값 설정
		}

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
			return "redirect:/home"; // 회원가입 완료 후 로그인 페이지로 리다이렉트
		} else {
			model.addAttribute("error", "회원가입에 실패했습니다. 다시 시도해주세요.");
			System.out.println("회원가입 실패");
			return "Sign/Sign"; // 실패 시 회원가입 페이지로 돌아감
		}
	}

	@GetMapping("/MyPage")
	public String myPage(HttpSession session, Model model) {
		// 세션에서 로그인한 사용자 정보 가져오기
		MemberDTO loggedInUser = (MemberDTO) session.getAttribute("user");

		if (loggedInUser != null) {
			model.addAttribute("user", loggedInUser);
			return "/Login/MyPage"; // 마이페이지 뷰로 이동
		} else {
			// 로그인하지 않은 경우, 로그인을 하도록 리다이렉트
			return "redirect:/Login"; // 로그인 페이지로 리다이렉트
		}
	}
}
