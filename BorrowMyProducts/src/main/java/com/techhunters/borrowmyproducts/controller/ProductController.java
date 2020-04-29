package com.techhunters.borrowmyproducts.controller;

import com.techhunters.borrowmyproducts.dto.CategoryDTO;
import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.dto.ProductLocationDTO;
import com.techhunters.borrowmyproducts.dto.ProductRequestDTO;
import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.entity.*;
import com.techhunters.borrowmyproducts.objectmappers.CategoryMapper;
import com.techhunters.borrowmyproducts.objectmappers.UserMapper;
import com.techhunters.borrowmyproducts.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRequestService productRequestService;

    @Autowired
    private TwilioMessageApi twilioCustomMessageApi;

    @RequestMapping(value = "/myProducts", method = RequestMethod.GET)
    public String displayMyProducts(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByEmail(principal.getName());
        ProductLocationDTO productLocationDTO = new ProductLocationDTO();
        ProductDTO product = new ProductDTO();
        CategoryDTO category = new CategoryDTO();
        model.addAttribute("product", product);
        model.addAttribute("location", productLocationDTO);
        model.addAttribute("category", category);
        model.addAttribute("products", productService.listByUserId(user.getUserId()));
        return "myProducts";
    }

    @PostMapping("/addProduct")
    public String addMyProduct(HttpServletRequest request, @ModelAttribute(name = "product") @Validated ProductDTO productDTO, @ModelAttribute(name = "location") @Validated ProductLocationDTO productLocationDTO, @ModelAttribute(name = "category") CategoryDTO cat, Model model, BindingResult result) throws IOException {
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByEmail(principal.getName());
        if (result.hasErrors()) {
            return "myProducts";
        }
        if (productDTO != null && productLocationDTO != null) {
            CategoryDTO category = categoryService.findByCategoryName(cat.getCategoryName());
            log.info(user.getEmail());
            log.info(category.getCategoryName());
            log.info("" + productLocationDTO.getLatitude());
            productDTO.setProductLocation(productLocationDTO);
            productDTO.setProductStatus("available");
            productDTO.setImage("/images/" + productDTO.getImage());
            productLocationDTO.setProduct(productDTO);
            productDTO.setUser(user);
            productDTO.setCategory(category);
            productService.save(productDTO);
        }
        return "redirect:/myProducts";
    }

    @GetMapping("/category/{id}")
    public String electronicProductcs(Model model, HttpServletRequest request, @PathVariable("id") String id) {
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByEmail(principal.getName());
        List<ProductDTO> products = productService.listAvailableProductsByCategory(id, user.getUserId());
        model.addAttribute("products", products);
        return "categoryProducts";
    }

    @GetMapping("/requested/{category}/{id}")
    public String updateRequest(Model model, @PathVariable("id") int id, @PathVariable("category") String category, HttpServletRequest request) {
//        log.info(product);
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByEmail(principal.getName());
        ProductDTO product1 = productService.findById(id);
        UserDTO owner = userService.findById(product1.getUser().getUserId());
        String msg = "Your product " + product1.getProductName() + " has been requested";
        if (twilioCustomMessageApi.notifyUser(msg, owner.getPhone())) {
            log.info("owner has been notified successfully");
        } else {
            log.info("error");
        }
        ProductRequestDTO productRequest = new ProductRequestDTO();
        productRequest.setProduct(product1);
        productRequest.setUser(user);
        productRequest.setStatus("pending");
        productRequestService.save(productRequest);
        log.info("" + category);
        return "redirect:/category/{category}";
    }

    @GetMapping("/myRequests")
    public String seeRequests(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        model.addAttribute("productRequests", productRequestService.listByUser(principal.getName()));
        return "myRequests";
    }

    @GetMapping("/seeRequests/{id}")
    public String myProductsRequests(@PathVariable("id") int id, Model model) {
        List<ProductRequestDTO> requests = productRequestService.listByProductPending(id);
        log.info("" + requests.size());
        model.addAttribute("productRequests", requests);
        return "seeRequests";
    }

    @GetMapping("/accepted/{proId}/{userId}")
    public String accepted(@PathVariable("proId") int proId, @PathVariable("userId") int userId, Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        UserDTO owner = userService.findByEmail(principal.getName());
        ProductDTO product1 = productService.findById(proId);
        product1.setProductStatus("in use");
        productService.save(product1);
        List<ProductRequestDTO> requests = productRequestService.listByProduct(proId);
        for (ProductRequestDTO req : requests) {
            if (req.getUser().getUserId() == userId) {
                req.setStatus("accepted");
            } else {
                req.setStatus("rejected");
            }
            productRequestService.save(req);
            if (req.getUser().getUserId() != userId) {
                log.info("" + req.getUser().getPhone());
                String msg1 = "Your product request for the product " + product1.getProductName() + " has been rejected";
                if (twilioCustomMessageApi.notifyUser(msg1, req.getUser().getPhone())) {
                    log.info("owner has been notified successfully");
                } else {
                    log.info("error");
                }
                productRequestService.delete(req);
            }
        }
        UserDTO user = userService.findById(userId);
        String msg = "Your product request for the product " + product1.getProductName() + " has been accepted" + " your owner mobile number " + owner.getPhone();
        if (twilioCustomMessageApi.notifyUser(msg, user.getPhone())) {
            log.info("user has been notified successfully");
        } else {
            log.info("error");
        }
        model.addAttribute("categories", categoryService.findAll());
        return "accepted";
    }

    @GetMapping("/reject/{proId}/{userId}")
    public String reject(@PathVariable("proId") int proId, @PathVariable("userId") int userId, Model model) {
        List<ProductRequestDTO> requests = productRequestService.listByProduct(proId);
        for (ProductRequestDTO req : requests) {
            if (req.getUser().getUserId() == userId) {
                productRequestService.delete(req);
            }
        }
        return "redirect:/seeRequests/{proId}";
    }
}