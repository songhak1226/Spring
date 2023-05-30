package com.smhrd.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Spring에게 해당 Class가 Controller역할을 한다고 아렬주기 위해 사용하는 Annotation
// Controller -> view를 반환하는 역할 (.jsp, .html, ..)
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// 실제 요청 경로와 value값이 일치하면 해당 메서드가 실행되도록 만들어주는 Annotation
	// value : 요청 경로, method : HTTP Method(GET,POST ...)
	// context-root(Path) : 서버 안에서 동작하는 프로젝트를 식별하기 위한 값
	// http://localhost:8085/myapp(context-root)/
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		// "home" -> view
		// ViewResolver가 view 찾아줌
		// ViewResolver : webapp/WEB-INF/spring/appServlet/servlet-context.xml에 관련 정보 작성
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		// ->     //WEB-INF/views/home.jsp
		// 화면 구성(.jsp, .js, .css ...) : 기본경로가 webapp아래
		
		return "home";
	}
	
	// index.jsp를 return하는 메서드
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		
		return "index";
	}
	
}