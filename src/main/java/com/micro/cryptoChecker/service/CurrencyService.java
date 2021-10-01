package com.micro.cryptoChecker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.cryptoChecker.dto.PriceConversionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CurrencyService {

    @Value("${coin.apiKey}")
    private String apiKey;

    final String uri = "https://pro-api.coinmarketcap.com/v1/tools/price-conversion";

    public PriceConversionDto getCurrencies() throws JsonProcessingException {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-CMC_PRO_API_KEY", apiKey);
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("symbol","BTC")
                .queryParam("amount", 1);

        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(responseEntity.getBody(), PriceConversionDto.class);
    }
}
