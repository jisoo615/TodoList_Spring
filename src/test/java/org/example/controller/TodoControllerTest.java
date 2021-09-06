package org.example.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TodoController.class)
class TodoControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	TodoService todoService;//Mock bean으로 사용해줄 todoService
	
	private TodoEntity expected;
	
	@BeforeEach
	void setUp() {//매번 테스트 전에 객체 초기화
		this.expected = new TodoEntity();
		this.expected.setId(123L);
		this.expected.setTitle("TEST TITLE");
		this.expected.setOrder(0L);
		this.expected.setCompleted(false);
	}

	@Test
	void testCreate() throws Exception{
		when( this.todoService.add(any(TodoRequest.class)))
				.then( (i) -> {
					TodoRequest request = i.getArgument(0, TodoRequest.class);
					return new TodoEntity(this.expected.getId(),
							request.getTitle(),	/*타이틀만 request 값 반환*/
							this.expected.getOrder(),
							this.expected.isCompleted());
				});
		TodoRequest request = new TodoRequest();
		request.setTitle("ANY TITLE");
		
		//request body에 넣어줘야 하기 때문에 object mapper 사용
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(request);//request가 string형태로 바뀜
		
		this.mvc.perform(   post("/")
							.contentType(MediaType.APPLICATION_JSON)
							.content(content))
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.title").value("ANY TITLE") );
		
	}

}
