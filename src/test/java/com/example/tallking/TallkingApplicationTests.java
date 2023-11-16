package com.example.tallking;

import com.example.tallking.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TallkingApplicationTests {

    @Test
    void contextLoads() {
        String a="true";
        Boolean b= Boolean.valueOf(a);

        if(b){
            System.out.println("11111111111");
        }else {
            System.out.println("22222222222");
        }
    }

}
