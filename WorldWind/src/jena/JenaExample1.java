package jena;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class JenaExample1
{
    public static void main(String[] args) throws FileNotFoundException 
    {
    	JenaExample1 jenaTest = new JenaExample1();
        jenaTest.listTriples();    
    }

    public void listTriples() throws FileNotFoundException
    {
        //Creates a model
        Model model = ModelFactory.createDefaultModel();  
        File dir = new File("turtleFiles/jena1/");
        File[] fileList = dir.listFiles();
        for (File f : fileList)
        {
        	InputStream in = new FileInputStream(f);                                                         
        	if(in != null)
        	{
        		model.write(new FileOutputStream(f), "TURTLE");
        	}
        }

        //Create the SPARQL Query 
        String sparqlQueryString =
                        "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
                        "PREFIX ifgi: <http://ifgi.uni-muenster.de/> " +
                        " " +
                        "SELECT ?friends WHERE { " +
                        "       ifgi:jones foaf:knows ?friends . " +
                        "}";

        Query query = QueryFactory.create(sparqlQueryString);
        ARQ.getContext().setTrue(ARQ.useSAX);        

        //Executing the created query and stores its results in a ResultSet
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        //Iterating the ResultSet to get all its elements 
        while (results.hasNext()) {

            QuerySolution soln = results.nextSolution();   
            //Displaying on the screen the value of the variable
            //used in the SPARQL Query.
            System.out.println(soln.get("friends"));
        }
        qexec.close();
    }
}
