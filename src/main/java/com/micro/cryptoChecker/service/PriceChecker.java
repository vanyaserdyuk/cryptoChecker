package com.micro.cryptoChecker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micro.cryptoChecker.dto.PriceConversionDto;
import com.micro.cryptoChecker.model.PriceCheckBackgroundJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class PriceChecker {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private EmailSendingService emailSendingService;

    @Autowired
    private BackgroundJobService backgroundJobService;

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

    @PostConstruct
    public void init(){
        threadPoolTaskScheduler.setDaemon(true);
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.initialize();
    }

    public void startCurrencyChecking(int price) {
        PriceCheckBackgroundJob priceCheckbackgroundJob = PriceCheckBackgroundJob.builder().
                targetPrice(price).currency("Bitcoin").build();

        threadPoolTaskScheduler.execute(() -> {

           boolean isNeccessaryPriceAimed = false;

           while (!isNeccessaryPriceAimed) {
               try {
                   PriceConversionDto priceConversionDto = currencyService.getCurrencies();
                   if (Double.parseDouble(priceConversionDto.getData().getQuote().getUsd().getPrice()) < price) {
                       emailSendingService.sendSimpleMessage("vanyaserdyuk1@gmail.com",
                               String.format("It costs %d dollars", price), "Congratulations!");
                       isNeccessaryPriceAimed = true;
                       log.info("Job completed!");
                   }

                   log.info("Job is still processing");
                   Thread.sleep(3000);
               } catch (JsonProcessingException | InterruptedException e) {
                   log.error("Something went wrong while getting coin price");
               }
           }
        });

        backgroundJobService.save(priceCheckbackgroundJob);
    }
}
