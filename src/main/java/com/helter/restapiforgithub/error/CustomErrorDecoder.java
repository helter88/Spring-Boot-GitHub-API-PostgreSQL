package com.helter.restapiforgithub.error;

import org.springframework.stereotype.Component;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
      @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new RuntimeException("Resource not found");
        }
        return FeignException.errorStatus(methodKey, response);
    }
}