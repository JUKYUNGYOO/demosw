package events;

import lombok.*;

import java.time.LocalDateTime;


/*
Builder를 추가하면 public 이 아니므로
다른 에노테이션을 추가해야 한다.
 */
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of ="id")


public class Event {
    private Integer id;
    private String description;
    private String name;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임 private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;

    private boolean offline;
    private boolean free;
    private EventStatus eventStatus = EventStatus.DRAFT;
}
