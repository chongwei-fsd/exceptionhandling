package org.example.controller;

import jakarta.validation.Valid;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Customer;
import org.example.model.Feedback;
import org.example.repository.CustomerRepository;
import org.example.repository.FeedbackRepository;
import org.example.service.CustomerService;
import org.example.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<Object> allFeedback(){
        List<Feedback>feedbackList=feedbackService.findAll();
        if(feedbackList.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }

    //create feedback
    @PostMapping("/{id}")
    public ResponseEntity<Object> saveFeedback(@PathVariable("id")Long customerId, @RequestBody @Valid Feedback feedback){
        Feedback checkFeedback=customerService.findById(customerId).map(c->{
            Feedback newFeedback=new Feedback(c,feedback.getDescription());
            return feedbackService.save(newFeedback);
        }).orElseThrow(ResourceNotFoundException::new);

        return new ResponseEntity<>(checkFeedback,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object>updateFeedback(@PathVariable("id")Long feedbackId,@RequestBody @Valid Feedback feedback){
        Feedback checkFeedback=feedbackService.findById(feedbackId).map(f->{
            f.setDescription(feedback.getDescription());
            return feedbackService.save(f);
        }).orElseThrow(ResourceNotFoundException::new);

        return new ResponseEntity<>(checkFeedback,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteFeedback(@PathVariable("id")Long feedbackId){
        Feedback checkFeedback=feedbackService.findById(feedbackId).map(f->{
            feedbackService.deleteById(f.getId());
            return f;
        }).orElseThrow(ResourceNotFoundException::new);

        String response=String.format("Feedback %d deleted",checkFeedback.getId());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object>countFeedback(){
        long count=feedbackService.count();
        if(count<=0){
            return new ResponseEntity<>("No feedback",HttpStatus.NOT_FOUND);
        }
        Map<String,Object>totalFeedback=new HashMap<>();
        totalFeedback.put("total",count);
        return new ResponseEntity<>(totalFeedback,HttpStatus.OK);
    }









}
