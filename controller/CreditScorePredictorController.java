package com.fintech.creditscoring.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fintech.creditscoring.dto.CreditScoreRequest;
import com.fintech.creditscoring.dto.CreditScoreResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@RestController
@RequestMapping("/api/credit")
public class CreditScorePredictorController {

    @PostMapping(value = "/predict", consumes = {"multipart/form-data"})
    public CreditScoreResponse predictCreditScore(
            @RequestPart(value = "json", required = true) String jsonRequest,
            @RequestPart(value = "upi_pdf", required = true) MultipartFile upiPdf) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        CreditScoreRequest request = mapper.readValue(jsonRequest, CreditScoreRequest.class);
        String jsonPayload = mapper.writeValueAsString(request);

        // Convert UPI PDF to byte array
        byte[] pdfBytes = upiPdf.getBytes();

        // Send request to Python API (simplified example)
        String pythonApiUrl = "http://localhost:5000/predict";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("json", jsonPayload);
        map.add("upi_pdf", new ByteArrayResource(pdfBytes) {
            @Override
            public String getFilename() {
                return upiPdf.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        try {
            ResponseEntity<CreditScoreResponse> response = new RestTemplate()
                    .postForEntity(pythonApiUrl, requestEntity, CreditScoreResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Python API returned error: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new RuntimeException("Error calling Python API: " + ex.getResponseBodyAsString(), ex);
        }

    }
}