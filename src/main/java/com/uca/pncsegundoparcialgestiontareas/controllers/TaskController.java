package com.uca.pncsegundoparcialgestiontareas.controllers;

import com.uca.pncsegundoparcialgestiontareas.domain.dto.request.task.TaskDTORequest;
import com.uca.pncsegundoparcialgestiontareas.domain.dto.response.GeneralResponse;
import com.uca.pncsegundoparcialgestiontareas.domain.dto.response.task.TaskDTOResponse;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Priority;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Status;
import com.uca.pncsegundoparcialgestiontareas.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {
	private final TaskService taskService;

	@PostMapping
	public ResponseEntity<GeneralResponse> create(@Valid @RequestBody TaskDTORequest request) {
		TaskDTOResponse response = taskService.create(request);

		return new ResponseEntity<>(GeneralResponse.builder()
				.message("Task created successfully")
				.data(response)
				.build(), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<GeneralResponse> findAll(
			@RequestParam(required = false) Status status,
			@RequestParam(required = false) Priority priority
	) {
		List<TaskDTOResponse> response = taskService.findAll(status, priority);

		return ResponseEntity.ok(GeneralResponse.builder()
				.message("Tasks fetched successfully")
				.data(response)
				.build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<GeneralResponse> findById(@PathVariable Long id) {
		TaskDTOResponse response = taskService.findById(id);

		return ResponseEntity.ok(GeneralResponse.builder()
				.message("Task fetched successfully")
				.data(response)
				.build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<GeneralResponse> update(@PathVariable Long id, @Valid @RequestBody TaskDTORequest request) {
		TaskDTOResponse response = taskService.update(id, request);

		return ResponseEntity.ok(GeneralResponse.builder()
				.message("Task updated successfully")
				.data(response)
				.build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GeneralResponse> delete(@PathVariable Long id) {
		taskService.delete(id);

		return ResponseEntity.ok(GeneralResponse.builder()
				.message("Task deleted successfully")
				.build());
	}

}
