package videoMiner.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import videoMiner.model.Caption;

@Repository
public interface CaptionRepository extends JpaRepository<Caption, String> {
    Page<Caption> findAll(Pageable pageable);
}
