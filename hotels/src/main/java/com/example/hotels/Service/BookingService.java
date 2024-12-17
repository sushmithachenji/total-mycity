package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.Booking;
import com.example.hotels.Model.Room;
import com.example.hotels.Repository.BookingRepository;
import com.example.hotels.Repository.RoomRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


   private final RoomRepository roomRepository;


    public BookingService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAvailableRooms(int personCount, String checkInDate, String checkOutDate) {
        return roomRepository.findAvailableRooms(personCount, checkInDate, checkOutDate);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}

