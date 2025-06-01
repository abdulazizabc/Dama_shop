package com.example.dama_shop.model.enums;

public enum OrderStatus {
    PENDING,        // Ожидает обработки
    PROCESSING,     // В обработке
    SHIPPED,        // Отправлен
    DELIVERED,      // Доставлен
    CANCELED,       // Отменён
    RETURNED        // Возвращён
}

