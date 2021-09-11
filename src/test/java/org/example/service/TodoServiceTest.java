package org.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

	@Mock
	private TodoRepository todoRepository;
	
	@InjectMocks
	private TodoService todoService;
	
	@Test
	public void testAdd() {
		when(this.todoRepository.save(any(TodoModel.class)))
				.then(AdditionalAnswers.returnsFirstArg());
		TodoRequest expected = new TodoRequest();
		expected.setTitle("Test Title");
		TodoModel actual = this.todoService.add(expected);
		
		assertEquals(expected.getTitle(), actual.getTitle());
	}

	@Test
	public void testSearchById() {
		TodoModel todoModel = new TodoModel();
		todoModel.setId(123L);
		todoModel.setTitle("test TITLE");
		todoModel.setOrder(0L);
		todoModel.setCompleted(false);
		Optional<TodoModel> optional = Optional.of(todoModel);//optional로 매핑
		
		given(this.todoRepository.findById(anyLong()))
			.willReturn(optional);

		TodoModel actual = this.todoService.searchById(123L);
		TodoModel expected = optional.get();
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getOrder(), actual.getOrder());
		assertEquals(expected.isCompleted(), actual.isCompleted());
	}
	
	//정말 에러가 잘 발생하는지 확인하는 테스트
	@Test
	public void searchByIdFailed() {
		given( this.todoRepository.findById(anyLong()) )
				.willReturn( Optional.empty() );
		
		assertThrows(ResponseStatusException.class, () -> {
			this.todoService.searchById(123L);
		});
	}

}
