package org.ygcxy.controller.pvt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.entity.User;
import org.ygcxy.repository.UserRepository;

/**
 * @Project: org.ygcxy.controller.pvt
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 22:22
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/cloud/user")
@RequiredArgsConstructor
public class UserCloudController {
    private final UserRepository userRepository;
    @GetMapping("/id/{id}")
    public User findUserById(@PathVariable Long id){
        return userRepository.findUserByUserId(id);
    }
    @GetMapping("/email/{email}")
    public User findUserByEmail(@PathVariable String email){
        return userRepository.findUserByUserEmail(email);
    }
    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

}
