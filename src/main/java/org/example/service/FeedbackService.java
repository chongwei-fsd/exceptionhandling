package org.example.service;

import org.example.model.Feedback;
import org.example.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements FeedbackServiceInterface {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public Feedback save(Feedback feedback){
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> findAll(){
        return feedbackRepository.findAll();
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Feedback update(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteById(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public long count() {
        return feedbackRepository.count();
    }

}
