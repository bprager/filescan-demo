package ws.prager.filescan.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UploadService {
    Upload save(Upload post);

    Upload findOne(String id);

    Iterable<Upload> findAll();

    Page<Upload> findByTagsName(String tagName, PageRequest pageRequest);
}
