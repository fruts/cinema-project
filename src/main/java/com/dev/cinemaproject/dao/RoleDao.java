package com.dev.cinemaproject.dao;

import com.dev.cinemaproject.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Role getRoleByName(String roleName);
}
