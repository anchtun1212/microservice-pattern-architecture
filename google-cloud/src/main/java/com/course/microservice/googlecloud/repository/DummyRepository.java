package com.course.microservice.googlecloud.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.course.microservice.googlecloud.entity.Dummy;

@Repository
@RepositoryRestResource(collectionResourceRel = "dummy", path = "dummy")
public interface DummyRepository extends PagingAndSortingRepository<Dummy, UUID> {

}
