package org.example.service;

import java.util.List;

import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoService {
	
	private final TodoRepository todoRepository;
	
	//1 todo 리스트 목록에 아이템 추가
	//2 todo 리스트 목록에서 특정 아이템 조회
	//3 todo 리스트 전체 목록을 조회
	//4 todo 리스트 목록에서 특정 아이템 수정
	//5 todo 리스트 목록에서 특정 아이템 삭제
	//6 todo 리스트 목록 전체 삭제
	
	public TodoEntity add(TodoRequest request) {
		TodoEntity todoEntity = new TodoEntity();
		todoEntity.setTitle(request.getTitle());
		todoEntity.setOrder(request.getOrder());
		todoEntity.setCompleted(request.isCompleted());
		
		return todoRepository.save(todoEntity);//todoRepository는 제너릭이 TodoEntity이므로 바로 리턴
	}
	
	public TodoEntity searchById(Long id) {
		
		return this.todoRepository.findById(id)
						.orElseThrow();
	}
	
	public List<TodoEntity> searchAll() {
		return this.todoRepository.findAll();
	}
	
	public TodoEntity updateById(Long id, TodoRequest request) {
		TodoEntity todoEntity = this.searchById(id);
		if(request.getTitle()!=null) {
			todoEntity.setTitle(request.getTitle());
		}
		if(request.getOrder()!=null) {
			todoEntity.setOrder(request.getOrder());
		}
		if(todoEntity.isCompleted() != request.isCompleted()) {
			todoEntity.setCompleted(request.isCompleted());
		}

		return this.todoRepository.save(todoEntity);//업데이트도 똑같이 save사용
	}
	
	public TodoEntity deleteById(Long id) {
		this.todoRepository.deleteById(id);
		return null;
	}
	
	public TodoEntity deleteAll() {
		this.todoRepository.deleteAll();
		return null;
	}

}
