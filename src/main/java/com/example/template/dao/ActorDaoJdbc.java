package com.example.template.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.template.dao.rowmapper.ActorRowMapper;
import com.example.template.object.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActorDaoJdbc implements ActorDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

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

	@Override
	public int insertActor(Actor actor) {
		log.info("Inserting actor {}", actor);
		String query = "INSERT INTO actor (actor_id, first_name, last_name, last_update) VALUES (:id, :firstName, :lastName, :lastUpdate);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", actor.getId()).addValue("firstName", actor.getFirstName()).addValue("lastName",
				actor.getLastName());
		params.addValue("lastUpdate", Timestamp.valueOf(actor.getLastUpdate()));
		return jdbcTemplate.update(query, params);
	}

	@Override
	public int deleteActor(Integer id) {
		log.info("Deleting actor with id {}", id);
		String query = "DELETE FROM actor WHERE actor_id = :id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbcTemplate.update(query, params);
	}

	@Override
	public int updateActor(Actor actor) {
		log.info("Updating actor {}", actor);
		String query = "UPDATE actor SET first_name = :firstName, last_name = :lastName, last_update = :lastUpdate WHERE actor_id = :id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", actor.getId()).addValue("firstName", actor.getFirstName()).addValue("lastName",
				actor.getLastName());
		params.addValue("lastUpdate", Timestamp.valueOf(actor.getLastUpdate()));
		return jdbcTemplate.update(query, params);
	}

	@Override
	public int partialApdateActor(Actor actor) {
		log.info("Partially updating actor {}", actor);
		if (!StringUtils.hasText(actor.getFirstName()) && !StringUtils.hasText(actor.getLastName()) && actor.getLastUpdate() == null)
			return 0;
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder query = new StringBuilder("UPDATE actor SET ");
		if (StringUtils.hasText(actor.getFirstName())) {
			query.append("first_name = :firstName");
			params.addValue("firstName", actor.getFirstName());
		}
		if (StringUtils.hasText(actor.getLastName())) {
			if (StringUtils.hasText(actor.getFirstName()))
				query.append(", last_name = :lastName");
			else
				query.append("last_name = :lastName");
			params.addValue("lastName", actor.getLastName());
		}
		if (actor.getLastUpdate() != null) {
			if (StringUtils.hasText(actor.getFirstName()) || StringUtils.hasText(actor.getLastName()))
				query.append(", last_update = :lastUpdate");
			else
				query.append("last_update = :lastUpdate");
			params.addValue("lastUpdate", Timestamp.valueOf(actor.getLastUpdate()));
		}
		
		query.append(" WHERE actor_id = :id;");
		params.addValue("id", actor.getId());
		return jdbcTemplate.update(query.toString(), params);
	}
	
}
