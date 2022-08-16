package com.example.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.template.dao.ActorDao;
import com.example.template.object.Actor;

@RestController
@RequestMapping("/actors")
public class ActorController {

	@Autowired
	ActorDao dao;

	@GetMapping("/{id}")
	public ResponseEntity<Actor> getActor(@PathVariable Integer id) {
		Actor actor = dao.getActor(id);
		if (actor == null)
			return new ResponseEntity<Actor>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Actor>(actor, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
		int result = dao.insertActor(actor);
		if (result == 1)
			return new ResponseEntity<Actor>(actor, HttpStatus.CREATED);
		else
			return new ResponseEntity<Actor>(actor, HttpStatus.BAD_REQUEST);
	}
}
