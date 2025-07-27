package rw.ac.auca.ecommerce.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Admin Controller to serve admin dashboard page.
 */
@Controller
public class AdminController {

    @GetMapping("/admin/home")
    public String getAdminHomePage() {
        return "customer/admin-home"; // Points to: templates/customer/admin-home.html
    }
}
