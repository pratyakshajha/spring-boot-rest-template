package com.example.template.dao;

import com.example.template.object.Actor;

public interface ActorDao {
	public Actor getActor(Integer id);
	public int insertActor(Actor actor);
	public int deleteActor(Integer id);
	public int updateActor(Actor actor);
	public int partialApdateActor(Actor actor);
}
