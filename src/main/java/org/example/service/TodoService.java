package org.example.service;

import org.example.repository.TodoReapository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoService {
	
	private final TodoReapository todoRepository;
	

}
