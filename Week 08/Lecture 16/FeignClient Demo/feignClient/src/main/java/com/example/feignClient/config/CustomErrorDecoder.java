package com.example.feignClient.config;

import com.example.feignClient.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new NotFoundException("Not found.");
            default -> new Exception("Generic error.");
        };
    }

}
