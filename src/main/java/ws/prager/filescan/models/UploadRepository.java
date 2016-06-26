package ws.prager.filescan.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UploadRepository extends ElasticsearchRepository<Upload, String> {

    Page<Upload> findByTagsName(String name, Pageable pageable);

}
