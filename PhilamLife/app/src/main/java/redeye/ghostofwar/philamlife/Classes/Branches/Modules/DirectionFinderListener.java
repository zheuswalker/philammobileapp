package redeye.ghostofwar.philamlife.Classes.Branches.Modules;

import java.util.List;


public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
