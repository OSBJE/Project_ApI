package project_ApI.DanmarksStatistik.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project_ApI.DanmarksStatistik.model.DSTmodel01;

@Repository
public interface DSTrepository extends JpaRepository<DSTmodel01, Integer> {

}
