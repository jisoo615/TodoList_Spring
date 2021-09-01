package org.example.repository;

import org.example.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoReapository extends JpaRepository<TodoEntity, Long>{//제너릭으로 데이터주고받을 엔터티, id의 타입인 Long

}
