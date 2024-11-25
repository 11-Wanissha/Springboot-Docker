package com.example.firstJobApp.job.impl;

import com.example.firstJobApp.job.Job;
import com.example.firstJobApp.job.JobRepository;
import com.example.firstJobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }
    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }
    @Override
    public Job findById(Long id) {
      return jobRepository.findById(id).orElse(null);
    }
    @Override
    public boolean deleteJob(Long id) {
        if (jobRepository.existsById(id))  {
            jobRepository.deleteById(id);
            return true;
        }
            return false;
    }
    @Override
    public boolean updateJob(Long id, Job newJob) {
        Optional<Job> existingJob = jobRepository.findById(id);
        if (existingJob.isPresent()) {
            Job job = existingJob.get();
            updateIfNotNull(job::setTitle, newJob.getTitle());
            updateIfNotNull(job::setDescription, newJob.getDescription());
            updateIfNotNull(job::setMinSalary, newJob.getMinSalary());
            updateIfNotNull(job::setMaxSalary, newJob.getMaxSalary());
            updateIfNotNull(job::setLocation, newJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
    private <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
