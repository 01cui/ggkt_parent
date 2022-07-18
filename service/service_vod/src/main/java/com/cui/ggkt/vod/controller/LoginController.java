package com.cui.ggkt.vod.controller;

import com.cui.R.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/3 16:27
 * {@code @Version} 1.0
 */
@RestController
@RequestMapping("/vod/user")
public class LoginController {


    @PostMapping("login")
    public Result<HashMap<String, String>> login() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("token", "admin-token");
        return Result.ok(stringStringHashMap);
    }

    @GetMapping("info")
    public Result<HashMap<String, String>> info() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();

        stringStringHashMap.put("roles", "admin");
        stringStringHashMap.put("introduction", "I am a super administrator");
        stringStringHashMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        stringStringHashMap.put("name", "Super Admin");
        return Result.ok(stringStringHashMap);
    }


}