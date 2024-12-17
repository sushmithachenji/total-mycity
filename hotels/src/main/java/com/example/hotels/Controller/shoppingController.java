package com.example.hotels.Controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.hotels.Model.Product;
import com.example.hotels.Model.Shopping_Images;
import com.example.hotels.Repository.ProductRepository;
import com.example.hotels.Service.Shopping_ImageService;

@Controller
public class shoppingController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Shopping_ImageService shopping_ImageService;

    @GetMapping("/homePage")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/products/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        // Fetch artist by ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        Long categoryId = 1L;
        List<Product> similarProducts = productRepository.findByCategoryId(categoryId); // Example: finding top 5
                                                                                        // products in the same category

        // List<Shopping_Images> thumbnails = shopping_ImageService.getImageById(id);
        // Add both the current product and similar products to the model
        model.addAttribute("product", product);
        // model.addAttribute("thumbnail", thumbnails);
        model.addAttribute("products", similarProducts);
        return "uspolo";
    }

    @GetMapping("/products/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));

        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg") // or "image/png" based on your image type
                .body(product.getImageFileName());
    }

    @GetMapping("/image/{imageId}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable Long imageId) {
        Shopping_Images image = shopping_ImageService.getImageById(imageId);

        byte[] imageData = image.getImages(); // Assume this is a byte array from DB

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Set content type based on the image format
                .body(imageData);
    }

    @GetMapping("/shoppingCategory")
    public String categoriesList(Model model) {
        Long tshirtCategoryId = 11L;
        Long shirtCategoryId = 1L;
        Long womensChudidharsCategoryId = 17L;
        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.findByCategoryId(tshirtCategoryId));
        products.addAll(productRepository.findByCategoryId(shirtCategoryId));
        products.addAll(productRepository.findByCategoryId(womensChudidharsCategoryId));

        model.addAttribute("products", products);

        return "shopping_category";
    }

    @GetMapping("/tshirts")
    public String tshirtDisplay(Model model) {
        Long tshirtCategoryId = 11L;
        model.addAttribute("products", productRepository.findByCategoryId(tshirtCategoryId));
        return "shirts";
    }

    @GetMapping("/shirts")
    public String shirtsDisplay(Model model) {
        Long shirtCategoryId = 1L;
        model.addAttribute("products", productRepository.findByCategoryId(shirtCategoryId));
        return "shirts";
    }

    @GetMapping("/mens-jeans")
    public String mensJeans(Model model) {
        Long mensJeansCategoryId = 28L;
        model.addAttribute("products", productRepository.findByCategoryId(mensJeansCategoryId));
        return "shirts";
    }

    @GetMapping("/mens-track-pants")
    public String mensTrackPants(Model model) {
        Long mensTrackPantsCategoryId = 29L;
        model.addAttribute("products", productRepository.findByCategoryId(mensTrackPantsCategoryId));
        return "shirts";
    }

    @GetMapping("/womens-chudidhars")
    public String womensChudidhars(Model model) {
        Long womensChudidharsCategoryId = 14L;
        model.addAttribute("products", productRepository.findByCategoryId(womensChudidharsCategoryId));
        return "shirts";
    }

    @GetMapping("/womens-sarees")
    public String womensSarees(Model model) {
        Long womensSareesCategoryId = 2L;
        model.addAttribute("products", productRepository.findByCategoryId(womensSareesCategoryId));
        return "shirts";
    }

    @GetMapping("/womens-jeans")
    public String womensJeans(Model model) {
        Long womensJeansCategoryId = 13L;
        model.addAttribute("products", productRepository.findByCategoryId(womensJeansCategoryId));
        return "shirts";
    }

    @GetMapping("/womens-tops")
    public String womensTops(Model model) {
        Long womensTopsCategoryId = 12L;
        model.addAttribute("products", productRepository.findByCategoryId(womensTopsCategoryId));
        return "shirts";
    }

    @GetMapping("/doormates")
    public String doorMates(Model model) {
        Long doormatesCatgegoryId = 15L;
        model.addAttribute("products", productRepository.findByCategoryId(doormatesCatgegoryId));
        return "shirts";
    }

    @GetMapping("/homeTheatres")
    public String hometheatres(Model model) {
        Long hometheatresCatgegoryId = 16L;
        model.addAttribute("products", productRepository.findByCategoryId(hometheatresCatgegoryId));
        return "shirts";
    }

    @GetMapping("/rings")
    public String rings(Model model) {
        Long ringsCatgegoryId = 17L;
        model.addAttribute("products", productRepository.findByCategoryId(ringsCatgegoryId));
        return "shirts";
    }

    @GetMapping("/frocks")
    public String frocks(Model model) {
        Long frocksCatgegoryId = 10L;
        model.addAttribute("products", productRepository.findByCategoryId(frocksCatgegoryId));
        return "shirts";
    }

    @GetMapping("/bedsheets")
    public String bedsheets(Model model) {
        Long bedsheetsCatgegoryId = 18L;
        model.addAttribute("products", productRepository.findByCategoryId(bedsheetsCatgegoryId));
        return "shirts";
    }

    @GetMapping("/rompers")
    public String rompers(Model model) {
        Long rompersCatgegoryId = 19L;
        model.addAttribute("products", productRepository.findByCategoryId(rompersCatgegoryId));
        return "shirts";
    }

    @GetMapping("/curtains")
    public String curtains(Model model) {
        Long curtainsCatgegoryId = 20L;
        model.addAttribute("products", productRepository.findByCategoryId(curtainsCatgegoryId));
        return "shirts";
    }

    @GetMapping("/smart-watches")
    public String smartWatches(Model model) {
        Long watchesCatgegoryId = 21L;
        model.addAttribute("products", productRepository.findByCategoryId(watchesCatgegoryId));
        return "shirts";
    }

    @GetMapping("/bangles")
    public String bangles(Model model) {
        Long banglesCatgegoryId = 22L;
        model.addAttribute("products", productRepository.findByCategoryId(banglesCatgegoryId));
        return "shirts";
    }

    @GetMapping("/bluetooth-earphones")
    public String earphones(Model model) {
        Long earphonesCatgegoryId = 23L;
        model.addAttribute("products", productRepository.findByCategoryId(earphonesCatgegoryId));
        return "shirts";
    }

    @GetMapping("/mobiles")
    public String mobiles(Model model) {
        Long mobilesCatgegoryId = 25L;
        model.addAttribute("products", productRepository.findByCategoryId(mobilesCatgegoryId));
        return "shirts";
    }

    @GetMapping("/mens-footwear")
    public String mensFootwear(Model model) {
        Long mensFootwearCatgegoryId = 26L;
        model.addAttribute("products", productRepository.findByCategoryId(mensFootwearCatgegoryId));
        return "shirts";
    }

    @GetMapping("/home-needs")
    public String homeNeeds(Model model) {
        Long homeNeedsCatgegoryId = 27L;
        model.addAttribute("products", productRepository.findByCategoryId(homeNeedsCatgegoryId));
        return "shirts";
    }

    @GetMapping("/apple-products")
    public String appleProducts(Model model) {
        Long appleBrandId = 17L;
        model.addAttribute("products", productRepository.findByBrandId(appleBrandId));
        return "shirts";
    }

    @GetMapping("/zebronics-products")
    public String zebronicsProducts(Model model) {
        Long zebronicsBrandId = 13L;
        model.addAttribute("products", productRepository.findByBrandId(zebronicsBrandId));
        return "shirts";
    }

    @GetMapping("/boat-products")
    public String boatProducts(Model model) {
        Long boatBrandId = 11L;
        model.addAttribute("products", productRepository.findByBrandId(boatBrandId));
        return "shirts";
    }

    @GetMapping("/summary/{productId}")
    public String summaryOfProducts(@PathVariable Long productId, Model model) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
        model.addAttribute("product", product);
        return "summary";
    }

    @GetMapping("/uspayment/{productId}")
    public String saveAndDeliever(@PathVariable Long productId, Model model) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
        model.addAttribute("product", product);
        return "uspayment";
    }

    @GetMapping("/payment/{productId}/details")
public String paymentPage(@PathVariable Long productId,
                          @RequestParam int quantity,
                          Model model) {
    Product product = productRepository.findById(productId).orElse(null);
    
    if (product == null) {
        throw new RuntimeException("Product not found!"); // Optional: Handle product not found
    }

    double totalPrice = product.getPrice() * quantity; // Calculate the total price
    model.addAttribute("product", product);
    model.addAttribute("quantity", quantity);
    model.addAttribute("totalPrice", totalPrice);
    return "payment"; // Thymeleaf template name
}


}
