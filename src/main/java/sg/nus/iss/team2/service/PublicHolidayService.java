package sg.nus.iss.team2.service;

import java.util.List;

import javax.validation.Valid;

import sg.nus.iss.team2.model.PublicHoliday;

public interface PublicHolidayService {
    List<PublicHoliday> saveAll(List<PublicHoliday> holidays);

    List<PublicHoliday> findAllPublicHolidays();

    PublicHoliday findPublicHolidaysById(Long id);

    PublicHoliday createHoliday(PublicHoliday holiday);

    void deleteHoliday(Long id);

    PublicHoliday updateHoliday(@Valid PublicHoliday holiday);
    
}
