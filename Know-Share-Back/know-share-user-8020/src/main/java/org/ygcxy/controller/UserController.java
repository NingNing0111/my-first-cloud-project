package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.UserRequest;
import org.ygcxy.entity.User;
import org.ygcxy.repository.UserRepository;
import org.ygcxy.service.UserService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 10:28
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION +"/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseResult findUserById(
            @PathVariable Long id
    ){
        return userService.findUserById(id);
    }

    @GetMapping("/")
    public ResponseResult findUser(@RequestParam UserRequest userRequest) {
        return userService.findUserById(userRequest.getId());
    }
    @GetMapping("/email/{email}")
    public ResponseResult findByEmail(@PathVariable("email") String email){
        return userService.findUserByEmail(email);
    }
    @GetMapping("/entity/email/{email}")
    public User findByEmailEntity(@PathVariable("email") String email) {
        return userRepository.findUserByUserEmail(email);
    }
    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        log.info("插入数据:{}",user);
        try {
            userRepository.save(user);
        }catch (Exception e){
            log.info("新增用户失败:{}",e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteUserById(@PathVariable("id") Long id){
        return userService.deleteUserById(id);
    }
    @GetMapping("/page/{page}/size/{size}")
    public ResponseResult findUsers(
            @PathVariable(name = "page") Integer page,
            @PathVariable(name = "size") Integer size,
            @RequestParam(name = "type",defaultValue = "asc") String sortType,
            @RequestParam(name = "isBan",required = false) Boolean isBan
    ){
        log.info("{},{},{}",page,size,sortType);
        return userService.allUserPage(page,size, sortType,isBan);

    }

    @PutMapping("/{id}")
    public ResponseResult banUser(
            @PathVariable Long id
    ){
        return userService.banUser(id);
    }
}
