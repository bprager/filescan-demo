package ws.prager.filescan.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    public FileUploadController() {
        logger.debug("started");
        // TODO Auto-generated constructor stub
    }

    @CrossOrigin
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    // void UploadFile(MultipartHttpServletRequest request, HttpServletResponse
    // response) {
    void uploadFileHandler(@RequestParam("name") String name, @RequestParam("sha256") String sha256,
            @RequestParam("file") MultipartFile file) {

        logger.debug(name + " uploaded");
        logger.debug("hash: " + sha256);
        logger.debug("file length: " + file.getSize());

    }

}
