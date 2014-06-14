/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.cinematic.gui.jfx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author MAYANK
 */
public class TimelineUI extends VBox {

    @FXML
    private Slider timebar;
    @FXML
    private StackPane timelineStackPane;
    @FXML
    private ScrollPane timelineScrollPane;
    @FXML
    private VBox timelineVBox;
    @FXML
    private Separator timeslider;   
    
    DoubleProperty duration = new SimpleDoubleProperty();
    DoubleProperty currentTime = new SimpleDoubleProperty();
    public TimelineUI() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("timeline_ui.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        initListeners();
        
    }

    public void test() {
        System.out.println("Connected to TimelineUI");
    }
    /*********** Listeners ***************/
    public final void initListeners() {
        /**** bind timelineScrollPane horizontal scrollbar to change timebar's horizontal position 
         **** so that the conttents of timelineVBox and timebar move together when scrollbar is used
         ****/
        timelineScrollPane.hvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                double extraDistance = timebar.getPrefWidth() - timelineScrollPane.getPrefWidth();
                double timebarHVal = -1 * t1.doubleValue() * extraDistance;
                double timesliderRefVal =  (timelineVBox.getWidth()/timebar.getMax())*currentTime.doubleValue();
                double timesliderHVal = timebarHVal + timesliderRefVal;
                timebar.setLayoutX(timebarHVal);
                timeslider.setLayoutX(timesliderHVal);
            }
        });
        
        /***** current time changes timebar's value *****/
        timebar.valueProperty().bindBidirectional(currentTime);
        
        /**** currentTime changes timeslider's x position *****/
        currentTime.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                double newPos = (timelineVBox.getWidth() / timebar.getMax()) * t1.doubleValue();
                timeslider.setLayoutX(newPos);
                
            }
        });
        

        timeslider.setOnDragDetected(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //System.out.println("Deag Detected");
                //timeslider.setLayoutX(t.getX());
            }
        
        });
        timeslider.setOnDragDetected(new EventHandler<MouseEvent>(){
            double timesliderRefPos = timeslider.getLayoutX();
            @Override
            public void handle(MouseEvent t) {
                System.out.println("Dragging");
                currentTime.setValue(currentTime.getValue()+(t.getX()/timelineVBox.getWidth())*timebar.getMax() );
                System.out.println("ref pos : " + timesliderRefPos + " moved : " + t.getX());
            }
        
        });
      
    }
    
    
    /***************** DURATION ******************/
    public void setDuration(double duration)
    {
        timebar.setMax(duration);
    }
    public double getDuration()
    {
        return timebar.getMax();
    }
    public DoubleProperty getCurrentTime() {
        return currentTime;
    }
}

