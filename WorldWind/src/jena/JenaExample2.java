package jena;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JenaExample2 extends ApplicationTemplate
{
	public static class AppFrame extends ApplicationTemplate.AppFrame
	{
		public AppFrame() throws FileNotFoundException
		{
			super(true, true, false);

			//Creating the renderable layer
			RenderableLayer layer = new RenderableLayer();                       
			//Creating an instance of our class DataLoader            
			DataLoader dataLoader = new DataLoader();            
			//Creating an array of our predefined type GeometryRecord
			ArrayList geometryRecord = new ArrayList();             
			//Calling the function queryStates to execute our predefined SPARQL Query
			geometryRecord = dataLoader.queryStates();
			//Iterating the SPARQL result and adding its records into a Positions array 
			for (int i = 0; i < geometryRecord.size(); i++) {

				ArrayList<Position> borderPositions = new ArrayList<Position>();
				//Splitting every coordinate, which in our dataset is separated by ";"
				String latlong[] = ((GeometryRecord) geometryRecord.get(i)).getGeometry().split(";");
				//Getting the area of each municipality for further use
				long area = ((GeometryRecord) geometryRecord.get(i)).getMunicipalityArea();

				for(String str: latlong){

					//Splitting the latitude / longitude, which in our dataset is separated by ","
					String latlong2[] = str.split(",");     
					//Adding the latitude and longitude to the Positions array
					borderPositions.add(Position.fromDegrees(Double.parseDouble(latlong2[1]), 
							Double.parseDouble(latlong2[0]),1e4));
				}

				//Creating a new Polygon and its attributes                                     
				Polygon polygon = new Polygon(borderPositions);                 
				ShapeAttributes sideAttributes = new BasicShapeAttributes();

				//Classifying the polygons due its area size, giving them different colors
				if (area < 300 )
				{
					sideAttributes.setInteriorMaterial(Material.RED);
				} 
				else if (area >= 300 && area < 500 )
				{
					sideAttributes.setInteriorMaterial(Material.WHITE);
				} 
				else if (area >= 500 && area < 800)
				{
					sideAttributes.setInteriorMaterial(Material.YELLOW);
				}
				else if (area >= 800 && area < 1000)
				{
					sideAttributes.setInteriorMaterial(Material.GREEN);
				}
				else if (area >= 1000) 
				{
					sideAttributes.setInteriorMaterial(Material.BLUE);
				}

				//Setting polygon's attributes
				sideAttributes.setOutlineOpacity(0.5);
				sideAttributes.setInteriorOpacity(0.5);
				sideAttributes.setOutlineWidth(2);
				sideAttributes.setDrawOutline(true);
				sideAttributes.setDrawInterior(true);                                
				polygon.setAttributes(sideAttributes);

				//Creating a tooltip text for each polygon, containing their name and area
				polygon.setValue(AVKey.DISPLAY_NAME, ((GeometryRecord) geometryRecord.get(i)).getMunicipalityName() + 
						" - " + ((GeometryRecord) geometryRecord.get(i)).getMunicipalityArea() + "km" );
				//Adding the polygon to the renderable layer
				layer.addRenderable(polygon);
			}

			// Add the layer to the model.
			insertBeforeCompass(getWwd(), layer);
			// Update layer panel
			this.getLayerPanel().update(this.getWwd());            
		}
	}
	public static void main(String[] args)
	{
		Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);

		ApplicationTemplate.start("NASA World Wind Tutorial - Plotting LOD Geographic Datasets", AppFrame.class);
	}   
}
