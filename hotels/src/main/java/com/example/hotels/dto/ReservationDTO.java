package com.example.hotels.dto;

public class ReservationDTO {
    private Long roomId;
    private String roomType;
    private Double price;
    private Integer maxGuests;
    private String checkinDate;
    private String checkoutDate;
    private String primaryRoomImageBase64;
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getMaxGuests() {
        return maxGuests;
    }
    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
    }
    public String getCheckinDate() {
        return checkinDate;
    }
    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }
    public String getCheckoutDate() {
        return checkoutDate;
    }
    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    public String getPrimaryRoomImageBase64() {
        return primaryRoomImageBase64;
    }
    public void setPrimaryRoomImageBase64(String primaryRoomImageBase64) {
        this.primaryRoomImageBase64 = primaryRoomImageBase64;
    }

    // Getters and setters
    
}

