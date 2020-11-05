package eci.ieti.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.client.gridfs.model.GridFSFile;
import eci.ieti.data.model.Todo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RequestMapping("api")
@RestController
public class RESTController {

    //TODO inject components (TodoRepository and GridFsTemplate)
    @Autowired
    GridFsTemplate gridTemplate;
    @Autowired
    ObjectMapper mapperJson;

    @RequestMapping("/files/{filename}")
    public ResponseEntity<InputStreamResource> getFileByName(@PathVariable String filename) throws IOException {

        //TODO implement method
        GridFSFile file = gridTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(filename)));
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            GridFsResource resource = gridTemplate.getResource(file.getFilename());
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(resource.getContentType()))
                    .body(new InputStreamResource(resource.getInputStream()));
        }

    }

    @CrossOrigin("*")
    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        //TODO implement method
        gridTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        return file.getOriginalFilename();
    }

    @CrossOrigin("*")
    @PostMapping("/newtodo")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        //TODO implement method
        try {
            Unirest.post("https://taskplanners.azurewebsites.net/api/add-task?code=NWSnen0J4ChNFX2V00AR6LAvFRNd/WJAiOBTSEM3ZrsBUB6k8Ar73Q==")
                    .header("Content-Type", "application/json")
                    .body(mapperJson.writeValueAsString(todo)).asString().getStatus();
        } catch (UnirestException | JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @CrossOrigin("*")
    @GetMapping("/todo")
    public ResponseEntity<?> getTodoList() {
        String tasks = null;
        try {
            tasks = Unirest.get("https://taskplanners.azurewebsites.net/api/add-task?code=NWSnen0J4ChNFX2V00AR6LAvFRNd/WJAiOBTSEM3ZrsBUB6k8Ar73Q==")
                    .asString().getBody();
        } catch (UnirestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
