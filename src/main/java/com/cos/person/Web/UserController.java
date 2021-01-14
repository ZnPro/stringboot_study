package com.cos.person.Web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.CommonDto;
import com.cos.person.domain.JoinReqDto;
import com.cos.person.domain.UpdateReqDto;
import com.cos.person.domain.User;
import com.cos.person.domain.UserRepository;

import lombok.RequiredArgsConstructor;


@RestController
public class UserController {
	
	
	private final UserRepository usereRepository;

	//DI = 의존성 주입
	public UserController(UserRepository userRepository) {
		this.usereRepository = userRepository;
	}

	// http://localhost:8000/user
	@GetMapping("/user")
	public CommonDto<List<User>> findAll() {
		System.out.println("findAll()");
		return new CommonDto<>(HttpStatus.OK.value(),usereRepository.findAll());//MessageConverter => (JavaObject->Json String)
	}

	// http://localhost:8000/user/2
	@GetMapping("/user/{id}")
	public CommonDto<User> findById(@PathVariable int id) {
		System.out.println("findById(): id: "+id);
		return new  CommonDto<>(HttpStatus.OK.value(),usereRepository.findById(id));
	}

	@CrossOrigin
	// http://localhost:8000/user
	@PostMapping("/user")
	// x-www-form-urlencoded =>request.getParameter()
	// text/plain => @RequestBody 어노테이션
	// application/json => @ResponseBody 어노테이션 + 오브젝트로 받기
	public CommonDto<String> save(@RequestBody JoinReqDto dto) {
		System.out.println("save()");
		System.out.println("user" + dto);
		usereRepository.save(dto);
		
		return new CommonDto<>(HttpStatus.CREATED.value(),"ok");
		//System.out.println("data: "+data);
		//System.out.println("password: "+password);
		//System.out.println("phone: "+phone);
	}

	// http://localhost:8000/user/2
	@DeleteMapping("/user/{id}")
	public CommonDto delete(@PathVariable int id) {
		System.out.println("delete()");
		usereRepository.delete(id);
		return new CommonDto<>(HttpStatus.OK.value());
	}

	// http://localhost:8000/user/2
	@PutMapping("/user/{id}")
	public CommonDto update(@PathVariable int id, @RequestBody UpdateReqDto dto) {
		System.out.println("update()");
		usereRepository.update(id, dto);
		return new CommonDto<>(HttpStatus.OK.value());
	}

}
