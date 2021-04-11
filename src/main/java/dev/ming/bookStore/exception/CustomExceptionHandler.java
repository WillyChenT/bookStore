package dev.ming.bookStore.exception;

import dev.ming.bookStore.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e){
        
        logger.error("[系統異常]{}",e);

        if(e instanceof BasicException){
            BasicException basicException = (BasicException)e;

            return JsonData.buildError(basicException.getCode(),basicException.getMsg());

        }else{
            return JsonData.buildError(500,"異常:系統異常，請聯絡站務人員");
        }

    }
}
