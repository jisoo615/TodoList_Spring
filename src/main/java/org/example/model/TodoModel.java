package org.example.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor /*파라미터 없는 생성자 생성*/
@AllArgsConstructor /*모든 필드가 파라미터로 있는 생성자 생성*/
public class TodoModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(name="todoOrder", nullable=false)/*order는 이미 jpa애 존재하는 이름이라 바꿔주기*/
	private Long order;
	
	@Column(nullable=false)
	private boolean completed;
	
	
	/**
	 * @NoArgsConstructor 이면아래처럼 자동 생성
	 * public TodoEntity(){
	 * }
	 * 
	 * @AllArgsConstructor 이면 아래처럼 자동 생성
	 * public TodoEntity(Long id, String title, Long order, boolean completed){
	 * 	this.id = id;
	 * 	this.title = title;
	 * 	this.order = order;
	 * this.completed = completed;
	 * }
	 * 
	 * **/
	

}
