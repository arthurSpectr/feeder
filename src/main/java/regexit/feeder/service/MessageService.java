package regexit.feeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import regexit.feeder.domain.Message;
import regexit.feeder.repos.MessageRepo;
import regexit.feeder.service.paging.Paged;
import regexit.feeder.service.paging.Paging;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MessageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MessageRepo messageRepo;

    public Paged<Message> findByFilterOrDefault(String filter, int pageNumber, int size) {
        Page<Message> page;

        if (filter != null && !filter.isEmpty()) {
//            page = messageRepo.findByTag(filter, pageable);
            page = messageRepo.findByTag(filter, PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC, "id")));
        } else {
            page = messageRepo.findAll(PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC, "id")));
        }

        return new Paged<>(page, Paging.of(page.getTotalPages() + 1, pageNumber, size));
    }

    public void save(Message message, MultipartFile file) throws IOException {
        saveFile(message, file);

        messageRepo.save(message);
    }

    public void saveFile(@Valid Message message, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
    }
}
