package hasherr.ghostly.main.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hasherr.ghostly.main.entity.Wall;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Evan
 * Date: 2/10/14
 */
public class StateManager
{
    List<State> currentStates;
    float lastPosition;
    OrthographicCamera camera;

    public StateManager(OrthographicCamera camera)
    {
        currentStates = new ArrayList<State>();
        this.camera = camera;

        currentStates.add(new MenuState(this, camera));
    }

    public void render(SpriteBatch batch)
    {
        for (State gameState : currentStates)
        {
            gameState.render(batch);
        }
    }

    public void update()
    {
        State currentState = getCurrentState();
        currentState.update();

        if (currentState instanceof GameState)
        {
            if (((GameState) currentState).isReadyForSwitchAway)
            {
                switchToDeathState();
            }
        }
    }

    // Switches from the GameState to the DeathState.
    public void switchToDeathState()
    {
        currentStates.add(new DeathState(this, camera, camera.position.x - 125f, ((GameState) getCurrentState()).getScore()));
    }

    // Switches from the MenuState to the PauseState.
    public void switchToGameState()
    {
        resetForNewState();
        currentStates.add(new GameState(this));
    }

    // Switches from PauseState/DeathState to the MenuState.
    public void switchToMenuState()
    {
        resetForNewState();
        currentStates.add(new MenuState(this, camera));
    }

    private void resetForNewState()
    {
        Wall.allWalls.clear();
        Wall.wallsNotPassed.clear();
        currentStates.clear();
    }

    public float getCorrectCameraPosition()
    {
        if (getCurrentState() instanceof GameState)
        {
            lastPosition = ((GameState) getCurrentState()).getSetXPosition();
        }

        return lastPosition;
    }

    // Gets the last state in the index for rendering and updating purposes.
    public State getCurrentState()
    {
        State currentState = currentStates.get(currentStates.size() - 1);
        return currentState;
    }
}

