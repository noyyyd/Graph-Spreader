package ru.rsreu.graphspreader.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import ru.rsreu.graphspreader.model.algorithms.Algorithm;
import ru.rsreu.graphspreader.model.algorithms.FruchtermanReingold;
import ru.rsreu.graphspreader.model.elements.Position;
import ru.rsreu.graphspreader.model.elements.graph.Edge;
import ru.rsreu.graphspreader.model.elements.graph.Graph;
import ru.rsreu.graphspreader.model.elements.graph.Vertex;
import ru.rsreu.graphspreader.model.transfer.InputValues;
import ru.rsreu.graphspreader.model.utils.GraphCreator;


import static ru.rsreu.graphspreader.GraphSpreader.stageForFile;

public class FrontController {
    private Algorithm algorithm;
    private FileChooser fileChooser = new FileChooser();
    private int iterator = 0;
    private Graph graph;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Attraction;

    @FXML
    private TextField Gravity;

    @FXML
    private TextField Iterations;

    @FXML
    private MenuBar MenuBar;

    @FXML
    private TextField Repulsive;

    @FXML
    private Text Warning;

    @FXML
    private Button configuration;

    @FXML
    private AnchorPane graphDiagram;

    @FXML
    private Label iterations;

    @FXML
    private Label l;

    @FXML
    private Button startButton10x;

    @FXML
    private Button startButton1x;

    @FXML
    private Button startButton20x;

    @FXML
    private Button startButton5x;


    @FXML
    void initialize() {
        initMenu();

        configuration.setOnAction(actionEvent -> {
            createConfiguration();
        });

        startButton1x.setOnAction(actionEvent -> {
            if (algorithm != null) {
                actionOnButton(1);
            } else {
                Warning.setText("WARNING!!! \n Set the configuration");
            }
        });

        startButton5x.setOnAction(actionEvent -> {
            if (algorithm != null) {
                actionOnButton(5);
            } else {
                Warning.setText("WARNING!!! \n Set the configuration");
            }
        });

        startButton10x.setOnAction(actionEvent -> {
            if (algorithm != null) {
                actionOnButton(10);
            } else {
                Warning.setText("WARNING!!! \n Set the configuration");
            }
        });

        startButton20x.setOnAction(actionEvent -> {
            if (algorithm != null) {
                actionOnButton(20);
            } else {
                Warning.setText("WARNING!!! \n Set the configuration");
            }
        });
    }

    private void initGraph(File file) {
        GraphCreator graphCreator = new GraphCreator();
        graph = graphCreator.createGraphFromFile(file);
        graphDiagram.getChildren().addAll(createEdges(graph.getEdges()));
        graphDiagram.getChildren().addAll(createVertexes(graph.getVertexes()));
    }

    private void actionOnButton(int countIterations) {
        iterator += countIterations;
        startForceAlgorithm(countIterations);
        graphDiagram.getChildren().clear();
        graphDiagram.getChildren().addAll(createEdges(graph.getEdges()));
        graphDiagram.getChildren().addAll(createVertexes(graph.getVertexes()));
        iterations.setText("Iterations: " + iterator);
    }

    private InputValues createTransferObject() {
        InputValues inputValues = new InputValues();

        inputValues.setIterations(parseConfiguration(Iterations.getText()));
        inputValues.setGravity(parseConfiguration(Gravity.getText()));
        inputValues.setAttraction(parseConfiguration(Attraction.getText()));
        inputValues.setRepulsive(parseConfiguration(Repulsive.getText()));
        inputValues.setGraph(graph);
        inputValues.setL(l);

        return inputValues;
    }

    private int parseConfiguration(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {

        }

        return 0;
    }

    private void createConfiguration() {
        if (graph != null) {
            algorithm = new FruchtermanReingold(createTransferObject());
            Warning.setText("");
        } else {
            Warning.setText("WARNING!!! \n Add graph");
        }
    }

    private void initMenu() {
        List<Menu> menus = MenuBar.getMenus();

        for (Menu menu : menus) {
            List<MenuItem> items = menu.getItems();
            for (MenuItem item : items) {
                if (Objects.equals(item.getText(), "Open")) {
                    item.setOnAction(actionEvent -> {
                        iterator = 0;
                        graphDiagram.getChildren().clear();
                        File file = fileChooser.showOpenDialog(stageForFile);
                        initGraph(file);
                    });
                }
            }
        }
    }

    private void startForceAlgorithm(int count) {
        algorithm.start(count);
    }

    private List<Circle> createVertexes(List<Vertex> vertexes) {
        List<Circle> circles = new ArrayList<>();

        for (Vertex vertex : vertexes) {
            if (!vertex.isGravity()) {
                Circle circle = new Circle();
                circle.setRadius(10);
                circle.setFill(Paint.valueOf("#904848"));
                circle.setCenterX(vertex.getPosition().getX());
                circle.setCenterY(vertex.getPosition().getY());
                circle.setOnMouseClicked(mouseEvent -> {
                    System.out.println(vertex.getPosition().getX());
                    System.out.println(vertex.getPosition().getY());
                });

                circles.add(circle);
            }
        }

        return circles;
    }

    private List<Line> createEdges(List<Edge> edges) {
        List<Line> lines = new ArrayList<>();

        for (Edge edge : edges) {
            if (!edge.getFirstVertex().isGravity()  && !edge.getSecondVertex().isGravity()) {
                Line line = new Line(
                        edge.getFirstVertex().getPosition().getX(),
                        edge.getFirstVertex().getPosition().getY(),
                        edge.getSecondVertex().getPosition().getX(),
                        edge.getSecondVertex().getPosition().getY()
                );
                line.setStrokeWidth(4);
                line.setOnMouseClicked(mouseEvent -> {
                    System.out.println(calculateRemoteness(
                            edge.getFirstVertex().getPosition(),
                            edge.getSecondVertex().getPosition()
                    ));
                });

                lines.add(line);
            }
        }

        return lines;
    }

    private double calculateRemoteness(Position position, Position position1) {
        double x = position.getX();
        double x1 = position1.getX();
        double y = position.getY();
        double y1 = position1.getY();

        return Math.sqrt((Math.abs(x - x1) * Math.abs(x - x1)) +
                (Math.abs(y - y1) * Math.abs(y - y1)));
    }
}
