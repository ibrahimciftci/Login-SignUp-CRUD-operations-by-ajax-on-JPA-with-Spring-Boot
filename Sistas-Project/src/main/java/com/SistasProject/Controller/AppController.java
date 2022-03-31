package com.SistasProject.Controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SistasProject.Entities.Product;
import com.SistasProject.Entities.User;
import com.SistasProject.Repositories.ProductRepository;
import com.SistasProject.Repositories.UserRepository;

@Controller
public class AppController {
	

	
	@Autowired
	private Product product;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository productRepo;
	

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }
    
    @GetMapping("/products")
    public String product() {
    	return "products";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
    	model.addAttribute("user", new User());
    	return "signup_form";
    }
    
    @PostMapping("/process_register")
    public String processRegistration(User user) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String encodedPassword = encoder.encode(user.getPassword());
    	user.setPassword(encodedPassword);
    	userRepo.save(user);
    	
    	return "register_success";
    }
    
   
    @GetMapping(value = "/list_products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> viewList() {
    	List<Product> listProducts = productRepo.findAll();
    	return listProducts;
    	
    }
    
    
    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product showProductForm(@PathVariable Long productId) {
      return productRepo.findByProductId(productId);
    }
    
    
    @PostMapping(value = "/product/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public String update(@RequestBody Product product) {
    	Product entityProduct=productRepo.findByProductId(product.getProductId());
    	
        
    	if(entityProduct==null)
    		return "{\"msg\":\"Ürün Bulunamadı\"}";
    	else {
    		String lastPName=entityProduct.getProductName();
    		productRepo.save(product);
    		String message=lastPName+" adlı ürün başarıyla güncellendi";
    		return "{\"msg\":\""+message+"\"}";
    	}
	}
    
    @PostMapping(value = "/product/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public String create(@RequestBody Product product) {
       productRepo.save(product);
       String message=product.getProductName()+" adlı ürün başarıyla eklendi";
       return "{\"msg\":\""+message+"\"}";
	}

    @GetMapping(value = "/product/delete/{id}")
    @ResponseBody
	public String delete(@PathVariable Long id) {
    	
    	Product product=productRepo.findByProductId(id);
    	if(product==null)
    		return "Ürün Bulunamadı";
    	else
    	{
    		productRepo.deleteById(id);
    		return product.getProductName()+" adlı ürün başarıyla silindi";
    	}
	}
}
