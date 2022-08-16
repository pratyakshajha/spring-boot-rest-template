package com.example.template.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.template.object.Actor;

public class ActorRowMapper implements RowMapper<Actor> {

	@Override
	public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Actor actor = null;
		if (rs != null) {
			actor = new Actor();
			actor.setId(rs.getInt("actor_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actor.setLastUpdated(rs.getDate("last_update"));
		}
		return actor;
	}

}
