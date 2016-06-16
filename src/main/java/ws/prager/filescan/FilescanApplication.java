package ws.prager.filescan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilescanApplication {

    final static Logger logger = LoggerFactory.getLogger(FilescanApplication.class);

    public static void main(String[] args) {
        logger.debug("**** starting ****");
        SpringApplication.run(FilescanApplication.class, args);
    }
}
