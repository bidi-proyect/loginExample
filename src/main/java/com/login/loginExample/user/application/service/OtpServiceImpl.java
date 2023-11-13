package com.login.loginExample.user.application.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.login.loginExample.user.domain.service.IOtpService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpServiceImpl implements IOtpService {

    private static final  Integer EXPIRE_MIN = 5;
    private LoadingCache<String, String> otpCache;
    private  static final Logger logger = LoggerFactory.getLogger(IOtpService.class);

    public OtpServiceImpl() {
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return null;
                    }
                });
    }

    @Override
    public String generateCode(String phoneNumber) {
        String otp =  new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
        otpCache.put(phoneNumber, otp);
        return otp;
    }

    @Override
    public String concatNumber(List<Integer> numbers) {
        StringBuilder result = new StringBuilder();
        for (Integer number : numbers) {
            result.append(number);
        }
        return result.toString();
    }

    @Override
    public boolean sendCode(String phoneNumber) {
        Twilio.init("AC01621c2e09e50990f736d5c3a4148a61", "db5779405deded8bd3b0f1b8b5c2f5d8");
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+57" + phoneNumber),
                        new com.twilio.type.PhoneNumber("+13184076550"),
                        "\nbidi: \nDigita este codigo para confirmar tu contrasenia: " + generateCode(phoneNumber))
                .create();

        logger.debug("SID: {}", message.getSid());
        logger.info("Otp send");
        return true;
    }

    public String getCacheOtp(String key){
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return "";
        }
    }
    //clear stored otp
    public void clearOtp(String key){
        otpCache.invalidate(key);
    }
}
