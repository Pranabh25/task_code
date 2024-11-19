package com.springboot.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.task.dto.ResponseMessageDto;
import com.springboot.task.exception.ResourceNotFoundException;
import com.springboot.task.model.Task;
import com.springboot.task.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/task/hello")
    public String sayHello() {
        return "Hello Connected to Task Managemet";
    }
	
	@GetMapping("/task/all")
	public List<Task> getAllTask() {
		List<Task> list = taskService.getAllTask();
		return list;
	}
	
	@GetMapping("/task/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable int id, ResponseMessageDto dto) {
	    try {
	        Task task = taskService.getTaskById(id);
	        return ResponseEntity.ok(task);
	    } catch (ResourceNotFoundException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	@PostMapping("/task/add")
	public Task addTask(@RequestBody Task task) {
		 System.out.println(task);
		return taskService.insert(task);
	}
	
	@PutMapping("/task/update/{id}")
	public ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody Task newTask, ResponseMessageDto dto) throws ResourceNotFoundException {

	    Task existingTask = taskService.validate(id);

	    if (newTask.getTitle() != null)
	        existingTask.setTitle(newTask.getTitle());

	    if (newTask.getDescription() != null)
	        existingTask.setDescription(newTask.getDescription());

	    if (newTask.getDueDate() != null)
	        existingTask.setDueDate(newTask.getDueDate());

	    if (newTask.getPriority() != null)
	        existingTask.setPriority(newTask.getPriority());

	    if (newTask.getStatus() != null)
	        existingTask.setStatus(newTask.getStatus());

	    existingTask = taskService.insert(existingTask);
	    dto.setMsg("Task updated successfully.");
	    return ResponseEntity.ok(existingTask);
	}
	
	@DeleteMapping("/task/delete/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable int id, ResponseMessageDto dto) {
		try {
			taskService.validate(id);
			taskService.delete(id);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		} 
		dto.setMsg("Task Deleted");
		return ResponseEntity.ok(dto);
		}

}
