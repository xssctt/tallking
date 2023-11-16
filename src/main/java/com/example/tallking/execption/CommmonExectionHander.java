package com.example.tallking.execption;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommmonExectionHander {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Map<String,String> RuntiomeExection(Throwable e){

        e.printStackTrace();
        Map<String,String> map=new HashMap<String,String>();
        map.put("msg",e.getMessage());
        map.put("error","error");
        map.put("data",null);

        return map;
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String,String> RuntiomeExection(BindException e){

        e.printStackTrace();
        Map<String,String> map=new HashMap<String,String>();
        map.put("msg",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        map.put("error","error");
        map.put("data",null);

        return map;
    }

}
