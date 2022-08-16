package com.example.template.dao;

import com.example.template.object.Actor;

public interface ActorDao {
	public Actor getActor(Integer id);
	public int insertActor(Actor actor);
}
