package my.web.app.lesson31.repo;

import my.web.app.lesson31.models.Good;
import org.springframework.data.repository.CrudRepository;

public interface IGoodRepository extends CrudRepository<Good,Long> {
}
