package events;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
/*
Repository 를 빈으로 등록 하지 않고,
Web 용 만 등록 해줌.
 */
public class EventControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;
    /*
    repository 를 빈으로 등록,하면 되지만 또다른 에러
    Mock객체 이기 때문에 리턴 되는 값이 전부 null 임.
    nullPointerException발생, save()를 호출 할 때, 어떻게 동작하는지
    알려줘야 함.
     */
    @Test
    public void createEvent() throws Exception{
        /*
        post(요청) - /api/events/ 경로에 요청 함.
         */
        Event event = Event.builder()
                .name("Spring")
                .description("REST API Development with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2018,11,23,14,21))

                .closeEnrollmentDateTime(LocalDateTime.of(2018,11,24,14,21))
                .beginEventDateTime(LocalDateTime.of(2018,11,25,14,21))
                .endEventDateTime(LocalDateTime.of(2018,11,26,14,21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타텁 팩토리")
                .build();
        event.setId(10);//Id 설정
        Mockito.when(eventRepository.save(event)).thenReturn(event);
        /*
        eventRepository의 save()가 호출 되면 event를 리턴하라.
         */

        mockMvc.perform(post("/api/events/")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //JSON을 넘기고 있다. MediaType으로 알려
                .accept(MediaTypes.HAL_JSON) //어떠한 응답을 원한다. - HAL_JSON을 원한다.
                .content(objectMapper.writeValueAsString(event))
              //객체를 Json문자열에 넣어주면 됨.
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect( jsonPath("id").exists())
                //id가 있는지 확인
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,MediaTypes.HAL_JSON_UTF8_VALUE));
    }

}
