package ontologyapi;

import java.io.InputStream;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.hp.hpl.jena.*;

/**
 * Task 06: Modifying ontologies (RDFs)
 * @author elozano
 *
 */

//Christian Guaman Cocha
public class Task06
{
	public static String ns = "http://somewhere#";
	public static String foafNS = "http://xmlns.com/foaf/0.1#";
	public static String foafEmailURI = foafNS+"email";
	public static String foafKnowsURI = foafNS+"knows";
	public static String stringTypeURI = "http://www.w3.org/2001/XMLSchema#string";
	
	public static void main(String args[])
	{
		String filename = "example5.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		// Create a new class named "Researcher"
		OntClass researcher = model.createClass(ns+"Researcher");
		
		// ** TASK 6.1: Create a new class named "University" **
		OntClass uneversity = model.createClass(ns+"Unersity");
		
		// ** TASK 6.2: Add "Researcher" as a subclass of "Person" **
		OntClass person = model.createClass();
		
		person.addSubClass(researcher);
				
		// ** TASK 6.3: Create a new property named "worksIn" **
		Property worksIn = model.createProperty(ns+"WorksIn");
		
		// ** TASK 6.4: Create a new individual of Researcher named "Jane Smith" **
		
		Individual janeSmith = researcher.createIndividual(ns+"Jane Smith");
		
		// ** TASK 6.5: Add to the individual JaneSmith the fullName, given and family names **
		
		janeSmith.addProperty(VCARD.FN,"JaneSmith");
		janeSmith.addProperty(VCARD.Given,"Jane" );
		janeSmith.addProperty(VCARD.Family, "Smith");
		
		// ** TASK 6.6: Add UPM as the university where John Smith works **
		
		Individual upm = uneversity.createIndividual(ns+"UPM");
		Individual jonhSmith = uneversity.createIndividual(ns+"John Smith");
		
		jonhSmith.addProperty(worksIn, upm);
		
		model.write(System.out, "TURTLE");
	}
}
