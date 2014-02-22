package hasherr.ghostly.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hasherr.ghostly.main.entity.Ghost;

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
        currentStates.add(new GameState()); // temporary.
        this.camera = camera;
    }

    public void render(SpriteBatch batch)
    {
        for (State gameState : currentStates)
        {
            for (State state : currentStates)
            {
                state.render(batch);
            }
        }
    }

    public void update()
    {
        getCurrentState().update();

        // If the currently updating state is ready to be switched, remove it from the list of current states.
        if (getCurrentState().isReadyForSwitchAway())
        {
            State oldState = getCurrentState();
            oldState.prepareForSwitchAway();
            currentStates.add(new DeathState(camera, ((GameState) oldState).getSetXPosition()));
        }
    }

    public float getCorrectCameraPosition()
    {
        if (getCurrentState() instanceof GameState)
        {
            lastPosition = ((GameState) getCurrentState()).playerGhost.pos.x;
        }

        return lastPosition;
    }

    public State getCurrentState()
    {
        State currentState = currentStates.get(currentStates.size() - 1);
        return currentState;
    }

}

