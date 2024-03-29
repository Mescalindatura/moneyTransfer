package com.stb.money_transfer_app.utils;

import com.stb.money_transfer_app.model.Recipient;
import com.stb.money_transfer_app.model.Sender;
import com.stb.money_transfer_app.repo.RecipientsRepo;
import com.stb.money_transfer_app.repo.SendersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultDataLoader implements ApplicationRunner {
//puts default sender and recipient to the db, so we can use the app on the prod, in real-life this part should not exist
    final RecipientsRepo recipients;
    final SendersRepo senders;

    @Autowired
    public DefaultDataLoader(RecipientsRepo recipients, SendersRepo senders) {
        this.recipients = recipients;
        this.senders = senders;
    }

    @Override
    public void run(ApplicationArguments args) {
        Sender sender1 = new Sender();
        senders.save(sender1);

        Recipient recipient1 = new Recipient();
        recipients.save(recipient1);

        List<Sender> s = senders.findAll();
        List<Recipient> r = recipients.findAll();

        System.out.printf("Default users added to the database. Sender id: %d, recipient id: %d", s.get(0).getId(), r.get(0).getId());
    }
}

