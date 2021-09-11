package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {//투두엔터티의 모든 값을 써줌
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
		
		this.url = "http://localhost:8080/" + this.id;//일단은 간단하게 이렇게 작성함..
	}
}
