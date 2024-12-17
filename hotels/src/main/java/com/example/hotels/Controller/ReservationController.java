package com.example.hotels.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hotels.dto.ReservationDTO;

@Controller
@RequestMapping("/reserves")
public class ReservationController {

    @PostMapping
    public String reserveRoom(@ModelAttribute ReservationDTO reservationDTO, Model model) {
        // Process the reservation
        // Add success or error message to the model
        model.addAttribute("reservation", reservationDTO);
        return "new"; // Return a confirmation page
    }
}

