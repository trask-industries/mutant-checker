package com.traskindustries.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(name="IX_result", columnList = "result"),
                  @Index(name="UK_hash", columnList = "hash", unique = true)})
public class CheckedDNA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    private boolean result;

    @Column(length = 40)
    private String hash;

    @Lob
    private String dna;

    public static class Builder {
        private static final String HASH_ALG = "SHA-1";
        private static final Logger LOG  = Logger.getLogger(CheckedDNA.Builder.class);
        private boolean result;
        private String dna;
        private String hash;

        public Builder result(boolean result) {
            this.result = result;
            return this;
        }
        public Builder dna(String dna) {
            this.dna = dna;
            return this;
        }
        public CheckedDNA build() {
            final String hash =
                    calculateDNAHash();
            return new CheckedDNA(
                    null,
                    new Date(),
                    result,
                    hash,
                    dna);
        }
        private String calculateDNAHash() {
            MessageDigest messageDigest = null;
            try {
                messageDigest =
                    MessageDigest
                    .getInstance(HASH_ALG);
            } catch (NoSuchAlgorithmException e) {
                LOG.error(e.getMessage(), e);
                return "";
            }
            messageDigest
            .update(
                    dna
                    .getBytes());
            return printHexBinary(messageDigest.digest());

                    /*String(
                    messageDigest
                    .digest());
                    */
        }
    }
}
