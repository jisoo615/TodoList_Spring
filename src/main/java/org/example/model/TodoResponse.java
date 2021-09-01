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
	
	public TodoResponse(TodoEntity todoEntity) {
		this.id = todoEntity.getId();
		this.title = todoEntity.getTitle();
		this.order = todoEntity.getOrder();
		this.completed = todoEntity.isCompleted();
		
		this.url = "http://localhost:8080/" + this.id;//일단은 간단하게 이렇게 작성함..
	}
}
