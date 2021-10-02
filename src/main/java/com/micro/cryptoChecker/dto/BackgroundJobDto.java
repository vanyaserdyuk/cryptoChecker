package com.micro.cryptoChecker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BackgroundJobDto {
    private String currency;
    private int targetPrice;
}
