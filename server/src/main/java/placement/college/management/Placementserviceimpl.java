package placement.college.management;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Placementserviceimpl implements Placementservice {

    @Autowired
    private Placementrepository placementRepository;

    @Override
    public Placementcriteria createPlacement(Placementcriteria Placementcriteria) {
        return placementRepository.save(Placementcriteria);
    }

    @Override
    public List<Placementcriteria> getAllPlacements() {
        return placementRepository.findAll();
    }

    @Override
    public Optional<Placementcriteria> getPlacementById(Long id) {
        return placementRepository.findById(id);
    }

    @Override
    public Placementcriteria updatePlacement(Long id, Placementcriteria Placementcriteria) {
        return placementRepository.findById(id)
                .map(existingPlacement -> {
                    Placementcriteria.setId(id);
                    return placementRepository.save(Placementcriteria);
                })
                .orElseThrow(() -> new RuntimeException("Placement not found with ID: " + id));
    }

    @Override
    public boolean deletePlacement(Long id) {
        if (placementRepository.existsById(id)) {
            placementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
