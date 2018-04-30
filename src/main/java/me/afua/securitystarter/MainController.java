package me.afua.securitystarter;


import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {



    @RequestMapping("/")
    public String showMainPage()
    {
        return "index";
    }

}
