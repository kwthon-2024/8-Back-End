package kwthon.kwclub.club.service;

import kwthon.kwclub.club.DTO.ScheduleDTO;
import kwthon.kwclub.club.repository.ScheduleRepository;
import kwthon.kwclub.com.team.project.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = Schedule.builder()
                .eventDate(scheduleDTO.getEventDate())
                .club(scheduleDTO.getClub())
                .eventName(scheduleDTO.getEventName())
                .location(scheduleDTO.getLocation())
                .build();
        return ScheduleDTO.from(scheduleRepository.save(schedule));
    }

    public List<ScheduleDTO> getAllSchedules(Long clubId) {
        return scheduleRepository.findAllByClub_Id(clubId)
                .stream()
                .map(ScheduleDTO::from)
                .toList();
    }
}
