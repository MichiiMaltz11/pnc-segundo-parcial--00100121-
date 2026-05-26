package com.uca.pncsegundoparcialgestiontareas.domain.dto.response.task;

import com.uca.pncsegundoparcialgestiontareas.domain.entities.Priority;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTOResponse {
	private Long id;
	private String title;
	private String description;
	private Status status;
	private Priority priority;
	private Integer estimatedHours;
	private Integer loggedHours;
	private LocalDate dueDate;
	private String assignedTo;
	private Boolean active;

}
