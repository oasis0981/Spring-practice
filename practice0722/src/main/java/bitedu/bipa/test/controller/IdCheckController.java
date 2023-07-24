package bitedu.bipa.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bitedu.bipa.test.service.MemberService;

@RestController("restController")
@RequestMapping("/rest")
public class IdCheckController {
	
	@Autowired
	MemberService ms;
	
	// 아이디 중복 확인	
	@RequestMapping(value="/{checkId}", method=RequestMethod.GET)
	public ResponseEntity<Boolean> checkId(@PathVariable("checkId") String id) {
		boolean flag = ms.checkUser(id);
		return ResponseEntity.ok(flag);
	}
}