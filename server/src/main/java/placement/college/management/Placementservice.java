package placement.college.management;

import java.util.List;
import java.util.Optional;



public interface Placementservice {

    Placementcriteria createPlacement(Placementcriteria Placementcriteria);

    List<Placementcriteria> getAllPlacements();

    Optional<Placementcriteria> getPlacementById(Long id);

    Placementcriteria updatePlacement(Long id, Placementcriteria Placementcriteria);

    boolean deletePlacement(Long id);

}
