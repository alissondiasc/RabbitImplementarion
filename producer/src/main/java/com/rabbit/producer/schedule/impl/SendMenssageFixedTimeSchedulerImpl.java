package com.rabbit.producer.schedule.impl;

import com.rabbit.producer.commons.scheduller.FixedTimeScheduler;
import com.rabbit.producer.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendMenssageFixedTimeSchedulerImpl implements FixedTimeScheduler {

    @Value("${scheduler.send-message.isEnabled}")
    private Boolean isEnabled;

    @Autowired
    @Qualifier("sendMenssageServiceImpl")
    private BatchService batchService;

    @Override
    @Scheduled(fixedDelayString = "${scheduler.send-message.interval.ms}")
    public void execute() {
        if (isEnabled) {
            batchService.process();
        }
    }
}
