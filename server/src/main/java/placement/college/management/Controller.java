package placement.college.management;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/placements")
public class Controller {

    @Autowired
    private Placementservice placementService;

    public Controller(Placementservice placementService) {
        this.placementService = placementService;
    }

    // Create a new placement criteria
    @PostMapping("/create")
    public ResponseEntity<Placementcriteria> createPlacement(@Valid @RequestBody Placementcriteria Placementcriteria) {
        Placementcriteria savedPlacement = placementService.createPlacement(Placementcriteria);
        return new ResponseEntity<>(savedPlacement, HttpStatus.CREATED);
    }

    // // Get all placement criteria
    @GetMapping("/all")
    public ResponseEntity<List<Placementcriteria>> getAllPlacements() {
        List<Placementcriteria> placements = placementService.getAllPlacements();
        System.out.println("\n\n\n\n\n\n\nFetched Placements: " + placements + "\n\n\n\n\n\n\n");
        return new ResponseEntity<>(placements, HttpStatus.OK);
    }

    // Get a specific placement by ID
    @GetMapping("/{id}")
    public ResponseEntity<Placementcriteria> getPlacementById(@PathVariable("id") Long id) {
        Optional<Placementcriteria> placement = placementService.getPlacementById(id);
        return placement.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update placement criteria by ID
    @PutMapping("/{id}")
    public ResponseEntity<Placementcriteria> updatePlacement(@PathVariable("id") Long id,
            @Valid @RequestBody Placementcriteria Placementcriteria) {
        Optional<Placementcriteria> existingPlacement = placementService.getPlacementById(id);
        if (existingPlacement.isPresent()) {
            Placementcriteria updatedPlacement = placementService.updatePlacement(id, Placementcriteria);
            return new ResponseEntity<>(updatedPlacement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete placement criteria by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlacement(@PathVariable("id") Long id) {
        if (placementService.deletePlacement(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
