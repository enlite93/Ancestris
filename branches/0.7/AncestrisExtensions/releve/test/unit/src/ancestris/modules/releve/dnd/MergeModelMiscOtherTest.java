package ancestris.modules.releve.dnd;

import ancestris.modules.releve.TestUtility;
import ancestris.modules.releve.model.FieldPlace;
import ancestris.modules.releve.model.RecordMisc;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.TagPath;
import java.util.List;
import javax.swing.JFrame;
import junit.framework.TestCase;

/**
 *
 * @author Michel
 */
public class MergeModelMiscOtherTest extends TestCase {

     static public FieldPlace getRecordsInfoPlace() {
        FieldPlace recordsInfoPlace = new FieldPlace();
        recordsInfoPlace.setValue("ville_marc,code_marc,departement_marc,region_marc,pays_marc");
        return recordsInfoPlace;
    }

    public static RecordMisc createMiscOtherRecord(String id) {
        RecordMisc record = new RecordMisc();
        if (id.equals("Accord1")) {
            record.setEventDate("01/03/1999");
            record.setEventType("Accord ");
            record.setNotary("notaire_other");
            record.setCote("cote");
            record.setGeneralComment("generalcomment");
            record.setFreeComment("photo");
            record.setIndi("accordfirstname", "ACCORDLASTNAME", "M", "50", "", "accordBirthplace", "accordoccupation", "accordResidence", "accordcomment");

            // je place l'epouse ne premier et l'epoux en second
            record.setWife("Fatherfirstname", "FATHERLASTNAME", "M", "", "", "", "fatherOccupation2", "fatherResidence2", "fatherComment2");
            record.setWifeMarried("Motherfirstname", "MOTHERLASTNAME", "wifeoccupation2", "wifeResidence2", "wifecomment2", "true");
        } else {
            record.setEventDate("01/03/1999");
            record.setEventType("Accord ");
            record.setNotary("notaire_other");
            record.setCote("cote");
            record.setGeneralComment("generalcomment");
            record.setFreeComment("photo");
            record.setIndi("accordfirstname", "ACCORDLASTNAME", "M", "50", "", "accordBirthplace", "accordoccupation", "accordResidence", "accordcomment");

            // je place l'epouse ne premier et l'epoux en second
            record.setWife("Motherfirstname", "MOTHERLASTNAME", "F", "", "", "", "wifeoccupation2", "wifeResidence2", "wifecomment2");
            record.setWifeMarried("Fatherfirstname", "FATHERLASTNAME", "fatherOccupation2", "fatherResidence2", "fatherComment", "true");
        }
        return record;
    }

    /**
     *
    */
    public void testAddOther() {
        try {
            Gedcom gedcom = TestUtility.createGedcom();


            RecordMisc miscRecord = createMiscOtherRecord("Accord1");
            String sourceTitle = "";
            MergeRecord mergeRecord = new MergeRecord(getRecordsInfoPlace(), sourceTitle, miscRecord);

            Fam participant2Family;
            Indi participant2Wife;
            Indi participant2Husband;

            List<MergeModel> models = MergeModel.createMergeModel(mergeRecord, gedcom, null);
            assertEquals("Nombre model",3,models.size());

            MergeDialog dialog = MergeDialog.show(new JFrame(), gedcom, null, mergeRecord, false);

            //TestUtility.waitForDialogClose(dialog);

            //models.get(0).copyRecordToEntity();
            dialog.copyRecordToEntity();

            Indi participant1 = (Indi) gedcom.getEntity("I00007");
            assertEquals("Lien event vers source","@S00004@", participant1.getValue(new TagPath("INDI:EVEN:SOUR"),""));
            assertEquals("Source event","S00004", gedcom.getEntity(participant1.getValue(new TagPath("INDI:EVEN:SOUR"),"").replaceAll("@", "")).getId());
            assertEquals("Source event",miscRecord.getCote().getValue() + ", " +miscRecord.getFreeComment().getValue(), participant1.getValue(new TagPath("INDI:EVEN:SOUR:PAGE"),""));
            assertEquals("Date event",miscRecord.getEventDateProperty().getValue(), participant1.getValue(new TagPath("INDI:EVEN:DATE"),""));
            assertEquals("Lieu event",getRecordsInfoPlace().getValue(), participant1.getValue(new TagPath("INDI:EVEN:PLAC"),""));
            
            assertEquals("participant1 : Date naissance",mergeRecord.getIndi().getBirthDate().getValue(), participant1.getBirthDate().getValue());
            assertNotSame("participant1 : lieu naissance",mergeRecord.getIndi().getBirthPlace(), participant1.getValue(new TagPath("INDI:BIRT:PLAC"), ""));

            assertEquals("participant1 : Profession",1, participant1.getProperties(new TagPath("INDI:OCCU")).length);
            Property occupation = participant1.getProperties(new TagPath("INDI:OCCU"))[0];
            assertEquals("participant1 : Profession",mergeRecord.getIndi().getOccupation(), occupation.getValue(new TagPath("OCCU"),""));
            assertEquals("participant1 : Date Profession",mergeRecord.getEventDate().getValue(), occupation.getValue(new TagPath("OCCU:DATE"),""));
            assertEquals("participant1 : Lieu Profession",mergeRecord.getIndi().getResidence(), occupation.getValue(new TagPath("OCCU:PLAC"),""));
            Property link = participant1.getProperty(new TagPath("INDI:EVEN:XREF"));
            assertEquals("participant1 association vers participant2","@I1@", link.getValue() );

            Indi participant2 = (Indi) gedcom.getEntity("I1");
            participant2Family = participant2.getFamiliesWhereSpouse()[0];

            participant2Husband = participant2Family.getHusband();
            assertEquals("participant2 mari nom",mergeRecord.getWife().getLastName(), participant2Husband.getLastName());
            assertEquals("participant2 mari prenom",mergeRecord.getWife().getFirstName(),  participant2Husband.getFirstName());
            assertEquals("participant2 mari Date naissance","1 JAN 1970", participant2Husband.getBirthDate().getValue());
            // la date de deces n'est pas ajoutee
            assertEquals("participant2 marie deces",null, participant2Husband.getDeathDate());
            occupation = participant2Husband.getProperties(new TagPath("INDI:OCCU"))[0];
            assertEquals("participant2 mari Profession",mergeRecord.getWife().getOccupation(),      occupation.getValue(new TagPath("OCCU"),""));
            assertEquals("participant2 mari Date Profession",mergeRecord.getEventDate().getValue(), occupation.getValue(new TagPath("OCCU:DATE"),""));
            assertEquals("participant2 mari lieu Profession",mergeRecord.getWife().getResidence(), occupation.getValue(new TagPath("OCCU:PLAC"),""));
            assertEquals("participant2 lien vers participant1","@I00007@", participant2.getValue(new TagPath("INDI:ASSO"),""));
            assertEquals("participant2 lien vers participant1","INDI", participant2.getValue(new TagPath("INDI:ASSO:TYPE"),""));
            assertEquals("participant2 lien vers participant1","Présent@INDI:EVEN", participant2.getValue(new TagPath("INDI:ASSO:RELA"),""));


            participant2Wife = participant2Family.getWife();
            assertEquals("participant2 femme nom",mergeRecord.getWife().getMarriedLastName(), participant2Wife.getLastName());
            assertEquals("participant2 femme prenom",mergeRecord.getWife().getMarriedFirstName(),  participant2Wife.getFirstName());
            // la date de deces a ete ajoutée
            assertEquals("participant2 femme deces","BEF 1999", participant2Wife.getDeathDate().getValue());
            occupation = participant2Wife.getProperties(new TagPath("INDI:OCCU"))[0];
            assertEquals("participant2 femme Profession",mergeRecord.getWife().getMarriedOccupation(), occupation.getValue(new TagPath("OCCU"),""));
            assertEquals("participant2 femme Date Profession",mergeRecord.getEventDate().getValue(), occupation.getValue(new TagPath("OCCU:DATE"),""));
            assertEquals("participant2 femme Lieu Profession",mergeRecord.getWife().getMarriedResidence(), occupation.getValue(new TagPath("OCCU:PLAC"),""));

            //MergeDialog dialog = MergeDialog.show(new javax.swing.JFrame(), gedcom, null, miscRecord, true);
            //Thread.sleep(10000);

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            fail(ex.getMessage());
        }
    }


    /**
     *
    */
    public void testAddOtherParticipant2Wife() {
        try {
            Gedcom gedcom = TestUtility.createGedcom();


            RecordMisc miscRecord = createMiscOtherRecord("Accord2");
            String sourceTitle = "";
            MergeRecord mergeRecord = new MergeRecord(getRecordsInfoPlace(), sourceTitle, miscRecord);

            List<MergeModel> models;
            Fam indiParentFamily;
            Indi indiFather;
            Indi indiMother;
            Fam participant2Family;
            Indi participant2Husband;
            Indi participant2Wife;

            models = MergeModel.createMergeModel(mergeRecord, gedcom, null);
            assertEquals("Nombre model",3,models.size());

            MergeDialog dialog = MergeDialog.show(new javax.swing.JFrame(), gedcom, null, mergeRecord, false);
            //TestUtility.waitForDialogClose(dialog);

            //models.get(0).copyRecordToEntity();
            dialog.copyRecordToEntity();

            Indi participant1 = (Indi) gedcom.getEntity("I00007");
            assertEquals("Lien event vers source","@S00004@", participant1.getValue(new TagPath("INDI:EVEN:SOUR"),""));
            assertEquals("Source event","S00004", gedcom.getEntity(participant1.getValue(new TagPath("INDI:EVEN:SOUR"),"").replaceAll("@", "")).getId());
            assertEquals("Source event",miscRecord.getCote().getValue() + ", " +miscRecord.getFreeComment().getValue(), participant1.getValue(new TagPath("INDI:EVEN:SOUR:PAGE"),""));
            assertEquals("Date event",miscRecord.getEventDateProperty().getValue(), participant1.getValue(new TagPath("INDI:EVEN:DATE"),""));
            assertEquals("Lieu event",getRecordsInfoPlace().getValue(), participant1.getValue(new TagPath("INDI:EVEN:PLAC"),""));

            assertEquals("participant1 : Date naissance",mergeRecord.getIndi().getBirthDate().getValue(), participant1.getBirthDate().getValue());
            // le lieu et commentaire ne sont pas modifiés car la date de naissance du releve n'est pas plus precise
            assertNotSame("participant1 : lieu naissance",mergeRecord.getIndi().getBirthPlace(), participant1.getValue(new TagPath("INDI:BIRT:PLAC"), ""));
            //assertEquals("Indi : Note naissance","", fam.getHusband().getValue(new TagPath("INDI:BIRT:NOTE"), ""));

            assertEquals("participant1 : Profession",1, participant1.getProperties(new TagPath("INDI:OCCU")).length);
            Property occupation = participant1.getProperties(new TagPath("INDI:OCCU"))[0];
            assertEquals("participant1 : Profession",mergeRecord.getIndi().getOccupation(), occupation.getValue(new TagPath("OCCU"),""));
            assertEquals("participant1 : Date Profession",mergeRecord.getEventDate().getValue(), occupation.getValue(new TagPath("OCCU:DATE"),""));
            assertEquals("participant1 : Lieu Profession",mergeRecord.getIndi().getResidence(), occupation.getValue(new TagPath("OCCU:PLAC"),""));
            Property link = participant1.getProperty(new TagPath("INDI:EVEN:XREF"));
            assertEquals("participant1 association vers participant2","@Wife2@", link.getValue() );

            //assertEquals("IndiBirthDate","BMS Paris", ((Source)((PropertyXRef)sourceLink[0]).getTargetEntity()).getTitle() );
            //assertEquals("Indi : Note Profession","Profession indiquée dans l'acte CM entre Fatherfirstname FATHERLASTNAME et Motherfirstname WIFEFATHERLASTNAME le 01/03/1999 ( ville_marc, notaire_marc) ",
            //        occupation.getValue(new TagPath("OCCU:NOTE"),""));


            Indi participant2 = (Indi) gedcom.getEntity("Wife2");
            //assertEquals("Wife : Note Profession",
            //        "Profession indiquée dans l'acte CM entre Fatherfirstname FATHERLASTNAME et Motherfirstname WIFEFATHERLASTNAME le 01/03/1999 ( ville_marc, notaire_marc) ",
            //        occupation.getValue(new TagPath("OCCU:NOTE"),""));

            participant2Family = participant2.getFamiliesWhereSpouse()[0];

            participant2Husband = participant2Family.getHusband();
            assertEquals("participant2 mari nom",mergeRecord.getWife().getMarriedLastName(), participant2Husband.getLastName());
            assertEquals("articipant2 mari prenom",mergeRecord.getWife().getMarriedFirstName(),  participant2Husband.getFirstName());
            assertEquals("articipant2 mari profession",mergeRecord.getWife().getMarriedOccupation(),  participant2Husband.getProperty("OCCU").getValue());
            // la date de deces a ete ajoutée
            assertEquals("participant2 femme deces","BEF 1999", participant2Husband.getDeathDate().getValue());

            participant2Wife = participant2Family.getWife();
            assertEquals("participant2 femme nom",mergeRecord.getWife().getLastName(), participant2Wife.getLastName());
            assertEquals("participant2 femme prenom",mergeRecord.getWife().getFirstName(),  participant2Wife.getFirstName());
            assertEquals("participant2 femme Date naissance",mergeRecord.getWife().getBirthDate().getValue(), participant2Wife.getBirthDate().getValue());
            // la date de deces n'est pas ajoutee
            assertEquals("participant2 marie deces",null, participant2Wife.getDeathDate());
            occupation = participant2Wife.getProperties(new TagPath("INDI:OCCU"))[0];
            assertEquals("participant2 femme Profession",mergeRecord.getWife().getOccupation(),      occupation.getValue(new TagPath("OCCU"),""));
            assertEquals("participant2 femme Date Profession",mergeRecord.getEventDate().getValue(), occupation.getValue(new TagPath("OCCU:DATE"),""));
            assertEquals("participant2 femme lieu Profession",mergeRecord.getWife().getResidence(), occupation.getValue(new TagPath("OCCU:PLAC"),""));
            assertEquals("participant2 lien vers participant1","@I00007@", participant2.getValue(new TagPath("INDI:ASSO"),""));
            assertEquals("participant2 lien vers participant1","INDI", participant2.getValue(new TagPath("INDI:ASSO:TYPE"),""));
            assertEquals("participant2 lien vers participant1","Présent@INDI:EVEN", participant2.getValue(new TagPath("INDI:ASSO:RELA"),""));


            //MergeDialog dialog = MergeDialog.show(new javax.swing.JFrame(), gedcom, null, miscRecord, true);
            //Thread.sleep(10000);

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            fail(ex.getMessage());
        }
    }



}