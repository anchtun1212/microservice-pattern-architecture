package com.course.microservice.api.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.course.microservice.api.response.PerformanceAppraisal;

@FeignClient(name = "performanceAppraisalClient", url = "http://localhost:8884")
public interface PerformanceAppraisalClient {

	@GetMapping(value = "/api/composition/appraisal")
	ResponseEntity<List<PerformanceAppraisal>> getAppraisals();

}
