package com.example.template.service;

import java.util.List;
import java.util.Optional;

import com.example.template.object.Actor;

public interface ActorService {
	public List<Actor> searchActors(Optional<Integer> limit, Optional<Integer> offset);
}
