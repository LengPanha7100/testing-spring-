package com.example.restapi.service.team1;

import com.example.restapi.model.Venues;
import com.example.restapi.model.request.VenuesRequest;

import java.util.List;

public interface VenuesService {
    List<Venues> getAllVenues(Long pageNo, Long pageSize);

    Venues getVenuesById(Long id);

    Venues createVenues(VenuesRequest venuesRequest);

    Venues updateVenuesById(Long id, VenuesRequest venuesRequest);

    void deleteVenuesById(Long id);
}
