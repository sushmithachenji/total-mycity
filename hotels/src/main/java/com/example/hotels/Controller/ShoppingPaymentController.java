// import com.razorpay.Order;
// import com.razorpay.RazorpayClient;
// import com.razorpay.Utils;
// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// @Controller
// public class PaymentController {

//     // @Value("${razorpay.key_id}")
//     // private String razorpayKeyId;

//     // @Value("${razorpay.key_secret}")
//     // private String razorpayKeySecret;

//     private final RazorpayClient razorpayClient;

//     @Autowired
//     public CheckoutController() throws Exception {
//         this.razorpayClient = new RazorpayClient("rzp_test_hr66RzeUcyoNO7", "CDgUnHIJQKNwEphU1Narg07n");
//     }

//     // Endpoint to create an order
//     @PostMapping("/create-order")
//     public ResponseEntity<String> createOrder(@RequestParam("amount") double amount) {
//         try {
//             // Create Razorpay client instance
//             RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

//             // Create order request
//             JSONObject orderRequest = new JSONObject();
//             orderRequest.put("amount", (int) (amount * 100)); // Convert to paise (1 INR = 100 paise)
//             orderRequest.put("currency", "INR");
//             orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

//             // Create order
//             Order order = razorpayClient.orders.create(orderRequest);

//             // Prepare the response
//             JSONObject response = new JSONObject();
//             response.put("id", order.get("id").toString());
//             response.put("amount", order.get("amount").toString());
//             response.put("currency", order.get("currency").toString());

//             return ResponseEntity.ok(response.toString());
//         } catch (Exception e) {
//             e.printStackTrace();
//             return ResponseEntity.status(500).body("Error creating Razorpay order");
//         }
//     }

//     // Endpoint to process payment after user pays
//     @PostMapping("/process-payment")
//     public ResponseEntity<String> processPayment(
//             @RequestParam("razorpay_order_id") String razorpayOrderId,
//             @RequestParam("razorpay_payment_id") String razorpayPaymentId,
//             @RequestParam("razorpay_signature") String razorpaySignature) {
//         try {
//             // Validate the payment signature
//             boolean isSignatureValid = Utils.verifyPaymentSignature(
//                     new JSONObject()
//                             .put("razorpay_order_id", razorpayOrderId)
//                             .put("razorpay_payment_id", razorpayPaymentId)
//                             .put("razorpay_signature", razorpaySignature),
//                     razorpayKeySecret
//             );

//             if (isSignatureValid) {
//                 // Payment was successful, update your order status in DB, etc.
//                 return ResponseEntity.ok("Payment successful");
//             } else {
//                 return ResponseEntity.status(400).body("Invalid payment signature");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             return ResponseEntity.status(500).body("Error processing payment");
//         }
//     }
// }
package com.example.hotels.Controller;


import com.example.hotels.Model.Product;
import com.example.hotels.Repository.ProductRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/payment")
public class ShoppingPaymentController {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    // @PostMapping("/create-order")
    // public ResponseEntity<Map<String, String>> createOrder(@RequestParam int amount) {
    //     try {
    //         RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

    //         // Convert the amount to paise (1 INR = 100 paise)
    //         int amountInPaise = amount * 100;

    //         // Prepare the order request data
    //         JSONObject orderRequest = new JSONObject();
    //         orderRequest.put("amount", amountInPaise); // Amount in paise
    //         orderRequest.put("currency", "INR");
    //         orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

    //         // Create the order in Razorpay
    //         Order order = razorpayClient.orders.create(orderRequest);

    //         // Return the order details
    //         Map<String, String> response = new HashMap<>();
    //         response.put("id", order.get("id"));
    //         response.put("currency", order.get("currency"));
    //         response.put("amount", order.get("amount").toString());

    //         return ResponseEntity.ok(response);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         Map<String, String> error = new HashMap<>();
    //         error.put("message", "Failed to create order. Please try again.");
    //         return ResponseEntity.status(500).body(error);
    //     }
    // }

    @Autowired
    private ProductRepository productRepository; // Inject ProductRepository

    @PostMapping("/create-order/{productId}")
    public ResponseEntity<?> createOrder(@PathVariable Long productId) {
        try {
            // Fetch product by ID
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
            
            // Calculate amount in paise (Razorpay requires amount in paise)
            int amount = (int) (product.getPrice() * 100);

            // Razorpay client initialization
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

            // Create Razorpay order
            JSONObject options = new JSONObject();
            options.put("amount", amount); // Amount in paise
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");
            Order order = razorpayClient.orders.create(options);

            // Return order details as response
            return ResponseEntity.ok(order.toJson());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/payment-callback")
    public ResponseEntity<String> paymentCallback(@RequestParam String razorpay_payment_id,
            @RequestParam String razorpay_order_id,
            @RequestParam String razorpay_signature) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("razorpay_order_id", razorpay_order_id);
            payload.put("razorpay_payment_id", razorpay_payment_id);
            payload.put("razorpay_signature", razorpay_signature);

            // Verify the signature using Razorpay Utils
            Utils.verifyPaymentSignature(payload, razorpaySecret);

            // If no exception is thrown, the signature is valid
            return ResponseEntity.ok("Payment success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal error");
        }
    }
}