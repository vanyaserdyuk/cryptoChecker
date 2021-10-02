package com.micro.cryptoChecker.service;

import com.micro.cryptoChecker.dao.PriceCheckBackgroundJobDao;
import com.micro.cryptoChecker.model.PriceCheckBackgroundJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackgroundJobService {

    @Autowired
    private PriceCheckBackgroundJobDao priceCheckBackgroundJobDao;

    public void save(PriceCheckBackgroundJob priceCheckBackgroundJob) {
        priceCheckBackgroundJobDao.save(priceCheckBackgroundJob);
    }

    public List<PriceCheckBackgroundJob> getAllJobs() {
        return priceCheckBackgroundJobDao.findAll();
    }

    //TODO
    //write method for cleaning up old jobs
}
