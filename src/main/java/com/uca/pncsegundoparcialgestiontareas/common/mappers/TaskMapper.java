package com.uca.pncsegundoparcialgestiontareas.common.mappers;

import com.uca.pncsegundoparcialgestiontareas.domain.dto.request.task.TaskDTORequest;
import com.uca.pncsegundoparcialgestiontareas.domain.dto.response.task.TaskDTOResponse;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Task;

public class TaskMapper {
    private TaskMapper() {
    }

    public static Task toEntity(TaskDTORequest taskDTO) {
        return Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .status(taskDTO.getStatus())
                .priority(taskDTO.getPriority())
                .estimatedHours(taskDTO.getEstimatedHours())
                .loggedHours(taskDTO.getLoggedHours())
                .dueDate(taskDTO.getDueDate())
                .assignedTo(taskDTO.getAssignedTo())
                .build();
    }

    public static TaskDTOResponse toResponse(Task task) {
        return TaskDTOResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .estimatedHours(task.getEstimatedHours())
                .loggedHours(task.getLoggedHours())
                .dueDate(task.getDueDate())
                .assignedTo(task.getAssignedTo())
                .active(task.getActive())
                .build();
    }
}
