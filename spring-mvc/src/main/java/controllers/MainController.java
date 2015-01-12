package controllers;

import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import services.CustomerService;

@Controller
public class MainController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("message", "Home Page!");
        return "index";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getAllCustomers(ModelMap model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @RequestMapping(value = "/customers/{customerId}")
    public String getCustomerById(@PathVariable String customerId, Model model) {

        int id;

        try {
           id = Integer.parseInt(customerId);
           Customer customer = customerService.getCustomerById(id);

           model.addAttribute("customer", customer);
           model.addAttribute("customerId", id);

        } catch (Exception e){
           model.addAttribute("wrongId", "Wrong customer Id!");
        }

        return "customer";
    }

    @RequestMapping("*")
    @ResponseBody
    public String fallbackMethod(){
        return "fallback method";
    }

}