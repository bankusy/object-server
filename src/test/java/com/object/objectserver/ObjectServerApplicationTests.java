package com.object.objectserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectServerApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(String.format("HM%010d", 103));
    }

}
