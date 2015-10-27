package polygon;
import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AbstractGeneralShape;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.util.ArrayList;

import model.ObjLoad;

public class WorldWindTest3 extends ApplicationTemplate
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
            normalAttributes.setOutlineWidth(2);           
            normalAttributes.setOutlineOpacity(0.5);
            normalAttributes.setDrawInterior(true);
            normalAttributes.setDrawOutline(true);

            ObjLoad l = new ObjLoad();
            Shapefile shap = new Shapefile(l.parseTriangles("models/The DarkNight.obj"));
            layer.addRenderable(null);
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
        ApplicationTemplate.start("NASA World Wind Tutorial - Simple Polygons", 
AppFrame.class);
    }
}
