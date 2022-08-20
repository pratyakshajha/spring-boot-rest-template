package com.example.template.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.template.dao.ActorDao;
import com.example.template.object.Actor;

@Service
public class ActorServiceImpl implements ActorService {
	
	@Autowired
	private ActorDao dao;

	@Override
	public List<Actor> searchActors(Optional<Integer> limit, Optional<Integer> offset) {
		Integer limitParam = 100, offsetParam = 0;
		if (limit.isPresent())
			limitParam = limit.get();
		if (offset.isPresent())
			offsetParam = offset.get();
		return dao.searchActors(limitParam, offsetParam);
	}

}
