package com.spring.basic.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.basic.model.UserVO;
import com.spring.basic.model.UserVO;

@Controller // servlet-context에서 빈그래프를 보면 확인가능 뒤에 ("이름")으로 이름을 지정할수 있음
//빈이 이렇게 등록이 되어 있어야 Handlermapping이 이 클래스의 객체를 검색 할 수 있음

@RequestMapping("/request") // 컨트롤러 자체에 공통 URI를 제공해줌 <- 이걸 쓰면 맵핑할 때 앞의 /request 생략가능 

public class RequestController {
 // 1번 빈등록을 먼저해줌 ( @controller 라는 아노테이션을 붙여주면 자동으로 등록 됨 )
	
	public RequestController() {
		System.out.println("requestcon 생성됨 ! "); // 정상적으로 빈이 등록 됐는지 확인하기 위해
	}
	
	
	@RequestMapping("/test") // /request/test라는 요청이 들어오면 밑의 메서드를 호출시킴
	// 요청이 들어온다는게 무슨말이지?
	// http://localhost:8181/basic/request/test 가 웹페이지 주소에 요청된다면 이라는 뜻
	public String testCall() {
		System.out.println("/request/test 요청이 들어옴!");
		return "test2"; // "파일이름".jsp 파일을 실행시켜주는거임
	}
	
	
	//return으로 실행시키는 파일은 views 폴더안에 있는 jsp파일이 디폴트인데
	//다른 폴더에 있는 파일은 실행시킬수 없을까 ? 실행시키는 방법
	// 기본 디폴트가 views이기에 생략되니까 앞에 /폴더이름 <- 이렇게 붙여줘야함
//	@RequestMapping("/request/req")
	@RequestMapping("req") // 맨 위에 @RequestMapping("/request") 해놨기때문에 /request 생략 가능
	public String req() {
		System.out.println("/request/req 요청이 들어왔습니다!");
		return "/request/req-ex01";
	}
	
	
//	@RequestMapping(value="/request/basic01",method = RequestMethod.GET) 더 쉽게 쓰는 방법 ( 5버전부터 가능 )
	@GetMapping("/basic01")
	public String basicget() {
		System.out.println("/basic01 요청이 들어옴 : GET 방식");
		return "/request/req-ex01";
	}
	
//	@RequestMapping(value="/request/basic01",method = RequestMethod.POST) 더 쉽게 쓰는 방법 ( 5버전부터 가능 )
	@PostMapping("/basic01")
	public String basicpost() {
		System.out.println("/basic01 요청이 들어옴 : POST 방식");
		return "/request/req-ex01";
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
//	@GetMapping("/join")
//	public String register() {
//		System.out.println("/request/join : GET");
//		return "/request/join";
//	}
	
	//위에랑 똑같음
	//메서드 타입을 void로 선언하면
	//요청이 들어온 url값을 뷰 리졸버에게 전달함 
	@GetMapping("/join") //  = /request/join 맨 위에 기본디폴트값을 /request를 적어놔서 /request 생략가능
	public void register() {
		System.out.println("/request/join : GET");
	}
	
	//요청 uri 주소가 같더라도 , 전송 방식에 따라 맵핑을 다르기 하기 때문에
	//같은 주소를 사용하는것이 가능합니다. ( GET - > 화면처리  POST - > 입력값 처리 )
	
//	@PostMapping("/join")
//	public void register(HttpServletRequest request) {
//		System.out.println("/request/join : POST");
//		
//		System.out.println("ID : " + request.getParameter("userId"));
//		System.out.println("PW : " + request.getParameter("userPw"));
//		System.out.println("HOBBY : " + Arrays.toString(request.getParameterValues("hobby")));
//		//hobby는 id pw와 다르게 값이 여러개라 배열로 지정해야함
//		JSP에서 썼던 getparameter로 값을 구해오는 방식 / httpservletRequese 방식
//		(스프링에서는 쓸 필요가 없음)
	
	
	
	
//	2.@RequestParam 아노테이션을 이용한 요청 파라미터 처리.
//	@RequestParam("파라미터 변수명") 값을 받아서 처리할 변수
//	파라미터 변수명과 값을 받을 변수명을 동일하게 적으면 @RequestParam 생략 가능
	
//	@PostMapping("/join")
//	public void register(@RequestParam("userId") String userId,
//						 @RequestParam("userPw") String userPw,
//						 @RequestParam("hobby") List<String> hobbies) //hobby는 값이 여러개라 List를 줘야함
//	{
//		System.out.println("ID : " + userId);
//		System.out.println("PW : " + userPw);
//		System.out.println("hobby : " + hobbies);
//	}
	
	//만약 hobby를 하나도 안고르면 ? 오류가남
	//@RequestParam(value = "hobby", required = false , defaultValue = "취미가 없는 사람")
	//required를 false로 하면 체크박스를 하나도 체크하지 않아도 오류가 뜨지 않고 null값이 뜸
	// null값이 보기 싫으면 defaultValue로 null값을 직접 조정할수 있음
	
	
	/*
	 * 3. 커맨드 객체를 활용한 파라미터 처리
	 * - 파라미터 데이터와 연동되는 VO 클래스가 필요합니다. (join에 있는 정보들을 전부 담은 VO 클래스)
	 * 2번 방식이 FM이긴 하지만 일일히 정보 하나씩 requestparam으로 받아와야해서 코드가 복잡해짐
	 * VO 클래스의 필드는 파라미터의 변수명과 동일하게 작성합니다(setter 메서드를 호출)
	 * 
	 * # 커맨드 객체 : 사용자의 입력을 담기 위해 설계되고, 특정 검증 로직이나 비즈니스 로직을
	 * 수행 할수 있음. (VO보다는 역할이 좀 더 많고, 특정 목적을 가진 객체)
	 */
	
	
	@PostMapping("/join")
	public void register(UserVO vo) {
		System.out.println(vo);
	}
	
	
	
	
	}



