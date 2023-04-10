package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view2")
public class ViewController2 {

    public ViewController2(){
        System.out.println("****** viewController2");
    }


    @GetMapping("/home")
    public String homePage(@RequestParam(value = "name", required = false, defaultValue = "Petros") String name) {
        return "index";
    }

    @GetMapping("/user-home/id/{id}")
    public String userHome(@RequestParam(value = "name", required = false, defaultValue = "Petros") String name,
                           @PathVariable("id")int id,
                           ModelMap modelMap) {

        modelMap.addAttribute("userName", name);
        modelMap.addAttribute("id", id);
        return "userHome";
    }


}
