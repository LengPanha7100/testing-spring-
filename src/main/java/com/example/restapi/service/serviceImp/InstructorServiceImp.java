package com.example.restapi.service.serviceImp;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Instructor;
import com.example.restapi.model.request.InstructorRequest;
import com.example.restapi.repository.InstructorRepository;
import com.example.restapi.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImp implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImp(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Long pageNo , Long pageSize) {
        return instructorRepository.getAllInstructor(pageNo , pageSize);
    }

    @Override
    public Instructor getInstructorsById(Long id) {
        Instructor instructor = instructorRepository.getInstructorById(id);
        if(instructor == null){
            throw new BadRequestException("Get by "+ id+ " not found");
        }
        return instructor;
    }

    @Override
    public Instructor createInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.createInstructor(instructorRequest);
        return instructor;
    }

    @Override
    public Instructor updateInstructor(Long id, InstructorRequest instructorRequest) {
        getInstructorsById(id);
        Instructor instructor = instructorRepository.updateInstructor(id , instructorRequest);
        return instructor;
    }

    @Override
    public void deleteInstructor(Long id) {
       getInstructorsById(id);
       Instructor instructor = instructorRepository.deleteRepository(id);
    }

    @Override
    public List<Instructor> searchInstructorByName(String instructorName) {
        return instructorRepository.searchInstructorByName(instructorName);
    }
}
