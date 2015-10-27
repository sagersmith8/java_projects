package polyline;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import java.util.ArrayList;

public class LinesExample extends ApplicationTemplate
{
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
        public AppFrame()
        {
            super(true, true, false);

            // Enable shape dragging
            this.getWwd().addSelectListener(new BasicDragger(this.getWwd()));

            //Create the layer where you will place your polygons
            RenderableLayer layer = new RenderableLayer();

            // Set the coordinates (in degrees) to draw your polyline
            // To radians just change the method the class Position
            // to fromRadians().
            ArrayList positions = new ArrayList();
            positions.add(Position.fromDegrees(52, 8));
            positions.add(Position.fromDegrees(52, 13));
            positions.add(Position.fromDegrees(50, 19));
            positions.add(Position.fromDegrees(49, 19));

            Polyline polyline = new Polyline(positions,3e4);            

            polyline.setColor(getBackground().ORANGE);
            polyline.setLineWidth(3);

            //Tooltip text of the polygon
            polyline.setValue(AVKey.DISPLAY_NAME, "My first polyline"); 
            //Add the just created polygon to a renderable layer
            layer.addRenderable(polyline);
            // Add the layer to the model.
            insertBeforeCompass(getWwd(), layer);
            // Update layer panel
            this.getLayerPanel().update(this.getWwd());

        }
    }

    public static void main(String[] args)
    {
        //Set the initial configurations of your NASA World Wind App
        //Altitute, logitude and latitute, and window caption. 
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 51);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 16);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 160e4);
        ApplicationTemplate.start("NASA World Wind Tutorial - Simple Polyline",
            AppFrame.class);
    }
}