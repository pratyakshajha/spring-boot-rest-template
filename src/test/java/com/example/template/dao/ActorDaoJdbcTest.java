package com.example.template.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.template.object.Actor;

@ExtendWith(MockitoExtension.class)
public class ActorDaoJdbcTest {

	@Mock
	NamedParameterJdbcTemplate jdbcTemplate;

	@InjectMocks
	ActorDaoJdbc dao;

	@Test
	public void shouldUpdateOnlyFirstName() throws Exception {
		Actor actor = new Actor();
		actor.setId(2);
		actor.setFirstName("first");
		Mockito.when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);

		int actual = dao.partialApdateActor(actor);

		ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(jdbcTemplate).update(queryCaptor.capture(), any(MapSqlParameterSource.class));

		String expectedQuery = "UPDATE actor SET first_name = :firstName, last_update = :lastUpdate WHERE actor_id = :id;";
		assertEquals(queryCaptor.getValue(), expectedQuery);
		assertEquals(actual, 1);
	}

	@Test
	public void shouldUpdateFirstNameAndLastUpdate() throws Exception {
		Actor actor = new Actor();
		actor.setId(2);
		actor.setFirstName("first");
		actor.setLastUpdate(LocalDateTime.ofInstant(new Timestamp(123456789).toInstant(), ZoneId.systemDefault()));
		Mockito.when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);

		int actual = dao.partialApdateActor(actor);

		ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(jdbcTemplate).update(queryCaptor.capture(), any(MapSqlParameterSource.class));

		String expectedQuery = "UPDATE actor SET first_name = :firstName, last_update = :lastUpdate WHERE actor_id = :id;";
		assertEquals(queryCaptor.getValue(), expectedQuery);
		assertEquals(actual, 1);
	}
}
