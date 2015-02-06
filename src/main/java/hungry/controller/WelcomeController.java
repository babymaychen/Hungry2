package hungry.controller;

import hungry.model.LoginModel;
import hungry.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Welcome 页面
 * @author sun_tianshu
 *
 */
@Controller
public class WelcomeController {
    @Autowired
    private AuthenticationService authenticationService;

    //モデルの初期設定
    @ModelAttribute("loginModel")
    public LoginModel createInitLoginMode() {
        LoginModel loginModel = new LoginModel();
        return loginModel;
    }

    @RequestMapping(value = "")
    public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response, WebRequest webRequest, @ModelAttribute LoginModel loginModel) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, WebRequest webRequest, @ModelAttribute LoginModel loginModel) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }


}
