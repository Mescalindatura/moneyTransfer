package com.stb.money_transfer_app;

import com.stb.money_transfer_app.controllers.MoneyTransferCtrl;
import com.stb.money_transfer_app.dto.TransferData;
import com.stb.money_transfer_app.service.MoneyTransfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MoneyTransferCtrlTest {

    @Mock
    MoneyTransfer moneyTransferService;

    @InjectMocks
    MoneyTransferCtrl moneyTransferCtrl;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(moneyTransferCtrl).build();
    }

    @Test
    void testGetBalance() throws Exception {
        long userId = 1L;
        when(moneyTransferService.getBalance(userId)).thenReturn(1000.0);

        mockMvc.perform(get("/balance/{id}", userId))
                .andExpect(status().isOk());
    }

    @Test
    void testSendMoney() throws Exception {
        TransferData transferData = new TransferData(1L, 2L, 500.0);
        when(moneyTransferService.sendMoney(any())).thenReturn(true);

        mockMvc.perform(post("/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senderID\":1,\"recipientID\":2,\"amount\":500.0}"))
                .andExpect(status().isOk());
    }

    @Test
    void testWithdraw() throws Exception {
        long userId = 1L;
        double amount = 200.0;
        when(moneyTransferService.withdraw(userId, amount)).thenReturn(800.0);

        mockMvc.perform(post("/withdraw/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200.0"))
                .andExpect(status().isOk());
    }
}
