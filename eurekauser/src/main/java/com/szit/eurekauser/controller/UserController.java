package com.szit.eurekauser.controller;

import com.alibaba.fastjson.JSONArray;
import com.szit.eurekauser.pojo.User;
import com.szit.eurekauser.service.UserService;
import com.szit.eurekauser.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用户控制器类
 *  @author  刘成发
 *  @version 1.0 2020.04.05
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 判断手机号是否存在
     * @param telphone
     * @param response
     */
    @RequestMapping(value = "/isexisttelphone.html",method = RequestMethod.GET)
    protected void isExistTelphone(@RequestParam String telphone, HttpServletResponse response){
        Map<String,String> resultMap = new HashMap<String,String>();
       User user=userService.searchBytelphone(telphone);
        if(user!=null){
            resultMap.put("result","true");
        }else{
            resultMap.put("result","false");
        }
        response.setContentType("application/json");
        PrintWriter out = null;
        try{
            out = response.getWriter();
        }catch (IOException e){
            e.printStackTrace();
        }
        out.write(JSONArray.toJSONString(resultMap));
        out.flush();
        out.close();
    }

    /**
     * 通过手机号登录
     * @param phone
     * @param code
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/phonelogin.html")
    protected String phoneLogin(@RequestParam("telphone")String phone,@RequestParam("code")String code,
                                Model model,HttpSession session){
        String url="phonelogin";
        User user = userService.searchBytelphone(phone);
        if(phone!=null && !phone.equals("")) {
            if(user!=null){
                if(code!=null && !code.equals("")) {
                    if (code.equals(session.getAttribute("phoneCode"))) {
                        session.setAttribute("loginUser", user);
                        url = "redirect:userindex.html";
                    } else {
                        model.addAttribute("phone", phone);
                        model.addAttribute("message", "验证码错误！");
                    }
                }else {
                    model.addAttribute("message", "请输入验证码！");
                }
            } else {
                model.addAttribute("message","您未注册，请注册！");
            }
        }else{
            model.addAttribute("message","请输入手机号！");
        }

        return url;
    }

    /**
     * 判断用户名是否存在
     * @param userName
     * @param response
     */
    @RequestMapping(value = "/isexistusername.html",method = RequestMethod.GET)
    protected void isExistUserName(@RequestParam String userName, HttpServletResponse response){
        Map<String,String> resultMap = new HashMap<String,String>();
        boolean flag=userService.searchByUserName(userName);
        if(flag){
            resultMap.put("result","true");
        }else{
            resultMap.put("result","false");
        }
        response.setContentType("application/json");
        PrintWriter out = null;
        try{
            out = response.getWriter();
        }catch (IOException e){
            e.printStackTrace();
        }
        out.write(JSONArray.toJSONString(resultMap));
        out.flush();
        out.close();
    }

    /**
     * 访问注册页面
     * @return
     */
    @RequestMapping("/register.html")
    protected String register(){
        return "userregister";
    }

    public int r(int min,int max) {
        Random random = new Random();
        int num = 0;
        num = random.nextInt(max - min) + min;
        return num;
    }

    /**
     * 生成图片随机验证码
     * @return
     */
    @RequestMapping(value = "/img.html",method = RequestMethod.GET)
    protected void img(HttpSession session ,HttpServletResponse response) throws IOException {
        final char[] str = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
                'r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H',
                'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int width=85;
        int height=30;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(new Color(200,200,200));
        g.fillRect(0, 0, width, height);
        Random rnd = new Random();
        StringBuffer sb = new StringBuffer("");
        //产生四位数的字母数字验证码，各个数字的颜色也随即
        for(int i=0; i<4; i++) {
            int num = rnd.nextInt(str.length);
            Color c = new Color(rnd.nextInt(256),
                    rnd.nextInt(256),rnd.nextInt(256));
            g.setColor(c);
            g.setFont(new Font("黑体", Font.PLAIN, 20));
            g.drawString(str[num]+"", 10+i*20, 20);
            sb.append(str[num]);
        }

        //划干扰线
        for(int i=0; i<4; i++) {
            Color c = new Color(rnd.nextInt(256),
                    rnd.nextInt(256),rnd.nextInt(256));
            g.setColor(c);
            g.drawLine(rnd.nextInt(width), rnd.nextInt(height),
                    rnd.nextInt(width), rnd.nextInt(height));
        }
         /*
         若是产生四位数字，则nextInt(8999) + 1000;
         然后String.valueOf转换为String
         */
        String s = new String(sb);
        //验证码存入session里，方便在登陆校检页比对
        session.setAttribute("loginCode",s);
        //System.out.println("yam"+session.getAttribute("loginCode"));
        g.dispose();//释放图像的上下文资源
        //输出到页面
        ImageIO.write(bi,"PNG",response.getOutputStream());
        response.getOutputStream().flush();//刷新输出流
        response.getOutputStream().close();//关闭输出流
    }

    /**
     * 获取手机动态验证码
     * @param phone
     * @param res
     */
    @RequestMapping("/getphonecode.html")
    protected void getphonecode(@RequestParam("phone")String phone ,HttpServletResponse res, HttpSession session) {
        String param;
        if(phone!=null && !phone.equals("")) {
            param = num();
            String host = "https://zwp.market.alicloudapi.com";
            String path = "/sms/sendv2";
            String method = "GET";
            String appcode = "7ab866a47701479e9e89ed1d7128180f";
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("content", "【云通知】您的验证码是"+param+"。如非本人操作，请忽略本短信");
            querys.put("mobile", phone);
            session.setAttribute("phoneCode", param);
            System.out.println(session.getAttribute("phoneCode"));
            try {
                HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
                System.out.println(response.toString());
                //获取response的body
                //System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.setContentType("application/json");
            PrintWriter out = null;
            try {
                out = res.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.flush();
            out.close();
        }else {
            Map<String,String> resultMap = new HashMap<String,String>();
                resultMap.put("result","true");
            res.setContentType("application/json");
            PrintWriter out = null;
            try{
                out = res.getWriter();
            }catch (IOException e){
                e.printStackTrace();
            }
            out.write(JSONArray.toJSONString(resultMap));
            out.flush();
            out.close();
        }
    }

    /**
     * 手机动态码
     * @return
     */
    private String num() {
        int p = new Random().nextInt(899999) + 100000;
        String phoneCode = Integer.toString(p);
        return phoneCode;
    }

    /**
     * 用户注册
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/doregister.html")
    protected String doRegister(User user,@RequestParam("userName")String name,@RequestParam("telphone")String phone,
                                @RequestParam("userPassword")String password, @RequestParam("code")String code, Model model,HttpSession session){
        user.setuPassword(password);
        user.setUserName(name);
        user.setTelphone(phone);
        String url="userregister";
        if(user.getUserName()!=null && !user.getUserName().equals("")){
            if(user.getTelphone()!=null&& !user.getTelphone().equals("")){
                if(user.getuPassword()!=null&& !user.getuPassword().equals("")){
                    System.out.println(code);
                    System.out.println(session.getAttribute("phoneCode"));
                    if(code!=null&&!code.equals("")){
                        if(code.equals(session.getAttribute("phoneCode"))){
                            boolean flag = userService.register(user);
                            if(flag){
                                url="redirect:login.html";
                            }else{
                                model.addAttribute("user",user);
                                model.addAttribute("message","系统错误，注册失败！");
                            }
                        }else{
                            model.addAttribute("user",user);
                            model.addAttribute("message","手机验证码输入错误！");
                        }
                    }else {
                        model.addAttribute("user",user);
                        model.addAttribute("message","请输入手机验证码！");
                    }
                }else {
                    model.addAttribute("user",user);
                    model.addAttribute("message","请输入密码！");
                }
            }else {
                model.addAttribute("user",user);
                model.addAttribute("message","请输入手机号！");
            }
        }else{
            model.addAttribute("user",user);
            model.addAttribute("message","请输入用户名！");
        }
        return url;
    }

    /**
     * 访问手机号登录页面
     * @return
     */
    @RequestMapping (value = "/phonelogin.html",method = RequestMethod.GET)
    protected String plogin(){
        return "phonelogin";
    }

    /**
     * 访问登录页面
     * @return
     */
    @RequestMapping (value = "/login.html",method = RequestMethod.GET)
    protected String login(){
        return "userlogin";
    }

    /**
     * 用户通过用户名和密码登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/dologin.html")
    protected String doLogin(User user,@RequestParam("userName") String name,@RequestParam("userPassword") String userPassword,
                             @RequestParam("code") String code, Model model, HttpSession session){
        String url="userlogin";
        user.setUserName(name);
        user.setuPassword(userPassword);
        if(user.getUserName()!=null && !user.getUserName().equals("")){
            if(user.getuPassword()!=null && !user.getuPassword().equals("")){
                if(code!=null && !code.equals("")){
                    if(code. equals(session.getAttribute("loginCode"))){
                        boolean flag=userService.userLogin(user);
                        if(flag){
                            session.setAttribute("loginUser",user);
                            url="redirect:userindex.html";
                        }else{
                            model.addAttribute("user",user);
                            model.addAttribute("message","用户名或密码错误，请重新登录！");
                        }
                    }else{
                        model.addAttribute("user",user);
                        model.addAttribute("message","验证码错误，请重新输入！");
                    }
                }else{
                    model.addAttribute("user",user);
                    model.addAttribute("message","请输入验证码！");
                }
            }else{
                model.addAttribute("user",user);
                model.addAttribute("message","请输入密码！");
            }
        }else{
            model.addAttribute("user",user);
            model.addAttribute("message","请输入用户名！");

        }
        return url;
    }
    /**
     * 访问用户主页面
     * @return
     */
    @RequestMapping (value = "/userindex.html",method = RequestMethod.GET)
    protected String userlogin(HttpServletRequest request, HttpServletResponse response,
                                Model model) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();//获取本机cookie
        String lasttime=null;
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if ("lasttime".equals(cookie.getName())){
                    lasttime = URLDecoder.decode(cookie.getValue());
                    model.addAttribute("time",lasttime);
                    break;//获取到key为lasttime的cookie后，跳出循环
                }
            }
        }
        if (lasttime==null){
            model.addAttribute("time","您是第一次访问本网站");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Cookie cookie = new Cookie("lasttime", URLEncoder.encode(sdf.format(new Date())));
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        return "userindex";
    }
}
