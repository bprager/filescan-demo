package ws.prager.filescan;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ws.prager.filescan.models.Tag;
import ws.prager.filescan.models.Upload;
import ws.prager.filescan.models.UploadService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ElasticSearchTest {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(Upload.class);
        elasticsearchTemplate.createIndex(Upload.class);
        elasticsearchTemplate.putMapping(Upload.class);
        elasticsearchTemplate.refresh(Upload.class, true);
    }

    // @Test
    public void testSave() throws Exception {
        Tag tag = new Tag();
        tag.setId("1");
        tag.setName("size");
        Tag tag2 = new Tag();
        tag2.setId("2");
        tag2.setName("type");

        Upload upload = new Upload();
        upload.setId("1");
        upload.setFileName("File1.png");
        upload.setTags(Arrays.asList(tag, tag2));
        uploadService.save(upload);

        assertThat(upload.getId(), notNullValue());

        Upload upload2 = new Upload();
        upload2.setId("2");
        upload2.setFileName("File2.txt");
        upload2.setTags(Arrays.asList(tag));
        uploadService.save(upload);
        assertThat(upload2.getId(), notNullValue());

    }

    public void testFindOne() throws Exception {

    }

    public void testFindAll() throws Exception {

    }

    @Test
    public void testFindByTagsName() throws Exception {
        Tag tag1 = new Tag();
        tag1.setId("1");
        tag1.setName("size");
        tag1.setValue("1096");
        Tag tag2 = new Tag();
        tag2.setId("2");
        tag2.setName("type");
        tag2.setValue("image/png");

        Upload upload = new Upload();
        upload.setId("1");
        upload.setFileName("File1.png");
        upload.setTags(Arrays.asList(tag1, tag2));
        uploadService.save(upload);

        Upload upload2 = new Upload();
        upload2.setId("1");
        upload2.setFileName("File1.png");
        upload2.setTags(Arrays.asList(tag1));
        uploadService.save(upload);

        Page<Upload> uploads = uploadService.findByTagsName("size", new PageRequest(0, 10));
        Page<Upload> uploads2 = uploadService.findByTagsName("type", new PageRequest(0, 10));
        Page<Upload> uploads3 = uploadService.findByTagsName("none-existing", new PageRequest(0, 10));

        assertThat(uploads.getTotalElements(), is(1L));
        assertThat(uploads2.getTotalElements(), is(1L));
        assertThat(uploads3.getTotalElements(), is(0L));
    }
}