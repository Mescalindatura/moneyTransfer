package com.stb.money_transfer_app.dto;

public record TransferData(long senderID, long recipientID, double amount) {
}
