package com.example.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.template.dao.ActorDao;
import com.example.template.object.Actor;

@RestController
@RequestMapping("/actors")
public class ActorController {

	@Autowired
	ActorDao dao;

	@GetMapping("/")
	public ResponseEntity<List<Actor>> searchActors(@RequestParam String firstName, @RequestParam String lastName) {
		List<Actor> actors = dao.searchActors(firstName, lastName);
		if (actors != null)
			return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
		else
			return new ResponseEntity<List<Actor>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Actor> getActor(@PathVariable Integer id) {
		Actor actor = dao.getActor(id);
		if (actor != null)
			return new ResponseEntity<Actor>(actor, HttpStatus.OK);
		else
			return new ResponseEntity<Actor>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
		int result = dao.insertActor(actor);
		if (result == 1)
			return new ResponseEntity<Actor>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Actor>(actor, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Actor> deleteActor(@PathVariable Integer id) {
		int result = dao.deleteActor(id);
		if (result == 1)
			return new ResponseEntity<Actor>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Actor>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Actor> updateActor(@PathVariable Integer id, @RequestBody Actor actor) {
		actor.setId(id);
		int result = dao.updateActor(actor);
		if (result == 1)
			return new ResponseEntity<Actor>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Actor>(actor, HttpStatus.BAD_REQUEST);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Actor> partialApdateActor(@PathVariable Integer id, @RequestBody Actor actor) {
		actor.setId(id);
		int result = dao.partialApdateActor(actor);
		if (result == 1)
			return new ResponseEntity<Actor>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Actor>(HttpStatus.BAD_REQUEST);
	}
}
