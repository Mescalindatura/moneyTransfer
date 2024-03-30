package com.stb.money_transfer_app;

import com.stb.money_transfer_app.dto.TransferData;
import com.stb.money_transfer_app.exceptions.NotEnoughMoneyException;
import com.stb.money_transfer_app.exceptions.UserNotFoundException;
import com.stb.money_transfer_app.model.*;
import com.stb.money_transfer_app.repo.*;
import com.stb.money_transfer_app.service.MoneyTransfer;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MoneyTransferTest {
    @Mock
    private RecipientsRepo recipientsRepo;

    @Mock
    private SendersRepo sendersRepo;

    @Mock
    private OperationsRepo operationsRepo;

    @InjectMocks
    private MoneyTransfer moneyTransfer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetBalance_UserExists() {
        long userId = 1L;
        Recipient recipient = new Recipient();
        recipient.setBalance(1000.0);
        when(recipientsRepo.findById(userId)).thenReturn(Optional.of(recipient));

        double balance = moneyTransfer.getBalance(userId);

        assertEquals(1000.0, balance);
    }

    @Test
    void testGetBalance_UserNotExists() {
        long userId = 1L;
        when(recipientsRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> moneyTransfer.getBalance(userId));
    }

    @Test
    void testSendMoney_ValidAmount() {
        TransferData transferData = new TransferData(1L, 2L, 500.0);
        Sender sender = new Sender();
        Recipient recipient = new Recipient();
        recipient.setBalance(1000.0);
        when(sendersRepo.findById(transferData.senderID())).thenReturn(Optional.of(sender));
        when(recipientsRepo.findById(transferData.recipientID())).thenReturn(Optional.of(recipient));

        long result = moneyTransfer.sendMoney(transferData);

        assertEquals(result, 1);
        assertEquals(1500.0, recipient.getBalance());
    }

    @Test
    void testSendMoney_InvalidAmount() {
        TransferData transferData = new TransferData(1L, 2L, 1500.0);

        assertThrows(NotEnoughMoneyException.class, () -> moneyTransfer.sendMoney(transferData));
        verifyNoInteractions(sendersRepo, recipientsRepo, operationsRepo);
    }

    @Test
    void testWithdraw_UserExistsAndHasEnoughBalance() {
        long userId = 1L;
        double amount = 200.0;
        Recipient recipient = new Recipient();
        recipient.setBalance(500.0);
        when(recipientsRepo.findById(userId)).thenReturn(Optional.of(recipient));

        double balance = moneyTransfer.withdraw(userId, amount);

        assertEquals(300.0, balance);
        assertEquals(300.0, recipient.getBalance());
        verify(recipientsRepo, times(1)).save(recipient);
    }

    @Test
    void testWithdraw_UserNotExists() {
        long userId = 1L;
        when(recipientsRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> moneyTransfer.withdraw(userId, 200.0));
        verifyNoInteractions(operationsRepo);
    }

    @Test
    void testWithdraw_NotEnoughBalance() {
        long userId = 1L;
        Recipient recipient = new Recipient();
        recipient.setBalance(100.0);
        when(recipientsRepo.findById(userId)).thenReturn(Optional.of(recipient));

        assertThrows(NotEnoughMoneyException.class, () -> moneyTransfer.withdraw(userId, 200.0));
        verifyNoInteractions(operationsRepo);
    }
}
