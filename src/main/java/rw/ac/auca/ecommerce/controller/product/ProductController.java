package rw.ac.auca.ecommerce.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.ecommerce.core.product.model.Product;
import rw.ac.auca.ecommerce.core.product.service.IProductService;
import rw.ac.auca.ecommerce.core.util.product.EStockState;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The class ProductController.
 *
 * @author Jeremie
 * @version 1.0
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/product/")
public class ProductController {

    private final IProductService productService;

    // 🔍 1. List all active products
    @GetMapping("/search/all")
    public String getAllProducts(Model model){
        List<Product> products = productService.findProductsByState(true);
        model.addAttribute("products", products);
        return "product/productList";
    }

    // 📝 2. Show the registration form
    @GetMapping("/register")
    public String getProductRegistrationPage(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("stockStates", EStockState.values()); // to populate dropdown
        return "product/productRegistrationPage";
    }

    // ✅ 3. Handle form submission
    @PostMapping("/register")
    public String registerProduct(@ModelAttribute("product") Product theProduct, Model model){
        if (Objects.nonNull(theProduct)) {
            productService.createProduct(theProduct);
            model.addAttribute("message", "Product Saved Successfully");
        } else {
            model.addAttribute("error", "Failed to Save Product");
        }
        model.addAttribute("product", new Product()); // clear form
        model.addAttribute("stockStates", EStockState.values());
        return "product/productRegistrationPage";
    }

    // 👁️ 4. View product by ID (optional)
    @GetMapping("/view/{id}")
    public String viewProduct(@PathVariable("id") UUID id, Model model) {
        Product product = productService.findProductByIdAndState(id, true);
        model.addAttribute("product", product);
        return "product/productDetails";
    }
}
