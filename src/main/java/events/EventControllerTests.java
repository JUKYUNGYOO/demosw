package events;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void createEvent() throws Exception{
        /*
        post(요청) - /api/events/ 경로에 요청 함.
         */
        mockMvc.perform(post("/api/events/")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //JSON을 넘기고 있다. MediaType으로 알려
                .accept(MediaTypes.HAL_JSON) //어떠한 응답을 원한다. - HAL_JSON을 원한다.

        )
                .andExpect(status().isCreated());
    }

}
