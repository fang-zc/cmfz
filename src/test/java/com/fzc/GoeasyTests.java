package com.fzc;

import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GoeasyTests {
    @Test
    public void test1() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-03eb8cfc24824db5a57134b8f2bd20c3");
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }
}
