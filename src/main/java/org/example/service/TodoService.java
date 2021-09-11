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
	
	//1 todo ����Ʈ ��Ͽ� ������ �߰�
	//2 todo ����Ʈ ��Ͽ��� Ư�� ������ ��ȸ
	//3 todo ����Ʈ ��ü ����� ��ȸ
	//4 todo ����Ʈ ��Ͽ��� Ư�� ������ ����
	//5 todo ����Ʈ ��Ͽ��� Ư�� ������ ����
	//6 todo ����Ʈ ��� ��ü ����
	
	public TodoModel add(TodoRequest request) {
		TodoModel todoModel = new TodoModel();
		todoModel.setTitle(request.getTitle());
		todoModel.setOrder(request.getOrder());
		todoModel.setCompleted(request.isCompleted());
		
		return todoRepository.save(todoModel);//todoRepository�� ���ʸ��� TodoEntity�̹Ƿ� �ٷ� ����
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

		return this.todoRepository.save(todoModel);//������Ʈ�� �Ȱ��� save���
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
