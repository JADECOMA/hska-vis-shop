//package de.hska.iwi.vs.lab.composite.user.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class UserCompositeController {
//
//    private static Logger log = LoggerFactory.getLogger(UserCompositeController.class);
//
//    @GetMapping(value="/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> blub(@PathVariable("userId") Long userId) {
//        log.info("URL-PATH: / | METHOD: GET");
//
//        return new ResponseEntity<>("{\"test\":\"composite\"}", HttpStatus.OK);
//    }
//}
