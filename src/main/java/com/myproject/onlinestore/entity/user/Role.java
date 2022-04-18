package com.myproject.onlinestore.entity.user;

import java.util.Set;

public enum Role {
	ROLE_USER(Set.of(Permission.STANDART_PERMISSION)),
	
	ROLE_ADMIN(Set.of(Permission.STANDART_PERMISSION));

	private Set<Permission> permissions;

	Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}
}