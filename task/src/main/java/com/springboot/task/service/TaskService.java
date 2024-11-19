package com.springboot.task.service;

import java.util.List;
import java.util.Optional;

import com.springboot.task.exception.ResourceNotFoundException;
import com.springboot.task.model.Task;
import com.springboot.task.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

	public Task insert(Task task) {
		return taskRepository.save(task);
	}

	public Task validate(int id) throws ResourceNotFoundException {
		Optional<Task> optional = taskRepository.findById(id);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("Task id is invalid");
		return optional.get();
	}

	public Task getTaskById(int Id) throws ResourceNotFoundException {
	    Optional<Task> optionalTask = taskRepository.findById(Id);
	    if (optionalTask.isPresent()) {
	        return optionalTask.get();
	    } else {
	        throw new ResourceNotFoundException("Task id is invalid");
	    }
	}
	
	public void delete(int id) {
		taskRepository.deleteById(id);
	}

}
