package com.szit.eurekamanager.controller;

import com.szit.eurekamanager.pojo.Admin;
import com.szit.eurekamanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/userlogin.html")
    protected void userLogin(){
         this.restTemplate.getForObject("http://localhost:8001/user/login.html",String.class);
    }

    /**
     * 访问工作人员登录页面
     * @return
     */
    @RequestMapping("/login.html")
    protected String login(){
        return "workerlogin";
    }


    /**
     * 工作人员登录
     * @param admin
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/dologin.html")
    protected String dologin(Admin admin, @RequestParam("anum")String anum,
                             @RequestParam("aPassword") String password, Model model, HttpSession session){
        admin.setAnum(anum);
        admin.setaPassword(password);
        System.out.println(anum);
        System.out.println(password);
        String url="workerlogin";
        if(anum!=null && !anum.equals("")){
            if(password!=null && !password.equals("")){
                boolean flag=adminService.adminLogin(admin);
                if(flag){
                    session.setAttribute("loginAdmin",admin);
                    url="redirect:workermain.html";
                }else{
                    model.addAttribute("admin",admin);
                    model.addAttribute("message","账号或密码错误，请重新输入！");
                }
            }else {
                model.addAttribute("message","请输入密码！");
            }
        }else {
            model.addAttribute("message","请输入账号！");
        }

        return url;
    }

    /**
     * 访问工作人员主页
     * @return
     */
    @GetMapping("/workermain.html")
    protected String visitWorkerMain(){
        return "workermain";
    }

}
