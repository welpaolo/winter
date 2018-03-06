package it.unimore.deduplication;

import de.uni_mannheim.informatik.dws.winter.matching.MatchingEngine;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.StandardRecordBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.rules.LinearCombinationMatchingRule;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.DataSet;
import de.uni_mannheim.informatik.dws.winter.model.HashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.CSVRecordReader;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Record;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.comparators.RecordComparatorLevenshtein;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class OrganizationDeduplication {


    public static void main(String arg[]) {
        System.out.println("Start!");

        // DATASETS
        DataSet<Record, Attribute> openaire = new HashedDataSet<>();
        DataSet<Record, Attribute> cercauniversita = new HashedDataSet<>();
        DataSet<Record, Attribute> arianna = new HashedDataSet<>();
        DataSet<Record, Attribute> questio = new HashedDataSet<>();
        DataSet<Record, Attribute> bvd = new HashedDataSet<>();
        DataSet<Record, Attribute> cnr = new HashedDataSet<>();

        DataSet<Record, Attribute> all = new HashedDataSet<>();




        File cercaUniversitaFile = new File("/Users/paolosottovia/Downloads/researchAlpsCsvs/organizations_CercaUniversita.csv");

        File openaireFile = new File("/Users/paolosottovia/Downloads/researchAlpsCsvs/organizations_OpenAire.csv");


        File questioFile = new File("/Users/paolosottovia/Downloads/researchAlpsCsvs/organizations_Questio.csv");

        File ariannaFile = new File("/Users/paolosottovia/Downloads/researchAlpsCsvs/organizations_Arianna - Anagrafe Nazionale delle Ricerche.csv");

        File dvbFile = new File("/Users/paolosottovia/Downloads/researchAlpsCsvs/organizations_Bureau van Dijk.csv");

        File cnrFile = new File("/Users/paolosottovia/Downloads/researchAlpsCsvs/organizations_Consiglio Nazionale delle Ricerche (CNR).csv");

        //read data from csv Files
        try {
            new CSVRecordReader(-1).loadFromCSV(
                    cercaUniversitaFile,
                    cercauniversita);
            new CSVRecordReader(-1).loadFromCSV(
                    openaireFile, openaire);

            new CSVRecordReader(-1).loadFromCSV(
                    questioFile, questio);

            new CSVRecordReader(-1).loadFromCSV(
                    ariannaFile, arianna);

            new CSVRecordReader(-1).loadFromCSV(
                    dvbFile, bvd);

            new CSVRecordReader(-1).loadFromCSV(
                    cnrFile, cnr);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Number of items in Openaire: " + openaire.size());
        System.out.println("Number of items in cerca universita: " + cercauniversita.size());
        System.out.println("Number of items in questio: " + questio.size());
        System.out.println("Number of items in arianna: " + arianna.size());
        System.out.println("Number of items in bvd: " + bvd.size());
        System.out.println("Number of items in cnr: " + bvd.size());

        List<DataSet<Record, Attribute>> allDatasets = new ArrayList<>();

        allDatasets.add(openaire);
        allDatasets.add(cercauniversita);
        allDatasets.add(questio);
        allDatasets.add(arianna);
        allDatasets.add(bvd);
        allDatasets.add(cnr);

//        File openAireFile = new File("/Users/paolosottovia/Downloads/researchAlpsCsvs/organizations_CercaUniversita_copy.csv");


        Attribute labelOpenAire = new Attribute("label");
        Attribute labelCercaUniversita = new Attribute("label");

        LinearCombinationMatchingRule<Record, Attribute> matchingRule = new LinearCombinationMatchingRule<>(0.75);
        // add comparators


        Record r = openaire.getRandomRecord();

        boolean contains = r.getValues().containsKey(labelOpenAire);
        System.out.println("Contains value: " + contains);

        System.out.println("Values: " + r.getValues().keySet().toString());


        System.out.println("VALUE: " + r.getValue(labelOpenAire));

        System.out.println(r.toString());

        System.out.println("Indentifier: " + r.getIdentifier());
        System.out.println("provenance: " + r.getProvenance());

        DataSet<Attribute, Attribute> attributes = openaire.getSchema();
        Attribute a = attributes.getRandomRecord();
        System.out.println(a.toString());

        StandardRecordBlocker<Record, Attribute> blocker = new StandardRecordBlocker<>(new OrganizationBlockingKey());

        Graph<String, DefaultEdge> g
                = new SimpleGraph<>(DefaultEdge.class);


        try {

            // RecordComparatorLevenshtein lab = new
            // RecordComparatorLevenshtein(Organization.LABEL, Organization.LABEL);
            RecordComparatorLevenshtein lab = new RecordComparatorLevenshtein(labelOpenAire, labelCercaUniversita);
            lab.setLowerCase(true);
            matchingRule.addComparator(lab, 0.6d);
            //


            RecordComparatorLevenshtein city = new RecordComparatorLevenshtein(Organization.CITY, Organization.CITY);
            city.setLowerCase(true);
//           matchingRule.addComparator(city, 0.2d);


            RecordComparatorLevenshtein link = new RecordComparatorLevenshtein(Organization.LINK, Organization.LINK);
            link.setLowerCase(true);
//            matchingRule.addComparator(link, 0.2d);
//			RecordComparatorLevenshtein url = new RecordComparatorLevenshtein(Organization.LINK, Organization.LINK);
//			matchingRule.addComparator(url, 0.3d);


            // Initialize Matching Engine
            Collection<Correspondence<Record, Attribute>> allCorrespondances = new ArrayList<>();


            Attribute LABEL = new Attribute("label");
            Attribute ADDRESS = new Attribute("address");
            Attribute CITY = new Attribute("city");
            Attribute LINKS = new Attribute("links");

            for (int i = 0; i < allDatasets.size(); i++) {
                for (int j = i; j < allDatasets.size(); j++) {

                    if (i == j) {
                        continue;
                    }

                    DataSet<Record, Attribute> dataSet1 = allDatasets.get(i);
                    DataSet<Record, Attribute> dataSet2 = allDatasets.get(j);

                    System.out.println("Dataset1 size: " + dataSet1.size());
                    System.out.println("Dataset2 size: " + dataSet2.size());

                    MatchingEngine<Record, Attribute> engine = new MatchingEngine<>();
//                    Processable<Correspondence<Record, Attribute>> correspondences = engine.runIdentityResolution(openaire,
//                            bvd, null, matchingRule, blocker);

                    Processable<Correspondence<Record, Attribute>> correspondences = engine.runIdentityResolution(dataSet1,
                            dataSet2, null, matchingRule, blocker);

                    System.out.println("Number of correspondences: " + correspondences.size());


                    Collection<Correspondence<Record, Attribute>> corresp = correspondences.get();
                    allCorrespondances.addAll(corresp);


                    for (Correspondence<Record, Attribute> corr : corresp) {
                        Record record1 = corr.getFirstRecord();
                        Record record2 = corr.getSecondRecord();

                        double sim = corr.getSimilarityScore();

                        System.out.println("\n\n--------------------------------------------------------------------");
                        System.out.println("R1: " + record1.toString());
                        System.out.println("R2: " + record2.toString());
                        System.out.println("score: " + sim);

                        System.out.println("LABEL\tADDRESS\tCITY\tLINKS");
                        System.out.println(record1.getValue(LABEL) + "\t" + record1.getValue(ADDRESS) + "\t" + record1.getValue(CITY) + "\t" + record1.getValue(LINKS));
                        System.out.println(record2.getValue(LABEL) + "\t" + record2.getValue(ADDRESS) + "\t" + record2.getValue(CITY) + "\t" + record2.getValue(LINKS));

                    }


                    System.out.println("\n\nNumber of step correspondences: " + correspondences.size());

                }
            }


            System.out.println("\n\n\n NUMBER of ALL correspondences: " + allCorrespondances.size());


            Map<String, Set<Record>> ids = new HashMap<>();


            Set<Set<String>> connectedComponents = new HashSet<>();

            Map<String, Record> idRecords = new HashMap<>();
            Set<String> allIds = new HashSet<>();

            //Given all the correspondences create a graph
            //Nodes are the entities
            //Edges are the correspondences
            Attribute attributeId = new Attribute("id");
            for (Correspondence<Record, Attribute> corr : allCorrespondances) {

                Record r1 = corr.getFirstRecord();
                Record r2 = corr.getSecondRecord();

                String id1 = r1.getValue(attributeId);
                String id2 = r2.getValue(attributeId);

                g.addVertex(id1);
                g.addVertex(id2);
                g.addEdge(id1, id2);


                allIds.add(id1);
                allIds.add(id2);

                idRecords.put(id1, r1);
                idRecords.put(id2, r2);

                boolean inserted = false;


                for (Set<String> component : connectedComponents) {

                    if (component.contains(id1) || component.contains(id2)) {
                        component.add(id1);
                        component.add(id2);
                        inserted = true;
                        break;
                    }
                }

                if (!inserted) {
                    Set<String> idSet = new HashSet<>();
                    idSet.add(id1);
                    idSet.add(id2);
                    connectedComponents.add(idSet);
                }


            }


            System.out.println("Number of edges: " + g.edgeSet().size());
            System.out.println("Number of nodes: " + g.vertexSet().size());
            ConnectivityInspector<String, DefaultEdge> c = new ConnectivityInspector<String, DefaultEdge>((UndirectedGraph<String, DefaultEdge>) g);

            //Connected components represent the set of entities that represent the same real-wold entity
            List<Set<String>> connectedComponentsCleaned = c.connectedSets();
            System.out.println("Number of connected components: " + connectedComponentsCleaned.size());


            for (Set<String> set : connectedComponentsCleaned) {

                System.out.println("\n\n\n===============================================================");
                System.out.println("LABEL\tADDRESS\tCITY\tLINKS");

                for (String id : set) {
                    Record record = idRecords.get(id);
                    System.out.println(record.getValue(LABEL) + "\t" + record.getValue(ADDRESS) + "\t" + record.getValue(CITY) + "\t" + record.getValue(LINKS));

                }


            }
            printCorrespondence("/Users/paolosottovia/Downloads/researchAlpsCsvs/correspondence.tsv", connectedComponentsCleaned);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public static void printCorrespondence(String filename, Collection<Set<String>> correspondence) {
        BufferedWriter writer = null;
        try {

            File logFile = new File(filename);

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));

            for (Set<String> corr : correspondence) {
                String line = "";

                for (String c : corr) {
                    line += c + "\t";
                }
                line = line.substring(0, line.length() - 1);

                writer.write(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
