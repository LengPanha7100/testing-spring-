package com.example.restapi.controller.team1;

import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Venues;
import com.example.restapi.model.request.VenuesRequest;
import com.example.restapi.service.team1.VenuesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@AllArgsConstructor
public class VenuesController {
    private final VenuesService venuesService;
    @GetMapping
    public ResponseEntity<APIResponse<List<Venues>>> getAllVenues(@RequestParam (defaultValue = "1") Long pageNo,
                                       @RequestParam (defaultValue = "10") Long pageSize) {
        List<Venues> venues = venuesService.getAllVenues(pageNo,pageSize);
        APIResponse<List<Venues>> apiResponse = APIResponse.<List<Venues>>builder()
                .message("Get all venues successfully")
                .status(HttpStatus.OK)
                .payload(venues)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Venues>> getVenuesById(@PathVariable Long id) {
        Venues venues = venuesService.getVenuesById(id);
        APIResponse<Venues> apiResponse = APIResponse.<Venues>builder()
                .message("Get venues by id successfully")
                .status(HttpStatus.OK)
                .payload(venues)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PostMapping
    public ResponseEntity<APIResponse<Venues>> createVenues(@RequestBody VenuesRequest venuesRequest) {
        Venues venues = venuesService.createVenues(venuesRequest);
        APIResponse<Venues> apiResponse = APIResponse.<Venues>builder()
                .message("Create venues successfully")
                .status(HttpStatus.OK)
                .payload(venues)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Venues>> updateVenuesById(@PathVariable Long id , @RequestBody VenuesRequest venuesRequest) {
        Venues venues = venuesService.updateVenuesById(id , venuesRequest);
        APIResponse<Venues> apiResponse = APIResponse.<Venues>builder()
                .message("Update venues by id successfully")
                .status(HttpStatus.OK)
                .payload(venues)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Venues>> deleteVenuesById(@PathVariable Long id ) {
        venuesService.deleteVenuesById(id );
        APIResponse<Venues> apiResponse = APIResponse.<Venues>builder()
                .message("Delete venues by "+id+" successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
