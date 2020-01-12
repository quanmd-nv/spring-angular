package com.angular.rest;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angular.data.model.SidebarScrenModel;
import com.angular.data.model.SidebarScrenModel.ItemGroup;
import com.angular.data.repository.SidebarScrenRepository;
import com.angular.rest.dto.SidebarScreen;
import com.angular.rest.dto.TestDto;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestResource {

	@Autowired
	private SidebarScrenRepository repository;

	@PostMapping("/add-sidebar")
	public Mono<Void> addSidebarMenu(@RequestBody TestDto model) {
		ItemGroup group = new SidebarScrenModel().new ItemGroup();
		group.setTitle(model.getGroup());
		Set<ItemGroup> groups = new HashSet<>();
		groups.add(group);

		return this.repository.findByItemGroupsIn(groups).switchIfEmpty(saveWithoutGroupParent(model)).then();
	}
	
	@PostMapping("/test")
	public Mono<Void> test(@RequestBody String s) {
		return Mono.empty();
	}
	
	Mono<SidebarScrenModel> saveWithoutGroupParent(TestDto model) {
		SidebarScrenModel sidebarScrenModel = new SidebarScrenModel();
		sidebarScrenModel.setScreenName(SidebarScreen.fromValue(model.getGroup()).getName());
		ItemGroup itemGroups = new SidebarScrenModel().new ItemGroup();
		itemGroups.setTitle(model.getItemName());
		itemGroups.setIconClass(model.getIconClass());
		itemGroups.setLink(model.getLink());
		Set<ItemGroup> groups = new HashSet<>();
		groups.add(itemGroups);
		sidebarScrenModel.setItemGroups(groups);
		return repository.save(sidebarScrenModel);
	}
	
}
