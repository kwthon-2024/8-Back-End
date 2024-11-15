package kwthon.kwclub.club.DTO;

import jakarta.persistence.*;
import kwthon.kwclub.com.team.project.entity.Club;
import kwthon.kwclub.com.team.project.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ScheduleDTO {
    private Long id;
    private Club club;
    private String eventName;
    private String eventDate;
    private String location;

    public static ScheduleDTO from(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .club(schedule.getClub())
                .eventName(schedule.getEventName())
                .eventDate(schedule.getEventDate())
                .location(schedule.getLocation())
                .build();
    }

    public static Schedule toEntity(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .id(scheduleDTO.getId())
                .club(scheduleDTO.getClub())
                .eventDate(scheduleDTO.getEventDate())
                .eventName(scheduleDTO.getEventName())
                .location(scheduleDTO.getLocation())
                .build();
    }
}
