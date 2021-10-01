package com.micro.cryptoChecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Quote {
    @JsonProperty("USD")
    private Currency usd;
}
