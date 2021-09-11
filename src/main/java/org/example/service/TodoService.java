package org.example.service;

import java.util.List;

import org.example.model.TodoModel;
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
	
	public TodoModel add(TodoRequest request) {
		TodoModel todoModel = new TodoModel();
		todoModel.setTitle(request.getTitle());
		todoModel.setOrder(request.getOrder());
		todoModel.setCompleted(request.isCompleted());
		
		return todoRepository.save(todoModel);//todoRepository는 제너릭이 TodoEntity이므로 바로 리턴
	}
	
	public TodoModel searchById(Long id) {
		
		return this.todoRepository.findById(id)
						.orElseThrow();
	}
	
	public List<TodoModel> searchAll() {
		return this.todoRepository.findAll();
	}
	
	public TodoModel updateById(Long id, TodoRequest request) {
		TodoModel todoModel = this.searchById(id);
		if(request.getTitle()!=null) {
			todoModel.setTitle(request.getTitle());
		}
		if(request.getOrder()!=null) {
			todoModel.setOrder(request.getOrder());
		}
		if(todoModel.isCompleted() != request.isCompleted()) {
			todoModel.setCompleted(request.isCompleted());
		}

		return this.todoRepository.save(todoModel);//업데이트도 똑같이 save사용
	}
	
	public TodoModel deleteById(Long id) {
		this.todoRepository.deleteById(id);
		return null;
	}
	
	public TodoModel deleteAll() {
		this.todoRepository.deleteAll();
		return null;
	}

}
