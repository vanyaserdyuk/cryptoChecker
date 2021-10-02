package com.micro.cryptoChecker.mapper;

import com.micro.cryptoChecker.dto.BackgroundJobDto;
import com.micro.cryptoChecker.model.PriceCheckBackgroundJob;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleJobDtoMapper {
    PriceCheckBackgroundJob jobDtoToPriceCheckJob(BackgroundJobDto backgroundJobDto);
    BackgroundJobDto priceCheckBackGroundJobToDto(PriceCheckBackgroundJob priceCheckBackgroundJob);
}
