package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {//���ο���Ƽ�� ��� ���� ����
	private Long id;
	private String title;
	private Long order;
	private boolean completed;
	
	private String url;
	
	public TodoResponse(TodoModel todoModel) {
		this.id = todoModel.getId();
		this.title = todoModel.getTitle();
		this.order = todoModel.getOrder();
		this.completed = todoModel.isCompleted();
		
		this.url = "http://localhost:8080/" + this.id;//�ϴ��� �����ϰ� �̷��� �ۼ���..
	}
}
