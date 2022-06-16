package salon.data;

import org.springframework.data.repository.CrudRepository;
import salon.dao.Record;

public interface RecordRepository extends CrudRepository<Record, Long> {
}
