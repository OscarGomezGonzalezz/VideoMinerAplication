package videoMiner.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import videoMiner.model.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,String> {
    Page<Channel> findByName(String name, Pageable pageable);
    Page<Channel> findAll(Pageable pageable);
}
