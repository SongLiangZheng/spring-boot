package com.example.demo;

import com.example.demo.dao.IClientinfoDAO;
import com.example.demo.dto.Clientinfo;
import com.example.demo.dto.Friend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @GetMapping("/gender")
    public ModelAndView freemarker() {
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("张三", 20));
        friends.add(new Friend("李四", 22));

        ModelAndView mav = new ModelAndView("/gender");
        mav.addObject("name", "[Angel -- 守护天使]");
        mav.addObject("gender", 1);// gender:性别，1：男；0：女；
        mav.addObject("friends", friends);
        return mav;
    }

    @PostMapping("/doLogin")
    public String dologin(String username, String password,
            Model model) {
        String msg = username.equals("admin") && password.equals("1234") ? "登录成功" : "失败";
        model.addAttribute("message", msg);
        return "home";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @Autowired
    private IClientinfoDAO clientinfoDAO;

    @GetMapping("/clients")
    @ResponseBody
    public List<Clientinfo> getClients(){
        return clientinfoDAO.queryClientinfo(new HashMap<>());
    }
}