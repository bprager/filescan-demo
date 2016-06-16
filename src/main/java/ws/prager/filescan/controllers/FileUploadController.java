package ws.prager.filescan.controllers;

import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {

    final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    public FileUploadController() {
        logger.debug("started");
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void UploadFile(MultipartHttpServletRequest request, HttpServletResponse response) {
        Iterator<String> itr = request.getFileNames();

        MultipartFile file = request.getFile(itr.next());

        String fileName = file.getOriginalFilename();
        logger.debug(fileName + " uploaded");
    }

}
