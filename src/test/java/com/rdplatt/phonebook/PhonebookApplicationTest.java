package com.rdplatt.phonebook;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PhonebookApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGet() throws JSONException {

        String response = this.restTemplate.getForObject("/contact", String.class);

        JSONAssert.assertEquals("[{\"firstName\":\"Rich\",\"lastName\":\"Platt\",\"phoneNumber\":\"123123\",\"id\":1},{\"firstName\":\"Suze\",\"lastName\":\"Platt\",\"phoneNumber\":\"123123\",\"id\":2},{\"firstName\":\"Joe\",\"lastName\":\"Platt\",\"phoneNumber\":\"123123\",\"id\":3}]",
                response, false);
    }

}