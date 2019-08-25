package com.tushar.usermanagement.resources;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.tushar.usermanagement.config.UserRegistrationConfig;
import com.tushar.usermanagement.exception.CustomResponse;
import com.tushar.usermanagement.message.UserRegistrationNotification;
import com.tushar.usermanagement.model.User;
import com.tushar.usermanagement.request.RegistrationRequest;
import com.tushar.usermanagement.service.UserManagementService;

@RestController
public class UserManagementController {

	@Autowired
	private UserManagementService service;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private UserRegistrationConfig userRegistrationConfig;

	@GetMapping("/getAll")
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@CrossOrigin
	@PostMapping("/user")
	public ResponseEntity<CustomResponse> registerUser(@Validated @RequestBody RegistrationRequest request) {
		System.out.println(request);
		User registeredUser = new User(request.getEmail(), request.getPassword());
		service.save(registeredUser);
		rabbitTemplate.convertAndSend(userRegistrationConfig.getExchangeName(), userRegistrationConfig.getRoutingKey(),
				new UserRegistrationNotification(registeredUser.getId(), registeredUser.getEmail(),
						registeredUser.getPassword()));

		CustomResponse success = new CustomResponse();
		success.setTimestamp(LocalDateTime.now());
		success.setMessage("Thanks for signing up. An email has been sent to " + request.getEmail());
		success.setStatus(HttpStatus.CREATED.value());

		return new ResponseEntity<>(success, HttpStatus.CREATED);
	}

	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "duplicate email address")
	@ExceptionHandler(value = { MongoWriteException.class, DuplicateKeyException.class })
	public void duplicateEmailAddress() {
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<CustomResponse> invalidInput(MethodArgumentNotValidException e) {

		StringBuffer buffer = new StringBuffer();
		for (ObjectError err : e.getBindingResult().getAllErrors()) {
			buffer.append(err.getDefaultMessage() + " , ");
		}

		CustomResponse errors = new CustomResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(buffer.substring(0, buffer.length() - 2).toString());
		errors.setStatus(HttpStatus.PRECONDITION_FAILED.value());

		return new ResponseEntity<>(errors, HttpStatus.PRECONDITION_FAILED);
	}

}
