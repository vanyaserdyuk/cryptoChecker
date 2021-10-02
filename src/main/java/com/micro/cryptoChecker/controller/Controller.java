package com.micro.cryptoChecker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micro.cryptoChecker.dto.BackgroundJobDto;
import com.micro.cryptoChecker.dto.PriceConversionDto;
import com.micro.cryptoChecker.model.PriceCheckBackgroundJob;
import com.micro.cryptoChecker.service.BackgroundJobService;
import com.micro.cryptoChecker.service.CurrencyService;
import com.micro.cryptoChecker.service.EmailSendingService;
import com.micro.cryptoChecker.service.PriceChecker;
import com.micro.cryptoChecker.mapper.SimpleJobDtoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class Controller {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private EmailSendingService emailSendingService;

    @Autowired
    private PriceChecker priceChecker;

    @Autowired
    private BackgroundJobService backgroundJobService;

    private final SimpleJobDtoMapper jobDtoMapper = Mappers.getMapper(SimpleJobDtoMapper.class);

    @GetMapping("/currency")
    public PriceConversionDto getCurrentDate() {
        try {
            return currencyService.getCurrencies();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/message")
    public void sendMessage() {
        emailSendingService.sendSimpleMessage("vanyaserdyuk1@gmail.com,", "Test", "Hi!");
    }

    @GetMapping("/watch/{price}")
    public void sendMessage(@PathVariable("price") int price) {
        priceChecker.startCurrencyChecking(price);
    }

    @GetMapping("/histoey")
    public List<BackgroundJobDto> getHistory() {
        List<PriceCheckBackgroundJob> jobs = backgroundJobService.getAllJobs();
        return jobs.stream().map(jobDtoMapper::priceCheckBackGroundJobToDto)
                .collect(Collectors.toList());
    }
}
