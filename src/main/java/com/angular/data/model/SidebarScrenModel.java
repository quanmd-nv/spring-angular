package com.angular.data.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "sidebar_items")
public class SidebarScrenModel {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String screenName;
	
	private Set<ItemGroup> itemGroups;

	@Data
	@EqualsAndHashCode
	public class ItemGroup {
		
		@EqualsAndHashCode.Include
		private String title;
		private String iconClass;
		private String link;
		private Set<Item> items;
		
		public ItemGroup() {
			
		}
		
		@Data
		@EqualsAndHashCode
		public class Item {
			
			@EqualsAndHashCode.Include
			private String title;
			private String iconClass;
			private String link;	
			
		}
	}
}
