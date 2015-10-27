package game;
import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.LatLonGraticuleLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.util.ArrayList;

import polygon.WorldWindTest4.AppFrame;

public class FlightSimulator extends ApplicationTemplate
{
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
        public AppFrame()
        {
            super(true, true, false);
          //Create the layer where you will place your polygons
            RenderableLayer layer = new RenderableLayer();

            // Set the basic attributes of your polygon
            ShapeAttributes normalAttributes = new BasicShapeAttributes();
            normalAttributes.setInteriorMaterial(Material.YELLOW);
            normalAttributes.setOutlineMaterial(Material.RED);
            normalAttributes.setOutlineWidth(2);           
            normalAttributes.setOutlineOpacity(0.5);
            normalAttributes.setDrawInterior(true);
            normalAttributes.setDrawOutline(true);

            // Set the coordinates (in degrees) to draw your polygon
            // To radians just change the method the class Position
            // to fromRadians().
            ArrayList<Position> positions = new ArrayList<Position>();
            positions.add(Position.fromDegrees(52, 10, 5e4));
            positions.add(Position.fromDegrees(55, 11, 15e4));
            positions.add(Position.fromDegrees(55, 11, 15e4));
            positions.add(Position.fromDegrees(52, 14, 15e4));
            positions.add(Position.fromDegrees(52, 10, 5e4));

            ExtrudedPolygon poly = new ExtrudedPolygon(positions);

            poly.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            //Set Altitude
            poly.setAttributes(normalAttributes);
            //Tooltip text of the polygon
            poly.setValue(AVKey.DISPLAY_NAME, "My first polygon"); 
            //Add the just created polygon to a renderable layer
            layer.addRenderable(poly);
            // Add the layer to the model.
            insertBeforeCompass(getWwd(), layer);
            // Add graticule
            insertBeforePlacenames(this.getWwd(), new LatLonGraticuleLayer());
            
        }
    }

    public static void main(String[] args)
    {
    	 //Set the initial configurations of your NASA World Wind App
        //Altitute, logitude and latitute, and window caption.
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 54);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 13);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE,AVKey.ABOVE_GROUND_LEVEL);
        Configuration.setValue(AVKey.INITIAL_PITCH,AVKey.HORIZONTAL);
        ApplicationTemplate.start("FlightSimulator", AppFrame.class);
    }
}