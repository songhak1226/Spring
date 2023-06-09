package com.smhrd.myapp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.myapp.domain.WebMember;
import com.smhrd.myapp.mapper.MemberMapper;

@Controller // view 반환
public class MemberController {

	// 의존성주입(DI) : Mapper 객체(구현체)를 외부에서 생성하고 주입시켜서 사용
	@Autowired
	private MemberMapper mapper;

	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public String join(@RequestParam("email") String email, @RequestParam("pw") String pw,
			@RequestParam("tel") String tel, @RequestParam("address") String address) {

		WebMember wm = new WebMember(email, pw, tel, address);

		int cnt = mapper.join(wm);

		System.out.println(cnt);

		// /member/join : 클라이언트가 요청 -> 주소창에 member/join이라고 뜸
		if (cnt > 0) { // 회원가입 성공
			// return "joinSuccess"; -> member/join 주소창에 이렇게 남아있음
			return "redirect:/joinsuccess?email=" + email;
		} else { // 회원가입 실패
			// return "index"; -> member/join
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("pw") String pw, HttpSession session) {
		WebMember wm = new WebMember(email, pw);
		WebMember loginMember = mapper.login(wm);
		session.setAttribute("loginMember", loginMember);

		return "redirect:/";
	}

	@RequestMapping(value = "/member/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginMember");
		// session.invalidate(); 세션 전체 삭제
		return "redirect:/";
	}

	@RequestMapping(value = "/member/delete/{email}")
	public String delete(@PathVariable("email") String email) {
		// RequestParam : 쿼리스트링, post packet 바디 데이터, 주의할점 : 데이터를 각각 받을 수 있음
		// PathVariable : 경로에 포함된 데이터 가지고 올 때
		System.out.println(email);
		int cnt = mapper.delete(email);

		return "redirect:/select";
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String update(@ModelAttribute WebMember wm, HttpSession session) {

		WebMember loginMember = (WebMember) session.getAttribute("loginMember");
		wm.setEmail(loginMember.getEmail());

		int cnt = mapper.update(wm);

		if (cnt > 0) { // 수정성공
			session.setAttribute("loginMember", wm);
			return "redirect:/";
		} else { // 수정실패
			return "redirect:/update";
		}
	}

	// view 반환 x -> data(model) 반환
	// @Controller => view를 반환하는 메서드
	// @Controller안에 @ResponseBody라고 써주면 model을 반환하는 메서드
	@RequestMapping(value = "/member/emailcheck")
	public @ResponseBody String emailCheck(@RequestParam("input") String email) {
		System.out.println(email);

		int result = mapper.emailCheck(email);
		System.out.println(result);

		if (result > 0) { // 값 있으면 -> 사용불가능한 이메일
			return "fail"; // 일반문자열 (view X 문자열 O)
		} else { // 값 없으면(0) -> 사용가능한 이메일
			return "success";
		}
	}
}
