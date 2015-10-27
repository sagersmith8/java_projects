package jena;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class DataLoader {

    public ArrayList queryStates() throws FileNotFoundException {
            //Defining an array list of type GeometryRecord
            ArrayList geometryRecord = new ArrayList();
            //Createing a new model
            Model model = ModelFactory.createDefaultModel();
            //Setting the dataset directory
            File dir = new File("turtleFiles/jena2/");
            //Loading all files from the dataset directory
            File[] fileList = dir.listFiles();
            for (int i = 0; i < fileList.length; i++) 
            {
                InputStream in = null;
                in = new FileInputStream(fileList[i]);
                System.out.println("Loading:" + fileList[i]);
                if(in != null)
                {
                 model.read(in, null, "TURTLE");
                }
             }

            //Defining the SPARQL Query for the loaded datasets
            String sparqlQuery = 
                      "  prefix brazil:  <http://brazil.municipalities.br/pernambuco/>   "   
                    + "  prefix xsd:     <http://www.w3.org/2001/XMLSchema#> " 
                    + "  prefix tisc:    <http://observedchange.com/tisc/ns#>  " 
                    + "  prefix foaf:          <http://xmlns.com/foaf/0.1/> " 
                    + "  prefix dbpedia: <http://dbpedia.org/resource/> " 
                    + "  prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " 
                    + " SELECT ?municipalityName ?municipalityArea ?geometry WHERE { "
                    + "     ?municipalityCode rdf:type dbpedia:Municipalities_of_Brazil ." 
                    + "     ?municipalityCode foaf:name ?municipalityName ." 
                    + "     ?municipalityCode tisc:geometry ?geometry ." 
                    + "     ?municipalityCode tisc:areasize ?municipalityArea ."
                    + " }  ";

            //Executing SPARQL query
            Query query = QueryFactory.create(sparqlQuery);
            ARQ.getContext().setTrue(ARQ.useSAX);
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
            //Storing the SPARQL Query result into a ResultSet.
            ResultSet results = qexec.execSelect();
            //Iterating over the ResultSet and adding each record into our 
            //GeometryRecord array list.
            while (results.hasNext()) {
                    QuerySolution soln = results.nextSolution();

                    GeometryRecord tmpLODRecord = new GeometryRecord(
                            soln.getLiteral("municipalityName").toString().substring(0, 
                                soln.getLiteral("municipalityName").toString().indexOf("^^")), 
                            soln.getLiteral("municipalityArea").getLong(), 
                            soln.getLiteral("geometry").getString());

                    geometryRecord.add(tmpLODRecord);
            }

            qexec.close();
            //Returning an array list with our query result elements.
            return geometryRecord;
    }
}
