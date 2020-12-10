package com.example.barsa.services.rest;

import com.example.barsa.db.entities.User;
import com.example.barsa.dto.UserDto;
import com.example.barsa.services.data.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("BarsaREST")
public class UserDataRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable Long id) {
        System.out.println("UserDataRestController.getUser()");

        User user = userService.getUserById(id);

        return user == null ? null : new UserDto(user);
    }

    @RequestMapping("/users")
    public List<UserDto> getUsers(@RequestParam int page, @RequestParam int pageSize) {
        System.out.println("UserDataRestController.getUsers() [page=" + page + ", pageSize=" + pageSize + "]");

        List<User> list = userService.getUsers(page, pageSize);

        return list.stream().map(obj -> new UserDto(obj)).collect(Collectors.toList());
    }

    @PostMapping(value = "/users/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> searchByParams(@RequestBody UserDto knownParams) {
        System.out.println("UserDataRestController.searchByParams() [knownParams=" + knownParams + "]");

        List<User> list = userService.findUsersByParams(knownParams);

        return list.stream().map(obj -> new UserDto(obj)).collect(Collectors.toList());
    }
}
