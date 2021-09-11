package org.example.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor /*�Ķ���� ���� ������ ����*/
@AllArgsConstructor /*��� �ʵ尡 �Ķ���ͷ� �ִ� ������ ����*/
public class TodoModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(name="todoOrder", nullable=false)/*order�� �̹� jpa�� �����ϴ� �̸��̶� �ٲ��ֱ�*/
	private Long order;
	
	@Column(nullable=false)
	private boolean completed;
	
	
	/**
	 * @NoArgsConstructor �̸�Ʒ�ó�� �ڵ� ����
	 * public TodoEntity(){
	 * }
	 * 
	 * @AllArgsConstructor �̸� �Ʒ�ó�� �ڵ� ����
	 * public TodoEntity(Long id, String title, Long order, boolean completed){
	 * 	this.id = id;
	 * 	this.title = title;
	 * 	this.order = order;
	 * this.completed = completed;
	 * }
	 * 
	 * **/
	

}
