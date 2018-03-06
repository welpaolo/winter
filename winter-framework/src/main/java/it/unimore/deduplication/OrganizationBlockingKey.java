package it.unimore.deduplication;

import de.uni_mannheim.informatik.dws.winter.matching.blockers.generators.RecordBlockingKeyGenerator;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.Matchable;
import de.uni_mannheim.informatik.dws.winter.model.Pair;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Record;
import de.uni_mannheim.informatik.dws.winter.processing.DataIterator;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;

public class OrganizationBlockingKey extends RecordBlockingKeyGenerator<Record, Attribute> {

    @Override
    public void generateBlockingKeys(Record record, Processable<Correspondence<Attribute, Matchable>> correspondences,
                                     DataIterator<Pair<String, Record>> resultCollector) {
        // TODO Auto-generated method stub

        String postcode = null;

        if (record.hasValue(Organization.POSTCODE)) {
            postcode = record.getValue(Organization.POSTCODE);

            if (postcode.length() < 5) {
//                System.out.println("Postcode malformed: "+postcode);

                for (int i = 0; i < (5 - postcode.length()); i++) {
                    postcode = "0" + postcode;
                }


            } else if (postcode.length() > 5) {
                System.err.println("ERROR POSTCODE: " + postcode);
            } else {

            }
            //
            //

            if (postcode.length() >= 4) {
                postcode = postcode.substring(0, 4) + "X";
            }
//            System.out.println("NEW POSTCODE: " + postcode);


        } else {

            postcode = "00000";
        }

        // just for test matches
        //postcode = "00000";

        String bkv = postcode;
        resultCollector.next(new Pair<>((bkv), record));

    }

}
