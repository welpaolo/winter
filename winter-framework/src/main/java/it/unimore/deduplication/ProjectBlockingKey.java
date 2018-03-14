package it.unimore.deduplication;

import de.uni_mannheim.informatik.dws.winter.matching.blockers.generators.RecordBlockingKeyGenerator;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.Pair;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Record;
import de.uni_mannheim.informatik.dws.winter.processing.DataIterator;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;
import it.unimore.deduplication.model.Project;

public class ProjectBlockingKey extends RecordBlockingKeyGenerator<Record, Attribute> {
    @Override
    public void generateBlockingKeys(Record record, Processable<Correspondence<Attribute, Matchable>> correspondences, DataIterator<Pair<String, Record>> resultCollector) {

        String year = null;

        if (record.hasValue(Project.YEAR)) {
            year = record.getValue(Project.YEAR);
//            System.out.println("Case 1: " + year);

        } else {
//            System.out.println("Case 2: ");
            if (record.hasValue(Project.START_DATE)) {
//                System.out.println("Case 2.1 ");
                String date = record.getValue(Project.START_DATE);
                String[] comp = date.split(" ");
                if (comp.length >= 1) {
                    year = comp[comp.length - 1];
                } else {
                    System.out.println("Error startdate: " + date);
                    year = "XXXX";
                }
            } else {
//                System.out.println("Case 2.2 ");
//                System.out.println("Record: " + record.toString());
                year = "XXXX";
            }
        }


        if (year.length() != 4) {
            System.err.println("ERROR into year field: " + year);
        }

//        System.out.println("Key: "+ year + " record: "+ record.toString());

        resultCollector.next(new Pair<>(year, record));

    }
}
