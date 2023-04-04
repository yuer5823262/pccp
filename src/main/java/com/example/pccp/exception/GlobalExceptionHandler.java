package com.example.pccp.exception;

import com.example.pccp.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public  Object handleException(Exception e){
//        log.error("DamPourException: ", e);
//        return ApiRestResponse.error(DampouringExceptionEnum.SYSTEM_ERROR);
//    }

    @ExceptionHandler(PccpException.class)
    @ResponseBody
    public  Object handleDamPourException(PccpException e){
        log.error("PccpException: ", e);
        return ApiRestResponse.error(e.getCode(),e.getMessage());
    }
    //    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    @ResponseBody
//    public  Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
//        log.error("HttpRequestMethodNotSupportedException: ", e);
//        return ApiRestResponse.error(DampouringExceptionEnum.REQUEST_METHOD_ERROR);
//    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public  Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException: ", e);
        return handleBindingResult(e.getBindingResult());
    }
    private ApiRestResponse handleBindingResult(BindingResult result){
        List<String> list = new ArrayList<>();
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for (int i = 0; i < allErrors.size(); i++) {
                ObjectError objectError = allErrors.get(i);
                String message = objectError.getDefaultMessage();
                list.add(message);
            }

        }
        if(list.size() == 0){
            return  ApiRestResponse.error(PccpExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return ApiRestResponse.error(PccpExceptionEnum.REQUEST_PARAM_ERROR.getCode(),list.toString());
    }
}