package org.example.controller;

import org.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/")
public class TodoController {
	private final TodoService service;
	
	@PostMapping
	public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
		System.out.print("CREATE");
		
		if(ObjectUtils.isEmpty(request.getTitle())) {
			return ResponseEntity.badRequest().build();
		}
		if(ObjectUtils.isEmpty(request.getOrder())) {
			request.setOrder(0L);
		}
		if(ObjectUtils.isEmpty(request.isCompleted())) {
			request.setCompleted(false);
		}
		
		TodoEntity result = this.service.add(request);
		
		return ResponseEntity.ok(new TodoResponse(result));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
		System.out.print("READ ONE");
		TodoEntity result = this.service.searchById(id);
		return ResponseEntity.ok(new TodoResponse(result));
	}
	/**
	@GetMapping
	public ResponseEntity<List<TodoResponse>> readAll(){
		System.out.print("READ ALL");
		List<TodoEntity> list = this.service.searchAll();
		List<TodoResponse> response = list.stream().map(TodoResponse::new).collect(Collectors.toList());
		return ResponseEntity.ok(response);
	**/
	@PatchMapping("{id}")
	public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request){
		System.out.print("UPDATE");
		TodoEntity result = this.service.updateById(id, request);
		
		return ResponseEntity.ok(new TodoResponse(result));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteOne(@PathVariable Long id){
		System.out.print("DELETE ONE");
		this.service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteAll(){
		System.out.print("DELETE ALL");
		this.service.deleteAll();
		return ResponseEntity.ok().build();
	}

}
