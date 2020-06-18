package com.dev.cinemaproject.service;

import com.dev.cinemaproject.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getRoleByName(String roleName);
}
