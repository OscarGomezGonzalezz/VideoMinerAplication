package videoMiner.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import videoMiner.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video,String> {
    Page<Video> findByName(String nombre, Pageable pageable);
    Page<Video> findAll(Pageable pageable);
}
