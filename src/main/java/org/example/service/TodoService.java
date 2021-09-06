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
	
	//1 todo ����Ʈ ��Ͽ� ������ �߰�
	//2 todo ����Ʈ ��Ͽ��� Ư�� ������ ��ȸ
	//3 todo ����Ʈ ��ü ����� ��ȸ
	//4 todo ����Ʈ ��Ͽ��� Ư�� ������ ����
	//5 todo ����Ʈ ��Ͽ��� Ư�� ������ ����
	//6 todo ����Ʈ ��� ��ü ����
	
	public TodoEntity add(TodoRequest request) {
		TodoEntity todoEntity = new TodoEntity();
		todoEntity.setTitle(request.getTitle());
		todoEntity.setOrder(request.getOrder());
		todoEntity.setCompleted(request.isCompleted());
		
		return todoRepository.save(todoEntity);//todoRepository�� ���ʸ��� TodoEntity�̹Ƿ� �ٷ� ����
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

		return this.todoRepository.save(todoEntity);//������Ʈ�� �Ȱ��� save���
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
