package com.dev.cinemaproject.controllers;

import com.dev.cinemaproject.model.Role;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.service.RoleService;
import com.dev.cinemaproject.service.ShoppingCartService;
import com.dev.cinemaproject.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InjectDataController(RoleService roleService,
                                ShoppingCartService shoppingCartService,
                                UserService userService,
                                PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        injectRoles();
        injectUsers();
    }

    private void injectRoles() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(adminRole);
        roleService.add(userRole);
    }

    private void injectUsers() {
        User admin = new User();
        admin.setEmail("admin@gmail.ua");
        admin.setPassword(passwordEncoder.encode("uashka"));
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        User user = new User();
        user.setEmail("user@gmail.ua");
        user.setPassword(passwordEncoder.encode("usver"));
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.add(admin);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(admin);
        shoppingCartService.registerNewShoppingCart(user);
    }
}
