package kr.spartaclub.calender.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import kr.spartaclub.calender.entity.Calender;

public interface CalenderRepository extends JpaRepository<Calender,Long> {
}
