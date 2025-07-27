package rw.ac.auca.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.ecommerce.core.customer.model.Customer;
import rw.ac.auca.ecommerce.core.customer.service.ICustomerService;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final ICustomerService customerService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            Model model) {

        Customer customer = customerService.findByEmailAndPhoneNumber(email, phoneNumber);

        if (customer != null) {
            if (customer.getRole().equalsIgnoreCase("ADMIN")) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/customer/home"; // Or wherever regular users go
            }
        } else {
            model.addAttribute("error", "Invalid email or phone number.");
            return "login";
        }
    }
}
