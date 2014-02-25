package hasherr.ghostly.main.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    AssetManager assetManager;
    boolean isLoading;
    boolean isReady;

    // In-use textures.
    public static Texture backgroundTexture;
    public static Texture ghostTexture;
    public static Texture wallTexture;
    public static Texture replayButtonTexture;
    public static Texture menuButtonTexture;

    public StateManager(OrthographicCamera camera)
    {
        isLoading = true;
        isReady = false;
        currentStates = new ArrayList<State>();
        assetManager = new AssetManager();
        this.camera = camera;
        loadTextures();
        while (!assetManager.update());
        if (!isLoading && assetManager.update())
        {
            Gdx.app.log("Debug1", "Assets loaded, update() == true");
            isLoading = true;
            finishLoading();
            currentStates.add(new GameState()); // temporary.

            isReady = true;
        }
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
        Gdx.app.log("Debug1", "Okay, we're not there yet.");
        if (assetManager.update())
        {
            Gdx.app.log("Debug1", "Okay, we're where we want to be.");
            getCurrentState().update();

            // If the currently updating state is ready to be switched, remove it from the list of current states.

        }
    }

    private void loadTextures()
    {
        String[] textureStrings = { "sprites/background.png",
                                    "sprites/ghost.png",
                                    "sprites/wall.png",
                                    "sprites/ui/replay_button.png",
                                    "sprites/ui/main_menu_button.png"
                                  };

        for (String textureString : textureStrings)
        {
            assetManager.load(textureString, Texture.class);
        }

        isLoading = false;
        Gdx.app.log("Debug1", "TEXTURES LOADED");
    }

    // Load the textures into memory.
    private void finishLoading()
    {
        backgroundTexture = assetManager.get("sprites/background.png", Texture.class);
        ghostTexture = assetManager.get("sprites/ghost.png", Texture.class);
        wallTexture = assetManager.get("sprites/wall.png", Texture.class);
        replayButtonTexture = assetManager.get("sprites/ui/replay_button.png", Texture.class);
        menuButtonTexture = assetManager.get("sprites/ui/main_menu_button.png", Texture.class);
    }

    // Switches from the GameState to the DeathState.
    public void switchToDeathState()
    {
        State currentGameState = getCurrentState();
        currentGameState.prepareForSwitchAway();
        currentStates.add(new DeathState(camera, ((GameState) currentGameState).getSetXPosition()));
    }

    // Switches from the GameState to the PauseState.
    public void switchToPauseState()
    {
        getCurrentState().prepareForSwitchAway();
        currentStates.add(new DeathState(camera, ((GameState) getCurrentState()).getSetXPosition()));
    }

    // Switches from the MenuState to the PauseState.
    public void switchToGameState()
    {

    }

    // Switches from PauseState/DeathState to the MenuState.
    public void switchToMenuState()
    {

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
        if (assetManager.update())
        {
            State currentState = currentStates.get(currentStates.size() - 1);
            return currentState;
        }

        return null;
    }


    public void updateBal()
    {
        assetManager.update();
    }
}

