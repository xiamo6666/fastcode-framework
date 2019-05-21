package com.ssos.bf;


import com.ssos.formengine.service.AutoDefinitionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestHashCode.class})
@WebAppConfiguration
public class TestHashCode {

    @Autowired
    private AutoDefinitionService autoDefinitionService;

    @Test
    public void testchaxun() {
        System.out.println(autoDefinitionService);
//        TljucUtil.timeTasks(1000, 10, () -> {
//            System.out.println(autoDefinitionService.showtable("test"));
//        });
    }
}
