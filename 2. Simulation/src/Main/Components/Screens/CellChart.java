package Main.Components.Screens;

import Main.Components.Cell.Cell;
import Main.Main;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

/**
 * @author Samantha Whitt
 * This class creates a line chart that visually updates the number of cells of each state for whichever simulation
 * (not finished and needs real data -- list of all the cell lists to grab size)
 */
public class CellChart {
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private LineChart<String,Number> myChart;
    private final int CHART_WIDTH = 15;

    /**
     * constructor for cell chart that setups a basic xy line chart
     */
    // List<List<Cell>> data
    public CellChart() {
        setupChart();
    }

    /**
     * updates the cell chart every step to reflect the appropriate number of the cells of each state
     * @return realtime line chart
     */
    public LineChart<String, Number> updateCellChart() {
        return myChart;
    }

    /**
     * @return line chart
     */
    public LineChart<String, Number> getCellChart() {
        return myChart;
    }

    /**
     * sets x location of the line chart on the scene
     * @param x
     */
    public void setX(double x) {
        myChart.setLayoutX(x);
    }

    /**
     * sets y location of the line chart on the scene
     * @param y
     */
    public void setY(double y) {
        myChart.setLayoutY(y);
    }

    /**
     * sets up the chart based on a xy axis
     * (not finished -- needs real data and then will be updated appropriately)
     */
    private void setupChart() {
        myChart = new LineChart<>(xAxis, yAxis);
        xAxis.setLabel(Main.chartInfo.getString("x-axis"));
        xAxis.setAnimated(false);
        yAxis.setLabel(Main.chartInfo.getString("y-axis"));
        yAxis.setAnimated(false);

        myChart.setTitle(Main.chartInfo.getString("title"));
        myChart.setAnimated(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data Series");

        myChart.getData().add(series);
    }
}
