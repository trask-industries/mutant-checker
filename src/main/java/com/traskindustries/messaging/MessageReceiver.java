package com.traskindustries.messaging;

import com.traskindustries.model.CheckedDNA;
import com.traskindustries.repository.CheckedDNARepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger LOG  = Logger.getLogger(MessageReceiver.class);

    @Autowired
    CheckedDNARepository checkedDNARepository;

    public void receiveMessage(String message) {
        System.out.println("<<<< Received <" + message + ">");
        try {
            final CheckedDNA checkedDNAToSave =
                    new CheckedDNA
                            .Builder()
                            .dna("aa")
                            .result(true)
                            .build();
            checkedDNARepository
                    .save(checkedDNAToSave);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}
