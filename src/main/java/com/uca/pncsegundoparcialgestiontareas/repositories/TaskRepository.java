package com.uca.pncsegundoparcialgestiontareas.repositories;

import com.uca.pncsegundoparcialgestiontareas.domain.entities.Task;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Priority;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	boolean existsByTitleIgnoreCase(String title);

	Optional<Task> findByTitleIgnoreCase(String title);

	List<Task> findByStatus(Status status);

	List<Task> findByPriority(Priority priority);

	List<Task> findByStatusAndPriority(Status status, Priority priority);

}
