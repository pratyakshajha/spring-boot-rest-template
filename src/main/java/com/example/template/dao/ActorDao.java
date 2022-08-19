package com.example.template.dao;

import java.util.List;

import com.example.template.object.Actor;

public interface ActorDao {
	public List<Actor> searchActors(String firstName, String lastName);
	public Actor getActor(Integer id);
	public int insertActor(Actor actor);
	public int deleteActor(Integer id);
	public int updateActor(Actor actor);
	public int partialApdateActor(Actor actor);
}
