package events;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
command + shift + t 누르면 test클래스 생성할 수 있음.
 */
public class EventTest {
  @Test
    public void builder(){
      /*
      builder() 사용의 좋은 점
      내가 입력하는 값이 무엇인지 알 수 있다.
       */
      Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API development with Spring")
                .build();


       /*
        Event 클래스에 @Builder 어노테이션 추가
        */
       assertThat(event).isNotNull();


  }


  @Test
  public void javaBean(){
      //Given
      String name = "Event";
      String description = "Spring";

      //When
      Event event = new Event();
      event.setName(name);
      event.setDescription(description);

      //Then
      assertThat(event.getName()).isEqualTo(name);
      assertThat(event.getDescription()).isEqualTo(description);

  }

}





