package com.uca.pncsegundoparcialgestiontareas.domain.dto.request.task;

import com.uca.pncsegundoparcialgestiontareas.domain.entities.Priority;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTORequest {

	@NotBlank(message = "title es requerido")
	private String title;

	private String description;

	private Status status;

	@NotNull(message = "priority es requerido")
	private Priority priority;

	@NotNull(message = "estimatedHours es requerido")
	@Min(value = 1, message = "estimatedHours tiene que ser >= 1")
	private Integer estimatedHours;

	@NotNull(message = "loggedHours es requerido")
	@Min(value = 0, message = "loggedHours must be >= 0")
	private Integer loggedHours;

	@NotNull(message = "dueDate es requerido")
	private LocalDate dueDate;

	@NotBlank(message = "assignedTo es requerido")
	private String assignedTo;

}
