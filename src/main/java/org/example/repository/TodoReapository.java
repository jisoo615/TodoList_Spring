package org.example.repository;

import org.example.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoReapository extends JpaRepository<TodoEntity, Long>{//���ʸ����� �������ְ���� ����Ƽ, id�� Ÿ���� Long

}
