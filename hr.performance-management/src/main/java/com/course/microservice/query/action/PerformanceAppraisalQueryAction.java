package com.course.microservice.query.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.microservice.entity.PerformanceAppraisal;
import com.course.microservice.repository.PerformanceAppraisalRepository;

@Component
public class PerformanceAppraisalQueryAction {

	@Autowired
	private PerformanceAppraisalRepository repository;

	public List<PerformanceAppraisal> getAllPerformanceAppraisal() {
		var result = new ArrayList<PerformanceAppraisal>();
		repository.findAll().forEach(result::add);
		return result;
	}

}
