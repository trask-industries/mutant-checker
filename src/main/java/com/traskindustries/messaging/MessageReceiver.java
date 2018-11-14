package com.traskindustries.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traskindustries.messages.PersistDNACheckMessage;
import com.traskindustries.model.CheckedDNA;
import com.traskindustries.repository.CheckedDNARepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger LOG  = Logger.getLogger(MessageReceiver.class);

    @Autowired
    CheckedDNARepository checkedDNARepository;

    @Autowired
    ObjectMapper mapper;

    public void receiveMessage(String message) {
        LOG.info("<<<< Received msg: <" + message + ">");
        try {
            final PersistDNACheckMessage persistDNACheckMessage =
                    mapper
                            .readValue(message, PersistDNACheckMessage.class);
            final CheckedDNA checkedDNAToSave =
                    new CheckedDNA
                            .Builder()
                            .dna(persistDNACheckMessage.getDna())
                            .result(persistDNACheckMessage.isResult())
                            .build();
            checkedDNARepository
                    .save(checkedDNAToSave);
        } catch (DataIntegrityViolationException iex) {
            LOG.warn("DNA already exists!");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}
