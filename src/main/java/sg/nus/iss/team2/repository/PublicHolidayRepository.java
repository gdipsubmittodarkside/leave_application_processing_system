package sg.nus.iss.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.team2.model.PublicHoliday;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday,Long> {
    
}
