package com.example.template.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.template.dao.rowmapper.ActorRowMapper;
import com.example.template.object.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActorDaoJdbc implements ActorDao {
	
	@Autowired private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Actor getActor(Integer id) {
		Actor actor = null;
		log.info("Searching for actor with id {}", id);
		String query = "SELECT actor_id, first_name, last_name, last_update FROM actor WHERE actor_id = :id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		List<Actor> actors = jdbcTemplate.query(query, params, new ActorRowMapper());
		if (actors.size() > 0)
			actor = actors.get(0);
		return actor;
	}

}