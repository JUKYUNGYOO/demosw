package events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@SpringBootConfiguration
@Controller
@RequestMapping(value ="/api/events",produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {
   /*
   만들어진 레포지터리를 주입
   생성자의 파라미터가 이미 빈으로 등록되어 있다면
   @Autowired를 생략해도 됨.
    */
    private final EventRepository eventRepository;
    public EventController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event){

        /*
        Location Uri 만들기,
        EventController 클래스의 메소드 안에, createEvent()
         */
        Event newEvent = this.eventRepository.save(event);
        //저장된 객체가 나옴.
        URI createUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        event.setId(10);
                return ResponseEntity.created(createUri).body(event);
    }


}
