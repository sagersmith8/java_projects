package polygon;
import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.util.ArrayList;

import model.ObjLoad;

public class WorldWindTest4 extends ApplicationTemplate
{
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
        public AppFrame()
        {
            super(true, true, false);

            //Enable shape dragging, if you want.
            this.getWwd().addSelectListener(new BasicDragger(this.getWwd()));

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
            ObjLoad ol = new ObjLoad("");
            ExtrudedPolygon poly2 = new ExtrudedPolygon();

            //Set Altitude
            poly2.setAttributes(normalAttributes);
            //Tooltip text of the polygon
            poly2.setValue(AVKey.DISPLAY_NAME, "My first polygon"); 
            //Add the just created polygon to a renderable layer
            layer.addRenderable(poly2);
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
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 54);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 13);
        ApplicationTemplate.start("NASA World Wind Tutorial - Simple Polygons", AppFrame.class);
    }
}
