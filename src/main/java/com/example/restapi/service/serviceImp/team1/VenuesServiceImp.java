package com.example.restapi.service.serviceImp.team1;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Venues;
import com.example.restapi.model.request.VenuesRequest;
import com.example.restapi.repository.team1.VenuesRepository;
import com.example.restapi.service.team1.VenuesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VenuesServiceImp implements VenuesService {
    private final VenuesRepository venuesRepository;

    @Override
    public List<Venues> getAllVenues(Long pageNo, Long pageSize) {
        return venuesRepository.getAllVenues(pageNo , pageSize);
    }

    @Override
    public Venues getVenuesById(Long id) {
        Venues venues = venuesRepository.getVenuesById(id);
        if(venues == null){
            throw new BadRequestException("Get venues by id " +id+ " is null");
        }
        return venues;
    }

    @Override
    public Venues createVenues(VenuesRequest venuesRequest) {
        return venuesRepository.createVenues(venuesRequest);
    }

    @Override
    public Venues updateVenuesById(Long id, VenuesRequest venuesRequest) {
        getVenuesById(id);
        return venuesRepository.updateVenuesById(id,venuesRequest);
    }

    @Override
    public void deleteVenuesById(Long id) {
        getVenuesById(id);
        venuesRepository.deleteVenues(id);
    }
}
