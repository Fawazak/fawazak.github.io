package PoolGame.State;


import PoolGame.ConfigReader;
import PoolGame.Game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import PoolGame.Items.PoolTable;


/** The Easy level state */
public class EasyLevel implements State{
    private ConfigReader loadConfig(List<String> args, String path) {
        String configPath;
        boolean isResourcesDir = false;
        if (args.size() > 0) {
            configPath = args.get(0);
        } else {
            configPath = path;
            isResourcesDir = true;
        }
        // parse the file:
        ConfigReader config = null;
        try {
            config = new ConfigReader(configPath, isResourcesDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ConfigReader.ConfigKeyMissingException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        }
        return config;
    }



    @Override
    public Game load(Context context,  Stage stage,  Group root, Button easy, Button normal,  Button hard, Text timer, Text score) {
        List<String> args = new ArrayList<>();

        ConfigReader config = loadConfig(args, "/config_easy.json");
        Game game = new Game(config, timer, score);

        stage.setWidth(game.getWindowDimX()+100);
        stage.setHeight(game.getWindowDimY() +
                15 +
                PoolTable.POCKET_OFFSET +
                4);

        game.removeDrawables(root);
        game.addDrawables(root);
        EventHandler<ActionEvent> eventNormal = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                game.change = true;
                game.removeDrawables(root);
                context.setState(new NormalLevel());
                context.load(stage, root, easy,normal,hard, timer, score);

            }
        };
        EventHandler<ActionEvent> eventHard = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                game.change = true;
                game.removeDrawables(root);
                context.setState(new HardLevel());
                context.load(stage, root, easy,normal,hard, timer, score);

            }
        };

        normal.setOnAction(eventNormal);
        hard.setOnAction(eventHard);
        return game;

    }
}


