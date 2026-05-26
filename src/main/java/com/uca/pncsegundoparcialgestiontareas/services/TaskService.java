package com.uca.pncsegundoparcialgestiontareas.services;

import com.uca.pncsegundoparcialgestiontareas.common.mappers.TaskMapper;
import com.uca.pncsegundoparcialgestiontareas.domain.dto.request.task.TaskDTORequest;
import com.uca.pncsegundoparcialgestiontareas.domain.dto.response.task.TaskDTOResponse;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Priority;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Status;
import com.uca.pncsegundoparcialgestiontareas.domain.entities.Task;
import com.uca.pncsegundoparcialgestiontareas.exceptions.BusinessRuleException;
import com.uca.pncsegundoparcialgestiontareas.exceptions.ResourceNotFoundException;
import com.uca.pncsegundoparcialgestiontareas.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskDTOResponse create(TaskDTORequest request) {
        validateCreateRules(request);

        Task task = TaskMapper.toEntity(request);
        task.setTitle(request.getTitle().trim());
        task.setAssignedTo(request.getAssignedTo().trim());
        task.setStatus(Status.PENDING);
        task.setActive(true);

        return TaskMapper.toResponse(taskRepository.save(task));
    }

    public List<TaskDTOResponse> findAll(Status status, Priority priority) {
        List<Task> tasks;

        if (status != null && priority != null) {
            tasks = taskRepository.findByStatusAndPriority(status, priority);
        } else if (status != null) {
            tasks = taskRepository.findByStatus(status);
        } else if (priority != null) {
            tasks = taskRepository.findByPriority(priority);
        } else {
            tasks = taskRepository.findAll();
        }

        return tasks.stream().map(TaskMapper::toResponse).toList();
    }

    public TaskDTOResponse findById(Long id) {
        return TaskMapper.toResponse(getTaskOrThrow(id));
    }

    public TaskDTOResponse update(Long id, TaskDTORequest request) {
        Task task = getTaskOrThrow(id);

        validateCommonRules(request, true);
        validateTitleUniquenessOnUpdate(id, request.getTitle());

        task.setTitle(request.getTitle().trim());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setEstimatedHours(request.getEstimatedHours());
        task.setLoggedHours(request.getLoggedHours());
        task.setDueDate(request.getDueDate());
        task.setAssignedTo(request.getAssignedTo().trim());
        task.setActive(!Status.DONE.equals(request.getStatus()) && !Status.CANCELLED.equals(request.getStatus()));

        return TaskMapper.toResponse(taskRepository.save(task));
    }

    public void delete(Long id) {
        Task task = getTaskOrThrow(id);

        if (Status.IN_PROGRESS.equals(task.getStatus()) || Status.REVIEW.equals(task.getStatus())) {
            throw new BusinessRuleException("No se puede borrar un task con estado de IN_PROGRESS o REVIEW");
        }

        taskRepository.delete(task);
    }

    private Task getTaskOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task no encontrado"));
    }

    private void validateCreateRules(TaskDTORequest request) {
        validateCommonRules(request, false);

        if (request.getStatus() != null && !Status.PENDING.equals(request.getStatus())) {
            throw new BusinessRuleException("Nuevos tasks deben iniciar con estado PENDING");
        }

        if (taskRepository.existsByTitleIgnoreCase(request.getTitle().trim())) {
            throw new BusinessRuleException("Un task con el mismo título ya existe");
        }
    }

    private void validateTitleUniquenessOnUpdate(Long id, String title) {
        taskRepository.findByTitleIgnoreCase(title.trim())
                .filter(task -> !task.getId().equals(id))
                .ifPresent(task -> {
                    throw new BusinessRuleException("Un task con el mismo título ya existe");
                });
    }

    private void validateCommonRules(TaskDTORequest request, boolean requireStatus) {
        if (requireStatus && request.getStatus() == null) {
            throw new BusinessRuleException("status es requerido");
        }

        if (request.getLoggedHours() > request.getEstimatedHours()) {
            throw new BusinessRuleException("loggedHours no puede superar a estimatedHours");
        }

        if (!request.getDueDate().isAfter(LocalDate.now())) {
            throw new BusinessRuleException("dueDate debe ser una fecha futura");
        }
    }
}
