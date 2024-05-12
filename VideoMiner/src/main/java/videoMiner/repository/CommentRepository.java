package videoMiner.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import videoMiner.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {
    Page<Comment> findAll(Pageable pageable);
}
