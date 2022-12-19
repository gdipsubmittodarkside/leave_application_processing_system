package sg.nus.iss.team2.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.team2.model.PublicHoliday;
import sg.nus.iss.team2.repository.PublicHolidayRepository;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {

    @Autowired
    private PublicHolidayRepository repo;

    @Override
    public List<PublicHoliday> saveAll(List<PublicHoliday> holidays) {
        return repo.saveAll(holidays);
    }

    @Override
    public List<PublicHoliday> findAllPublicHolidays() {

        return repo.findAll();
    }

    @Override
    public PublicHoliday findPublicHolidaysById(Long id) {

        return repo.findById(id).get();
    }

    @Override
    public PublicHoliday createHoliday(PublicHoliday holiday) {
        return repo.save(holiday);
    }

    @Override
    public void deleteHoliday(Long id) {
        repo.deleteById(id);

    }

    @Override
    public PublicHoliday updateHoliday(@Valid PublicHoliday holiday) {

        return repo.save(holiday);

    }

}
