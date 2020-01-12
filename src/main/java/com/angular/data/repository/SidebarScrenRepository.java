package com.angular.data.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.angular.data.model.SidebarScrenModel;
import com.angular.data.model.SidebarScrenModel.ItemGroup;

import reactor.core.publisher.Mono;

@Repository
public interface SidebarScrenRepository extends ReactiveMongoRepository<SidebarScrenModel, String> {

	Mono<SidebarScrenModel> findByItemGroupsIn(Set<ItemGroup> items);
}
