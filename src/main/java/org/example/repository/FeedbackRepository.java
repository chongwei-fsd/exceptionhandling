package org.example.repository;

import org.example.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
// save() -- save() method is also equivalent to performing an update
// findOne()
// findById()
// findByEmail()
// findAll()
// count()
// delete()
// deleteById()
}
