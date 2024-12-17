package com.example.hotels.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotels.Model.Room;
import com.example.hotels.Service.BookingService;

@Controller
public class BookingControllerMet {

     private final BookingService bookingService;

    public BookingControllerMet(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    




@GetMapping("/book")
public String book(){
    return "booking";
}

}
