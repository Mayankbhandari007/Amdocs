package com.uxpsystems.assignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("assignement")
public class AssignmentController {

	private static Logger logger = LoggerFactory.getLogger(AssignmentController.class);
	@Autowired
	UserService service;

	@CrossOrigin
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {

		List<User> u = service.getAllUser();

		return new ResponseEntity<List<User>>(u, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable(value = "id") long id) {
		User u = service.getUser(id);

		if (u != null) {
			return new ResponseEntity<User>(u, HttpStatus.OK);

		} else {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	}

	@CrossOrigin
	@PostMapping(value = "/postUser")
	public ResponseEntity<String> postUser(@Valid @RequestBody User u) {

		if (u.getStatus().equalsIgnoreCase("Activated") || u.getStatus().equalsIgnoreCase("Deactivated")) {
			service.postUser(u);

			return new ResponseEntity<String>("Data Created", HttpStatus.CREATED);

		} else {
			return new ResponseEntity<String>("possible values of status are incorrect", HttpStatus.BAD_REQUEST);

		}

	}

	@CrossOrigin
	@PutMapping(value = "/putUser/{id}")
	public ResponseEntity<String> putUser(@PathVariable(value = "id") long userId, @Valid @RequestBody User u)

	{

		if (u.getStatus().equalsIgnoreCase("Activated") || u.getStatus().equalsIgnoreCase("Deactivated")) {
			String isSaved = service.putUser(u, userId);

			if (isSaved.equalsIgnoreCase("saved")) {

				return new ResponseEntity<String>("Data Updated SuccessFully", HttpStatus.OK);

			} else {
				return new ResponseEntity<String>("Data Not Found", HttpStatus.NO_CONTENT);

			}

		}

		else {

			return new ResponseEntity<String>("possible values of status are incorrect", HttpStatus.BAD_REQUEST);

		}
	}

	@CrossOrigin
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable(value = "id") long userId) {

		String isDeleted = service.deleteUser(userId);
		if (isDeleted.equalsIgnoreCase("Y")) {

			return new ResponseEntity<String>("Deleted SuccessFully", HttpStatus.OK);

		}

		else {

			return new ResponseEntity<String>("Data Not Found", HttpStatus.NO_CONTENT);

		}

	}

}
