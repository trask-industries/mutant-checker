package com.traskindustries.service;

import java.io.IOException;
import java.util.Objects;

import com.traskindustries.configuration.RabbitMQConfig;
import com.traskindustries.genetics.mutants.XGenIdentificationAccumulator;
import com.traskindustries.genetics.DNAStream;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traskindustries.exception.DNAIncoherenceException;

@Service
public class MutantService {

	private static final Logger LOG  = Logger.getLogger(MutantService.class);
	private static final String TOPIC = "/topic/notify";
	private static final String NOTIFICATION_TEMPLATE = "{\"dna\":\"$DNA$\",\"result\":\"$RESULT$\"}";
	private static final String DNA_FIELD = "$DNA$";
	private static final String RESULT_FIELD = "$RESULT$";
	private static final String GEN_DELIMITER = "|";

	@Autowired
	private RabbitTemplate rabbitTemplate;



	public boolean isMutant(String[] dna) {
		Objects.requireNonNull(dna);
		if (dna.length == 0
			|| dna.length != dna[0].length()) {
			throw new DNAIncoherenceException();
		}
		boolean result = DNAStream
				.fromStringArray(dna)
				.containsGen(
						new XGenIdentificationAccumulator());
		StoreAsync(dna, result);
		return result;
	}

	private void StoreAsync(final String[] dna,
							final Boolean result) {
		if(rabbitTemplate != null) {
			try {
				final String dnaString =
						String
						.join(
							GEN_DELIMITER,
							dna);
				rabbitTemplate
					.convertAndSend(
						RabbitMQConfig.TOPIC_EXCHANGE_NAME,
						RabbitMQConfig.ROUTING_KEY,
						NOTIFICATION_TEMPLATE
							.replace(DNA_FIELD,
									dnaString)
							.replace(RESULT_FIELD,
									result.toString()));
			} catch (Exception ex) {
				LOG.error(ex.getMessage(), ex);
			}
		}
	}
}
