package ws.prager.filescan.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadRepository postRepository;

    @Override
    public Upload save(Upload post) {
        postRepository.save(post);
        return post;
    }

    @Override
    public Upload findOne(String id) {
        return postRepository.findOne(id);
    }

    @Override
    public Iterable<Upload> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Page<Upload> findByTagsName(String tagName, PageRequest pageRequest) {
        return postRepository.findByTagsName(tagName, pageRequest);
    }
}
