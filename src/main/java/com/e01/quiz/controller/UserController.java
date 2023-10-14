package com.e01.quiz.controller;

import com.e01.quiz.dto.UserResponse;
import com.e01.quiz.entity.User;
import com.e01.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/user")
//    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER')")
    public List<UserResponse> getUsers() {
        List<User> users = userService.getAllUsers();

        return users.stream().map(user -> UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername()).build()).toList();
    }

    @GetMapping("/user/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

//    @PutMapping("/user")
//    public MappingResponse updateUser(Principal principal, @RequestBody UpdateUserRequest updateUserRequest) {
//        String email = principal.getName();
//        User updatedUser = userService.updateUser(email, updateUserRequest);
//
//        UserResponse userResponse = UserResponse.builder()
//                .id(updatedUser.getId())
//                .name(updatedUser.getName())
//                .email(updatedUser.getEmail())
//                .roles(updatedUser.getRoles())
//                .dateOfBirth(updatedUser.getDateOfBirth())
//                .address(updatedUser.getAddress())
//                .phoneNumber(updatedUser.getPhoneNumber())
//                .language(updatedUser.getLanguage())
//                .supportedBy(updatedUser.getSupportedBy())
//                .registerType(updatedUser.getRegisterType())
//                .build();
//
//        return MappingResponse.builder()
//                .status("ok")
//                .body(userResponse)
//                .message("Update user with id = " +  updatedUser.getId() + " successfully")
//                .build();
//    }
}
