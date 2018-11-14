package com.traskindustries.controller;

import com.traskindustries.dto.VerifyIsMutantRequest;
import com.traskindustries.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/mutant",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity<?> verifyIsMutant(@Valid @RequestBody VerifyIsMutantRequest request) {
        return new ResponseEntity<>(
                mutantService
                .isMutant(request.getDna()) ? HttpStatus.OK : HttpStatus.FORBIDDEN);
    }
}
