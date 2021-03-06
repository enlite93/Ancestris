package ancestris.modules.releve.file;

import ancestris.modules.releve.model.DataManager;
import ancestris.modules.releve.model.Field.FieldType;
import ancestris.modules.releve.model.RecordBirth;
import ancestris.modules.releve.model.RecordDeath;
import ancestris.modules.releve.model.RecordMarriage;
import ancestris.modules.releve.model.RecordMisc;
import java.io.File;
import junit.framework.TestCase;

/**
 *
 * @author Michel
 */
public class ReleveFileEgmtTest extends TestCase {
    
     /**
     * Test of saveFile method, of class ReleveFileEgmt.
     */
    public void testSaveFileBirth() throws Exception {
        File file = new File(System.getProperty("user.home") + File.separator + "testsaveFile.txt");

        DataManager dataManager = new DataManager();
        dataManager.setPlace("");

        RecordBirth birth = new RecordBirth();
        birth.setEventDate("11/11/2000");
        birth.setCote("cote");
        birth.setFreeComment("photo");
        birth.setIndi("firstname", "lastname", "M", "", "", "birthPlace", "occupation", "indiResidence", "comment");
        birth.setIndiFather("indifathername", "indifatherlastname", "indifatheroccupation", "indiFatherResidence", "indifathercomment", "false", "70y");
        birth.setIndiMother("indimothername", "indimotherlastname", "indimotheroccupation", "indiMotherResidence", "indimothercomment", "false", "72y");
        birth.setWitness1("w1firstname", "w1lastname", "w1occupation", "w1comment");
        birth.setWitness2("w2firstname", "w2lastname", "w2occupation", "w2comment");
        birth.setWitness3("w3firstname", "w3lastname", "w3occupation", "w3comment");
        birth.setWitness4("w4firstname", "w4lastname", "w4occupation", "w4comment");
        birth.setGeneralComment("generalcomment");

        dataManager.addRecord(birth);
        StringBuilder sb = ReleveFileEgmt.saveFile(dataManager, dataManager.getDataModel(), DataManager.RecordType.birth, file, false);
        assertEquals("verify save error", sb.length(), 0);

        FileBuffer fb = ReleveFileEgmt.loadFile(file);
        assertEquals("load result", "", fb.getError().toString());
        assertEquals("load count", 1, fb.getBirthCount());
        RecordBirth birth2 = (RecordBirth) fb.getRecords().get(0);

        // je compare tous les champs
        for (FieldType fieldType : FieldType.values()) {
            switch (fieldType) {
                case indiFatherLastName:
                    assertEquals(String.valueOf(fieldType.ordinal()),birth.getIndiLastName().toString(),birth2.getField(fieldType).toString());
                    break;
                case wifeFatherLastName:
                    assertNull(String.valueOf(fieldType.ordinal()),birth2.getField(fieldType));
                    break;
                case indiFatherOccupation:
                case indiMotherOccupation:
                    assertEquals(String.valueOf(fieldType.ordinal()), "",birth2.getField(fieldType).toString());
                    break;
                case indiFatherComment:
                    assertEquals(String.valueOf(fieldType.ordinal()), "indifathercomment, indifatheroccupation, indiFatherResidence, Age:70a",birth2.getField(fieldType).toString());
                    break;
                case indiMotherComment:
                    assertEquals(String.valueOf(fieldType.ordinal()), "indimothercomment, indimotheroccupation, indiMotherResidence, Age:72a",birth2.getField(fieldType).toString());
                    break;
                case witness1Occupation:
                case witness2Occupation:
                    assertEquals(String.valueOf(fieldType.ordinal()), "",birth2.getField(fieldType).toString());
                    break;
                case witness1Comment:
                    assertEquals(String.valueOf(fieldType.ordinal()), "w1comment, w1occupation",birth2.getField(fieldType).toString());
                    break;
                case witness2Comment:
                    assertEquals(String.valueOf(fieldType.ordinal()), "w2comment, w2occupation",birth2.getField(fieldType).toString());
                    break;
                case generalComment:
                    //assertEquals(String.valueOf(fieldType.ordinal()), "generalcomment ",birth2.getField(fieldType).toString());
                    assertEquals(String.valueOf(fieldType.ordinal()), "generalcomment, témoin(s): w3firstname w3lastname w3occupation w3comment, w4firstname w4lastname w4occupation w4comment ",birth2.getField(fieldType).toString());
                    break;
                case indiFatherAge:
                case indiMotherAge:
                case witness3FirstName:
                case witness3LastName:
                case witness3Occupation:
                case witness3Comment:
                case witness4FirstName:
                case witness4LastName:
                case witness4Occupation:
                case witness4Comment:
                    assertEquals(String.valueOf(fieldType.ordinal()), "",birth2.getField(fieldType).toString());
                    break;
                default:
                    // autres champs
                    if (birth.getField(fieldType) == null) {
                        assertNull(String.valueOf(fieldType.ordinal()), birth2.getField(fieldType));
                    } else {
                        if ( fieldType == FieldType.indiResidence || fieldType == FieldType.indiMarriedResidence
                                || fieldType == FieldType.indiFatherResidence || fieldType == FieldType.indiMotherResidence
                                || fieldType == FieldType.wifeResidence || fieldType == FieldType.wifeMarriedResidence
                                || fieldType == FieldType.wifeFatherResidence || fieldType == FieldType.wifeMotherResidence) {
                            assertNotNull(String.valueOf(fieldType.ordinal()), birth2.getField(fieldType));
                            assertEquals(String.valueOf(fieldType.ordinal()), "", birth2.getField(fieldType).toString());
                        } else {
                            assertNotNull(String.valueOf(fieldType.ordinal()), birth2.getField(fieldType));
                            assertEquals(String.valueOf(fieldType.ordinal()), birth.getField(fieldType).toString(), birth2.getField(fieldType).toString());
                        }
                    }
            }
        }

        file.delete();

    }

    /**
     * Test of saveFile method, of class ReleveFileEgmt.
     */
    public void testSaveFileMarriage() throws Exception {
        File file = new File(System.getProperty("user.home") + File.separator + "testsaveFile.txt");

        DataManager dataManager = new DataManager();
        dataManager.setPlace("");

        RecordMarriage marriage = new RecordMarriage();
        marriage.setEventDate("11/01/2000");
        marriage.setCote("cote");
        marriage.setFreeComment("photo");
        marriage.setIndi("indifirstname", "indilastname", "M", "23y", "01/01/1980", "indiBirthplace", "indioccupation", "indiResidence", "indicomment");
        marriage.setIndiMarried("indimarriedfirstname", "indimarriedlastname", "indimarriedoccupation", "indiMarriedResidence", "indimarriedcomment", "false");
        marriage.setIndiFather("indifathername", "indifatherlastname", "indifatheroccupation", "indiFatherResidence", "indifathercomment", "false", "70y");
        marriage.setIndiMother("indimothername", "indimotherlastname", "indimotheroccupation", "indiMotherResidence", "indimothercomment", "false", "72y");
        marriage.setWife("wifefirstname", "wifelastname", "F", "21y", "02/02/1982", "wifeBirthplace", "wifeoccupation", "wifeResidence", "wifecomment");
        marriage.setWifeMarried("wifemarriedfirstname", "wifemarriedlastname", "wifemarriedoccupation", "wifeMarriedResidence", "wifemarriedcomment", "false");
        marriage.setWifeFather("wifefathername", "wifefatherlastname", "wifefatheroccupation", "wifeFatherResidence", "wifefathercomment", "false", "60y");
        marriage.setWifeMother("wifemothername", "wifemotherlastname", "wifemotheroccupation", "wifeMotherResidence", "wifemothercomment", "false", "62y");
        marriage.setWitness1("w1firstname", "w1lastname", "w1occupation", "w1comment");
        marriage.setWitness2("w2firstname", "w2lastname", "w2occupation", "w2comment");
        marriage.setWitness3("w3firstname", "w3lastname", "w3occupation", "w3comment");
        marriage.setWitness4("w4firstname", "w4lastname", "w4occupation", "w4comment");
        marriage.setGeneralComment("generalcomment");

        dataManager.addRecord(marriage);
        StringBuilder sb = ReleveFileEgmt.saveFile(dataManager, dataManager.getDataModel(), DataManager.RecordType.marriage, file, false);
        assertEquals("verify save error", 0, sb.length());

        FileBuffer fb = ReleveFileEgmt.loadFile(file);
        assertEquals("load result", "", fb.getError().toString());
        assertEquals("load count", 1, fb.getMarriageCount());
        RecordMarriage marriage2 = (RecordMarriage) fb.getRecords().get(0);

        // je compare tous les champs
        assertEquals("EventDate",   marriage.getEventDateProperty().toString(),marriage2.getEventDateProperty().toString());
        assertEquals("Cote",        marriage.getCote().toString(),marriage2.getCote().toString());
        assertEquals("parish",      marriage.getParish().toString(),marriage2.getParish().toString());
        assertEquals("EventDate",   marriage.getEventDateProperty().toString(),marriage2.getEventDateProperty().toString());
        assertNull("Notary",        marriage2.getNotary());
        assertNull("EventType",     marriage2.getEventType());
        assertEquals("FreeComment",    marriage.getFreeComment().toString(),marriage2.getFreeComment().toString());

        assertEquals("IndiFirstName",            marriage.getIndiFirstName().toString(),marriage2.getIndiFirstName().toString());
        assertEquals("IndiLastName",            marriage.getIndiLastName().toString(),marriage2.getIndiLastName().toString());
        assertEquals("IndiSex",                 marriage.getIndiSex().toString(),marriage2.getIndiSex().toString());
        assertEquals("IndiAge",                 marriage.getIndiAge().getValue(),marriage2.getIndiAge().getValue());
        assertEquals("IndiBirthDate",           "",marriage2.getIndiBirthDate().getValue());
        assertEquals("IndiBirthPlace",          "",marriage2.getIndiBirthPlace().toString());
        assertEquals("IndiOccupation",          "",marriage2.getIndiOccupation().toString());
        assertEquals("IndiComment",             "indicomment, né le 01/01/1980, indiBirthplace, indioccupation, Ex conjoint: indimarriedfirstname indimarriedlastname indimarriedoccupation indiMarriedResidence indimarriedcomment",marriage2.getIndiComment().toString());
        assertEquals("IndiMarriedFirstName",    "",marriage2.getIndiMarriedFirstName().toString());
        assertEquals("IndiMarriedLastName",     "",marriage2.getIndiMarriedLastName().toString());
        assertEquals("IndiMarriedOccupation",   "",marriage2.getIndiMarriedOccupation().toString());
        assertEquals("IndiMarriedComment",      "".toString(),marriage2.getIndiMarriedComment().toString());
        assertEquals("IndiMarriedDead",         "",marriage2.getIndiMarriedDead().toString());
        assertEquals("IndiFatherFirstName",     marriage.getIndiFatherFirstName().toString(),marriage2.getIndiFatherFirstName().toString());
        assertEquals("IndiFatherLastName",      marriage.getIndiLastName().toString(),marriage2.getIndiFatherLastName().toString());
        assertEquals("IndiFatherAge",           "",marriage2.getIndiFatherAge().toString());
        assertEquals("IndiFatherDead",          marriage.getIndiFatherDead().toString(),marriage2.getIndiFatherDead().toString());
        assertEquals("IndiFatherOccupation",    "",marriage2.getIndiFatherOccupation().toString());
        assertEquals("IndiFatherComment",       "indifathercomment, indifatheroccupation, indiFatherResidence, Age:70a",marriage2.getIndiFatherComment().toString());
        assertEquals("IndiMotherFirstName",     marriage.getIndiMotherFirstName().toString(),marriage2.getIndiMotherFirstName().toString());
        assertEquals("IndiMotherLastName",      marriage.getIndiMotherLastName().toString(),marriage2.getIndiMotherLastName().toString());
        assertEquals("IndiMotherAge",           "",marriage2.getIndiMotherAge().toString());
        assertEquals("IndiMotherDead",          marriage.getIndiMotherDead().toString(),marriage2.getIndiMotherDead().toString());
        assertEquals("IndiMotherOccupation",    "",marriage2.getIndiMotherOccupation().toString());
        assertEquals("IndiMotherComment",      "indimothercomment, indimotheroccupation, indiMotherResidence, Age:72a",marriage2.getIndiMotherComment().toString());

        assertEquals("WifeFirstName",           marriage.getWifeFirstName().toString(),marriage2.getWifeFirstName().toString());
        assertEquals("WifeLastName",            marriage.getWifeLastName().toString(),marriage2.getWifeLastName().toString());
        assertEquals("WifeSex",                 marriage.getWifeSex().toString(),marriage2.getWifeSex().toString());
        assertEquals("WifeAge",                 marriage.getWifeAge().toString(),marriage2.getWifeAge().toString());
        assertEquals("WifeBirthDate",           "",marriage2.getWifeBirthDate().toString());
        assertEquals("WifeBirthPlace",          "",marriage2.getWifeBirthPlace().toString());
        assertEquals("WifeOccupation",          "",marriage2.getWifeOccupation().toString());
        assertEquals("WifeResidence",           marriage.getWifeResidence().toString(),marriage2.getWifeResidence().toString());
        assertEquals("WifeComment",             "wifecomment, né le 02/02/1982, wifeBirthplace, wifeoccupation, Ex conjoint: wifemarriedfirstname wifemarriedlastname wifemarriedoccupation wifeMarriedResidence wifemarriedcomment",marriage2.getWifeComment().toString());
        assertEquals("WifeMarriedFirstName",    "",marriage2.getWifeMarriedFirstName().toString());
        assertEquals("WifeMarriedLastName",     "",marriage2.getWifeMarriedLastName().toString());
        assertEquals("WifeMarriedOccupation",   "",marriage2.getWifeMarriedOccupation().toString());
        assertEquals("WifeMarriedComment",      "",marriage2.getWifeMarriedComment().toString());
        assertEquals("WifeMarriedDead",         marriage.getWifeMarriedDead().toString(),marriage2.getWifeMarriedDead().toString());
        assertEquals("WifeFatherFirstName",     marriage.getWifeFatherFirstName().toString(),marriage2.getWifeFatherFirstName().toString());
        assertEquals("WifeFatherLastName",      marriage.getWifeLastName().toString(),marriage2.getWifeFatherLastName().toString());
        assertEquals("WifeFatherOccupation",    "",marriage2.getWifeFatherOccupation().toString());
        assertEquals("WifeFatherAge",           "",marriage2.getWifeFatherAge().toString());
        assertEquals("WifeFatherDead",          marriage.getWifeFatherDead().toString(),marriage2.getWifeFatherDead().toString());
        assertEquals("WifeFatherComment",       "wifefathercomment, wifefatheroccupation, wifeFatherResidence, Age:60a",marriage2.getWifeFatherComment().toString());
        assertEquals("WifeMotherFirstName",     marriage.getWifeMotherFirstName().toString(),marriage2.getWifeMotherFirstName().toString());
        assertEquals("WifeMotherLastName",      marriage.getWifeMotherLastName().toString(),marriage2.getWifeMotherLastName().toString());
        assertEquals("WifeMotherAge",           "",marriage2.getWifeMotherAge().toString());
        assertEquals("WifeMotherDead",          marriage.getWifeMotherDead().toString(),marriage2.getWifeMotherDead().toString());
        assertEquals("WifeMotherOccupation",    "",marriage2.getWifeMotherOccupation().toString());
        assertEquals("WifeMotherComment",       "wifemothercomment, wifemotheroccupation, wifeMotherResidence, Age:62a",marriage2.getWifeMotherComment().toString());
        
        assertEquals("Witness1FirstName",     marriage.getWitness1FirstName().toString(),marriage2.getWitness1FirstName().toString());
        assertEquals("Witness1LastName",      marriage.getWitness1LastName().toString(),marriage2.getWitness1LastName().toString());
        assertEquals("Witness1Occupation",    "",marriage2.getWitness1Occupation().toString());
        assertEquals("Witness1Comment",       "w1comment, w1occupation",marriage2.getWitness1Comment().toString());
        assertEquals("Witness2FirstName",     marriage.getWitness2FirstName().toString(),marriage2.getWitness2FirstName().toString());
        assertEquals("Witness2LastName",      marriage.getWitness2LastName().toString(),marriage2.getWitness2LastName().toString());
        assertEquals("Witness2Occupation",    "",marriage2.getWitness2Occupation().toString());
        assertEquals("Witness2Comment",       "w2comment, w2occupation",marriage2.getWitness2Comment().toString());
        assertEquals("Witness3FirstName",     "",marriage2.getWitness3FirstName().toString());
        assertEquals("Witness3LastName",      "",marriage2.getWitness3LastName().toString());
        assertEquals("Witness3Occupation",    "",marriage2.getWitness3Occupation().toString());
        assertEquals("Witness3Comment",       "",marriage2.getWitness3Comment().toString());
        assertEquals("Witness4FirstName",     "",marriage2.getWitness4FirstName().toString());
        assertEquals("Witness4LastName",      "",marriage2.getWitness4LastName().toString());
        assertEquals("Witness4Occupation",    "",marriage2.getWitness4Occupation().toString());
        assertEquals("Witness4Comment",       "",marriage2.getWitness4Comment().toString());

        assertEquals("GeneralComment", "generalcomment, témoin(s): w3firstname w3lastname w3occupation w3comment, w4firstname w4lastname w4occupation w4comment ",marriage2.getGeneralComment().toString());
        
        file.delete();

    }

    /**
     * Test de l'enregistrement d'un deces
     */
    public void testSaveFileDeath() throws Exception {
        File file = new File(System.getProperty("user.home") + File.separator + "testsaveFile.txt");

        String place = "cityname,citycode,county,state,country";
        
        DataManager dataManager = new DataManager();
        dataManager.setPlace(place);

        RecordDeath death = new RecordDeath();
        death.setEventDate("11/11/2000");
        death.setCote("cote");
        death.setGeneralComment("generalcomment");
        death.setFreeComment("photo");
        death.setIndi("indifirstname", "indilastname", "M", "indiage", "01/01/1990", "indiBirthPlace", "indioccupation", "indiResidence", "indicomment");
        death.setIndiMarried("indimarriedfirstname", "indimarriedlastname", "indimarriedoccupation", "indiMarriedResidence", "indimarriedcomment", "false");
        death.setIndiFather("indifatherfirstname", "indifatherlastname", "indifatheroccupation", "indiFatherResidence", "indifathercomment", "false", "70y");
        death.setIndiMother("indimothername", "indimotherlastname", "indimotheroccupation", "indiMotherResidence", "indimothercomment", "false", "72y");
        death.setWitness1("w1firstname", "w1lastname", "w1occupation", "w1comment");
        death.setWitness2("w2firstname", "w2lastname", "w2occupation", "w2comment");
        death.setWitness3("w3firstname", "w3lastname", "w3occupation", "w3comment");
        death.setWitness4("w4firstname", "w4lastname", "w4occupation", "w4comment");

        dataManager.addRecord(death);
        StringBuilder sb = ReleveFileEgmt.saveFile(dataManager, dataManager.getDataModel(), DataManager.RecordType.death, file, false);
        assertEquals("verify save error", "", sb.toString());

        FileBuffer fb = ReleveFileEgmt.loadFile(file);
        assertEquals("load result", "", fb.getError().toString());
        assertEquals("load count", 1, fb.getDeathCount());
        RecordDeath death2 = (RecordDeath) fb.getRecords().get(0);

        // je compare tous les champs

        assertEquals("EventDate",       death.getEventDateProperty().toString(),death2.getEventDateProperty().toString());
        assertEquals("Cote",            death.getCote().toString(),death2.getCote().toString());
        assertEquals("parish",          death.getParish().toString(),death2.getParish().toString());
        assertEquals("EventDate",       death.getEventDateProperty().toString(),death2.getEventDateProperty().toString());
        assertNull("Notary",            death2.getNotary());
        assertNull("EventType",         death2.getEventType());
        assertEquals("FreeComment",    death.getFreeComment().toString(),death2.getFreeComment().toString());

        assertEquals("IndiFirstName",  death.getIndiFirstName().toString(),death2.getIndiFirstName().toString());
        assertEquals("IndiLastName",   death.getIndiLastName().toString(),death2.getIndiLastName().toString());
        assertEquals("IndiSex",        death.getIndiSex().toString(),death2.getIndiSex().toString());
        assertEquals("IndiAge",        death.getIndiAge().toString(),death2.getIndiAge().toString());
        assertEquals("IndiBirthDate",  "",death2.getIndiBirthDate().toString());
        assertEquals("IndiBirthPlace", "",death2.getIndiBirthPlace().toString());
        assertEquals("IndiPlace",      "",death2.getIndiBirthPlace().toString());
        assertEquals("IndiOccupation", "",death2.getIndiOccupation().toString());
        assertEquals("IndiComment",    "indicomment, né le 01/01/1990, indiBirthPlace, indioccupation",death2.getIndiComment().toString());
        assertEquals("IndiMarriedFirstName",    death.getIndiMarriedFirstName().toString(),death2.getIndiMarriedFirstName().toString());
        assertEquals("IndiMarriedLastName",     death.getIndiMarriedLastName().toString(),death2.getIndiMarriedLastName().toString());
        assertEquals("IndiMarriedOccupation",   "",death2.getIndiMarriedOccupation().toString());
        assertEquals("IndiMarriedComment",      death.getIndiMarriedComment().toString(),death2.getIndiMarriedComment().toString());
        assertEquals("IndiMarriedDead",         death.getIndiMarriedDead().toString(),death2.getIndiMarriedDead().toString());

        assertEquals("IndiFatherFirstName",     death.getIndiFatherFirstName().toString(),death2.getIndiFatherFirstName().toString());
        assertEquals("IndiFatherLastName",         death.getIndiLastName().toString(),death2.getIndiFatherLastName().toString());
        assertEquals("IndiFatherAge",           "",death2.getIndiFatherAge().toString());
        assertEquals("IndiFatherDead",          death.getIndiFatherDead().toString(),death2.getIndiFatherDead().toString());
        assertEquals("IndiFatherOccupation",    "",death2.getIndiFatherOccupation().toString());
        assertEquals("IndiFatherComment",       "indifathercomment, indifatheroccupation, indiFatherResidence, Age:70a",death2.getIndiFatherComment().toString());
        assertEquals("IndiMotherFirstName",     death.getIndiMotherFirstName().toString(),death2.getIndiMotherFirstName().toString());
        assertEquals("IndiMotherLastName",      death.getIndiMotherLastName().toString(),death2.getIndiMotherLastName().toString());
        assertEquals("IndiMotherAge",           "",death2.getIndiMotherAge().toString());
        assertEquals("IndiMotherDead",          death.getIndiMotherDead().toString(),death2.getIndiMotherDead().toString());
        assertEquals("IndiMotherOccupation",    "",death2.getIndiMotherOccupation().toString());
        assertEquals("IndiMotherComment",       "indimothercomment, indimotheroccupation, indiMotherResidence, Age:72a",death2.getIndiMotherComment().toString());

        assertEquals("WifeFirstName",           null,death2.getWifeFirstName());
        assertEquals("WifeLastName",            null,death2.getWifeLastName());
        assertEquals("WifeSex",                 null,death2.getWifeSex());
        assertEquals("WifeAge",                 null,death2.getWifeAge());
        assertEquals("WifeBirthDate",           null,death2.getWifeBirthDate());
        assertEquals("WifePlace",               null,death2.getWifeBirthPlace());
        assertEquals("WifeOccupation",          null,death2.getWifeOccupation());
        assertEquals("WifeResidence",           null,death2.getWifeResidence());
        assertEquals("WifeComment",             null,death2.getWifeComment());
        assertEquals("WifeMarriedFirstName",    null,death2.getWifeMarriedFirstName());
        assertEquals("WifeMarriedLastName",     null,death2.getWifeMarriedLastName());
        assertEquals("WifeMarriedOccupation",   null,death2.getWifeMarriedOccupation());
        assertEquals("WifeMarriedComment",      null,death2.getWifeMarriedComment());
        assertEquals("WifeMarriedDead",         null,death2.getWifeMarriedDead());
        assertEquals("WifeFatherFirstName",     null,death2.getWifeFatherFirstName());
        assertEquals("WifeFatherLastName",      null,death2.getWifeFatherLastName());
        assertEquals("WifeFatherAge",           null,death2.getWifeFatherAge());
        assertEquals("WifeFatherDead",          null,death2.getWifeFatherDead());
        assertEquals("WifeFatherOccupation",    null,death2.getWifeFatherOccupation());
        assertEquals("WifeFatherComment",       null,death2.getWifeFatherComment());
        assertEquals("WifeMotherFirstName",     null,death2.getWifeMotherFirstName());
        assertEquals("WifeMotherLastName",      null,death2.getWifeMotherLastName());
        assertEquals("WifeMotherAge",           null,death2.getWifeMotherAge());
        assertEquals("WifeMotherDead",          null,death2.getWifeMotherDead());
        assertEquals("WifeMotherOccupation",    null,death2.getWifeMotherOccupation());
        assertEquals("WifeMotherComment",       null,death2.getWifeMotherComment());

        assertEquals("Witness1FirstName",     death.getWitness1FirstName().toString(),death2.getWitness1FirstName().toString());
        assertEquals("Witness1LastName",      death.getWitness1LastName().toString(),death2.getWitness1LastName().toString());
        assertEquals("Witness1Occupation",    "",death2.getWitness1Occupation().toString());
        assertEquals("Witness1Comment",       "w1comment, w1occupation",death2.getWitness1Comment().toString());
        assertEquals("Witness2FirstName",     death.getWitness2FirstName().toString(),death2.getWitness2FirstName().toString());
        assertEquals("Witness2LastName",      death.getWitness2LastName().toString(),death2.getWitness2LastName().toString());
        assertEquals("Witness2Occupation",    "",death2.getWitness2Occupation().toString());
        assertEquals("Witness2Comment",       "w2comment, w2occupation",death2.getWitness2Comment().toString());
        assertEquals("Witness3FirstName",     "",death2.getWitness3FirstName().toString());
        assertEquals("Witness3LastName",      "",death2.getWitness3LastName().toString());
        assertEquals("Witness3Occupation",    "",death2.getWitness3Occupation().toString());
        assertEquals("Witness3Comment",       "",death2.getWitness3Comment().toString());
        assertEquals("Witness4FirstName",     "",death2.getWitness4FirstName().toString());
        assertEquals("Witness4LastName",      "",death2.getWitness4LastName().toString());
        assertEquals("Witness4Occupation",    "",death2.getWitness4Occupation().toString());
        assertEquals("Witness4Comment",       "",death2.getWitness4Comment().toString());


        assertEquals("GeneralComment", "generalcomment, témoin(s): w3firstname w3lastname w3occupation w3comment, w4firstname w4lastname w4occupation w4comment ",death2.getGeneralComment().toString());
        

        file.delete();

    }

    /**
     * Test of saveFile method, of class ReleveFileEgmt.
     */
    public void testSaveFileMarriageContract() throws Exception {
        File file = new File(System.getProperty("user.home") + File.separator + "testsaveFile.txt");

        DataManager dataManager = new DataManager();
        dataManager.setPlace("");

        RecordMisc misc = new RecordMisc();
        misc.setEventDate("11/01/2000");
        misc.setCote("cote");
        misc.setParish("parish");
        misc.setNotary("Notary");
        misc.setEventType("contrat de mariage");
        misc.setGeneralComment("generalcomment");
        misc.setFreeComment("photo");
        misc.setIndi("indifirstname", "indilastname", "M", "24y", "01/01/1980", "indiBirthPlace", "indioccupation", "indiResidence", "indicomment");
        misc.setIndiMarried("indimarriedfirstname", "indimarriedlastname", "indimarriedoccupation", "indiMarriedResidence", "indimarriedcomment", "false");
        misc.setIndiFather("indifathername", "indifatherlastname", "indifatheroccupation", "indiFatherResidence", "indifathercomment", "false", "70y");
        misc.setIndiMother("indimothername", "indimotherlastname", "indimotheroccupation", "indiMotherResidence", "indimothercomment", "false", "72y");
        misc.setWife("wifefirstname", "wifelastname", "F", "22y", "02/02/1982", "wifeBirthPlace", "wifeoccupation", "wifeResidence", "wifecomment");
        misc.setWifeMarried("wifemarriedfirstname", "wifemarriedlastname", "wifemarriedoccupation", "wifeMarriedResidence", "wifemarriedcomment", "true");
        misc.setWifeFather("wifefathername", "wifefatherlastname", "wifefatheroccupation", "wifeFatherResidence", "wifefathercomment", "", "60y");
        misc.setWifeMother("wifemothername", "wifemotherlastname", "wifemotheroccupation", "wifeMotherResidence", "wifemothercomment", "feue", "62y");
        misc.setWitness1("w1firstname", "w1lastname", "w1occupation", "w1comment");
        misc.setWitness2("w2firstname", "w2lastname", "w2occupation", "w2comment");
        misc.setWitness3("w3firstname", "w3lastname", "w3occupation", "w3comment");
        misc.setWitness4("w4firstname", "w4lastname", "w4occupation", "w4comment");

        dataManager.addRecord(misc);
        StringBuilder sb = ReleveFileEgmt.saveFile(dataManager, dataManager.getDataModel(), DataManager.RecordType.misc, file, false);
        assertEquals("verify save error", 0, sb.length());

        FileBuffer fb = ReleveFileEgmt.loadFile(file);
        assertEquals("load result", "", fb.getError().toString());
        assertEquals("load count", 1, fb.getMiscCount());
        RecordMisc misc2 = (RecordMisc) fb.getRecords().get(0);

        assertEquals("EventDate",   misc.getEventDateProperty().toString(),misc2.getEventDateProperty().toString());
        assertEquals("EventType",   "MARC",misc2.getEventType().toString());
        assertEquals("parish",      misc.getParish().toString(),misc2.getParish().toString());
        assertEquals("Notary",      misc.getNotary().toString(),misc2.getNotary().toString());
        assertEquals("Cote",        misc.getCote().toString(),misc2.getCote().toString());
        assertEquals("FreeComment", misc.getFreeComment().toString(),misc2.getFreeComment().toString());

        assertEquals("IndiFirstName",           misc.getIndiFirstName().toString(),misc2.getIndiFirstName().toString());
        assertEquals("IndiLastName",            misc.getIndiLastName().toString(),misc2.getIndiLastName().toString());
        assertEquals("IndiSex",                 misc.getIndiSex().toString(),misc2.getIndiSex().toString());
        assertEquals("IndiAge",                 misc.getIndiAge().toString(),misc2.getIndiAge().toString());
        assertEquals("IndiBirthDate",           "",misc2.getIndiBirthDate().getValue());
        assertEquals("IndiBirthPlace",          "",misc2.getIndiBirthPlace().toString());
        assertEquals("IndiPlace",               "",misc2.getIndiBirthPlace().toString());
        assertEquals("IndiOccupation",          "",misc2.getIndiOccupation().toString());
        assertEquals("IndiComment",             "indicomment, né le 01/01/1980, indiBirthPlace, indioccupation, Ex conjoint: indimarriedfirstname indimarriedlastname indimarriedoccupation indiMarriedResidence indimarriedcomment",misc2.getIndiComment().toString());
        assertEquals("IndiMarriedFirstName",    "",misc2.getIndiMarriedFirstName().toString());
        assertEquals("IndiMarriedLastName",     "",misc2.getIndiMarriedLastName().toString());
        assertEquals("IndiMarriedOccupation",   "",misc2.getIndiMarriedOccupation().toString());
        assertEquals("IndiMarriedComment",      "",misc2.getIndiMarriedComment().toString());
        assertEquals("IndiMarriedDead",         "",misc2.getIndiMarriedDead().toString());
        assertEquals("IndiFatherFirstName",     misc.getIndiFatherFirstName().toString(),misc2.getIndiFatherFirstName().toString());
        assertEquals("IndiFatherLastName",      misc.getIndiLastName().toString(),misc2.getIndiFatherLastName().toString());
        assertEquals("IndiFatherAge",           "",misc2.getIndiFatherAge().toString());
        assertEquals("IndiFatherDead",          misc.getIndiFatherDead().toString(),misc2.getIndiFatherDead().toString());
        assertEquals("IndiFatherOccupation",    "",misc2.getIndiFatherOccupation().toString());
        assertEquals("IndiFatherComment",       "indifathercomment, indifatheroccupation, indiFatherResidence, Age:70a",misc2.getIndiFatherComment().toString());
        assertEquals("IndiMotherFirstName",     misc.getIndiMotherFirstName().toString(),misc2.getIndiMotherFirstName().toString());
        assertEquals("IndiMotherLastName",      misc.getIndiMotherLastName().toString(),misc2.getIndiMotherLastName().toString());
        assertEquals("IndiMotherAge",           "",misc2.getIndiMotherAge().toString());
        assertEquals("IndiMotherDead",          misc.getIndiMotherDead().toString(),misc2.getIndiMotherDead().toString());
        assertEquals("IndiMotherOccupation",    "",misc2.getIndiMotherOccupation().toString());
        assertEquals("IndiMotherComment",      "indimothercomment, indimotheroccupation, indiMotherResidence, Age:72a",misc2.getIndiMotherComment().toString());

        assertEquals("WifeFirstName",           misc.getWifeFirstName().toString(),misc2.getWifeFirstName().toString());
        assertEquals("WifeLastName",            misc.getWifeLastName().toString(),misc2.getWifeLastName().toString());
        assertEquals("WifeSex",                 misc.getWifeSex().toString(),misc2.getWifeSex().toString());
        assertEquals("WifeAge",                 misc.getWifeAge().toString(),misc2.getWifeAge().toString());
        assertEquals("WifeBirthDate",           "",misc2.getWifeBirthDate().toString());
        assertEquals("WifeBirthPlace",          "",misc2.getWifeBirthPlace().toString());
        assertEquals("WifeOccupation",          "",misc2.getWifeOccupation().toString());
        assertEquals("WifeResidence",           misc.getWifeResidence().toString(),misc2.getWifeResidence().toString());
        assertEquals("WifeComment",             "wifecomment, né le 02/02/1982, wifeBirthPlace, wifeoccupation, Ex conjoint: wifemarriedfirstname wifemarriedlastname Décédé wifemarriedoccupation wifeMarriedResidence wifemarriedcomment",misc2.getWifeComment().toString());
        assertEquals("WifeMarriedFirstName",    "",misc2.getWifeMarriedFirstName().toString());
        assertEquals("WifeMarriedLastName",     "",misc2.getWifeMarriedLastName().toString());
        assertEquals("WifeMarriedOccupation",   "",misc2.getWifeMarriedOccupation().toString());
        assertEquals("WifeMarriedComment",      "",misc2.getWifeMarriedComment().toString());
        assertEquals("WifeMarriedDead",         "",misc2.getWifeMarriedDead().toString());
        assertEquals("WifeFatherFirstName",     misc.getWifeFatherFirstName().toString(),misc2.getWifeFatherFirstName().toString());
        assertEquals("WifeFatherLastName",      misc.getWifeLastName().toString(),misc2.getWifeFatherLastName().toString());
        assertEquals("WifeFatherAge",           "",misc2.getWifeFatherAge().toString());
        assertEquals("WifeFatherOccupation",    "",misc2.getWifeFatherOccupation().toString());
        assertEquals("WifeFatherComment",       "wifefathercomment, wifefatheroccupation, wifeFatherResidence, Age:60a",misc2.getWifeFatherComment().toString());
        assertEquals("WifeFatherDead",          misc.getWifeFatherDead().toString(),misc2.getWifeFatherDead().toString());
        assertEquals("WifeMotherFirstName",     misc.getWifeMotherFirstName().toString(),misc2.getWifeMotherFirstName().toString());
        assertEquals("WifeMotherLastName",      misc.getWifeMotherLastName().toString(),misc2.getWifeMotherLastName().toString());
        assertEquals("WifeMotherAge",           "",misc2.getWifeMotherAge().toString());
        assertEquals("WifeMotherDead",          misc.getWifeMotherDead().toString(),misc2.getWifeMotherDead().toString());
        assertEquals("WifeMotherOccupation",    "",misc2.getWifeMotherOccupation().toString());
        assertEquals("WifeMotherComment",       "wifemothercomment, wifemotheroccupation, wifeMotherResidence, Age:62a",misc2.getWifeMotherComment().toString());

        assertEquals("Witness1FirstName",     misc.getWitness1FirstName().toString(),misc2.getWitness1FirstName().toString());
        assertEquals("Witness1LastName",      misc.getWitness1LastName().toString(),misc2.getWitness1LastName().toString());
        assertEquals("Witness1Occupation",    "",misc2.getWitness1Occupation().toString());
        assertEquals("Witness1Comment",       "w1comment, w1occupation",misc2.getWitness1Comment().toString());
        assertEquals("Witness2FirstName",     misc.getWitness2FirstName().toString(),misc2.getWitness2FirstName().toString());
        assertEquals("Witness2LastName",      misc.getWitness2LastName().toString(),misc2.getWitness2LastName().toString());
        assertEquals("Witness2Occupation",    "",misc2.getWitness2Occupation().toString());
        assertEquals("Witness2Comment",       "w2comment, w2occupation",misc2.getWitness2Comment().toString());
        assertEquals("Witness3FirstName",     "",misc2.getWitness3FirstName().toString());
        assertEquals("Witness3LastName",      "",misc2.getWitness3LastName().toString());
        assertEquals("Witness3Occupation",    "",misc2.getWitness3Occupation().toString());
        assertEquals("Witness3Comment",       "",misc2.getWitness3Comment().toString());
        assertEquals("Witness4FirstName",     "",misc2.getWitness4FirstName().toString());
        assertEquals("Witness4LastName",      "",misc2.getWitness4LastName().toString());
        assertEquals("Witness4Occupation",    "",misc2.getWitness4Occupation().toString());
        assertEquals("Witness4Comment",       "",misc2.getWitness4Comment().toString());

        assertEquals("GeneralComment", "generalcomment, témoin(s): w3firstname w3lastname w3occupation w3comment, w4firstname w4lastname w4occupation w4comment",misc2.getGeneralComment().toString());

        file.delete();

    }

/**
     * Test of saveFile method, of class ReleveFileEgmt.
     */
    public void testSaveFileTestament() throws Exception {
        File file = new File(System.getProperty("user.home") + File.separator + "testsaveFile.txt");

        DataManager dataManager = new DataManager();
        dataManager.setPlace("");

        RecordMisc misc = new RecordMisc();
        misc.setEventDate("11/01/2000");
        misc.setCote("cote");
        misc.setParish("parish");
        misc.setNotary("Notary");
        misc.setEventType("testament");
        misc.setGeneralComment("generalcomment");
        misc.setFreeComment("photo");
        misc.setIndi("indifirstname", "indilastname", "M", "24y", "01/01/1980", "indiBirthPlace", "indioccupation", "indiResidence", "indicomment");
        misc.setIndiMarried("indimarriedfirstname", "indimarriedlastname", "indimarriedoccupation", "indiMarriedResidence", "indimarriedcomment", "false");
        misc.setIndiFather("indifathername", "indifatherlastname", "indifatheroccupation", "indiFatherResidence", "indifathercomment", "false", "70y");
        misc.setIndiMother("indimothername", "indimotherlastname", "indimotheroccupation", "indiMotherResidence", "indimothercomment", "false", "72y");
        misc.setWife("wifefirstname", "wifelastname", "F", "22y", "02/02/1982", "wifeBirthPlace", "wifeoccupation", "wifeResidence", "wifecomment");
        misc.setWifeMarried("wifemarriedfirstname", "wifemarriedlastname", "wifemarriedoccupation", "wifeMarriedResidence", "wifemarriedcomment", "false");
        misc.setWifeFather("wifefathername", "wifefatherlastname", "wifefatheroccupation", "wifeFatherResidence", "wifefathercomment", "false", "60y");
        misc.setWifeMother("wifemothername", "wifemotherlastname", "wifemotheroccupation", "wifeMotherResidence", "wifemothercomment", "false", "62y");
        misc.setWitness1("w1firstname", "w1lastname", "w1occupation", "w1comment");
        misc.setWitness2("w2firstname", "w2lastname", "w2occupation", "w2comment");
        misc.setWitness3("w3firstname", "w3lastname", "w3occupation", "w3comment");
        misc.setWitness4("w4firstname", "w4lastname", "w4occupation", "w4comment");

        dataManager.addRecord(misc);
        StringBuilder sb = ReleveFileEgmt.saveFile(dataManager, dataManager.getDataModel(), DataManager.RecordType.misc, file, false);
        assertEquals("verify save error", 0, sb.length());

        FileBuffer fb = ReleveFileEgmt.loadFile(file);
        assertEquals("load result", "", fb.getError().toString());
        assertEquals("load count", 1, fb.getMiscCount());
        RecordMisc misc2 = (RecordMisc) fb.getRecords().get(0);

        assertEquals("EventDate",   misc.getEventDateProperty().toString(),misc2.getEventDateProperty().toString());
        assertEquals("EventType",   "WILL",misc2.getEventType().toString());
        assertEquals("parish",      misc.getParish().toString(),misc2.getParish().toString());
        assertEquals("Notary",      misc.getNotary().toString(),misc2.getNotary().toString());
        assertEquals("Cote",        misc.getCote().toString(),misc2.getCote().toString());
        assertEquals("FreeComment", misc.getFreeComment().toString(),misc2.getFreeComment().toString());

        assertEquals("IndiFirstName",           misc.getIndiFirstName().toString(),misc2.getIndiFirstName().toString());
        assertEquals("IndiLastName",            misc.getIndiLastName().toString(),misc2.getIndiLastName().toString());
        assertEquals("IndiSex",                 misc.getIndiSex().toString(),misc2.getIndiSex().toString());
        assertEquals("IndiAge",                 misc.getIndiAge().toString(),misc2.getIndiAge().toString());
        assertEquals("IndiBirthDate",           "",misc2.getIndiBirthDate().getValue());
        assertEquals("IndiBirthPlace",          "",misc2.getIndiBirthPlace().toString());
        assertEquals("IndiPlace",               "",misc2.getIndiBirthPlace().toString());
        assertEquals("IndiOccupation",          "",misc2.getIndiOccupation().toString());
        assertEquals("IndiComment",             "indicomment, né le 01/01/1980, indiBirthPlace, indioccupation",misc2.getIndiComment().toString());

        assertEquals("IndiMarriedFirstName",    misc.getIndiMarriedFirstName().toString(),misc2.getIndiMarriedFirstName().toString());
        assertEquals("IndiMarriedLastName",     misc.getIndiMarriedLastName().toString(),misc2.getIndiMarriedLastName().toString());
        //assertEquals("WifeSex",                 misc.getIndiMarriedSex().toString(),misc2.getWifeSex().toString());
        //assertEquals("WifeAge",                 misc.getWifeAge().toString(),misc2.getWifeAge().toString());
        //assertEquals("WifeBirthDate",           misc.getIndiMarriedDead().toString(),misc2.getWifeD().toString());
        assertEquals("IndiMarriedDead",         misc.getIndiMarriedDead().toString(),misc2.getIndiMarriedDead().toString());
        //assertEquals("WifeBirthPlace",          "",misc2.getWifeBirthPlace().toString());
        assertEquals("IndiMarriedOccupation",   "",misc2.getIndiMarriedOccupation().toString());
        assertEquals("IndiMarriedResidence",    misc.getIndiMarriedResidence().toString(),misc2.getIndiMarriedResidence().toString());
        assertEquals("IndiMarriedComment",      "indimarriedoccupation, indimarriedcomment",misc2.getIndiMarriedComment().toString());

        assertEquals("IndiFatherFirstName",     misc.getIndiFatherFirstName().toString(),misc2.getIndiFatherFirstName().toString());
        assertEquals("IndiFatherLastName",      misc.getIndiLastName().toString(),misc2.getIndiFatherLastName().toString());
        assertEquals("IndiFatherAge",           "",misc2.getIndiFatherAge().toString());
        assertEquals("IndiFatherDead",          misc.getIndiFatherDead().toString(),misc2.getIndiFatherDead().toString());
        assertEquals("IndiFatherOccupation",    "",misc2.getIndiFatherOccupation().toString());
        assertEquals("IndiFatherComment",       "indifathercomment, indifatheroccupation, indiFatherResidence, Age:70a",misc2.getIndiFatherComment().toString());
        assertEquals("IndiMotherFirstName",     misc.getIndiMotherFirstName().toString(),misc2.getIndiMotherFirstName().toString());
        assertEquals("IndiMotherLastName",      misc.getIndiMotherLastName().toString(),misc2.getIndiMotherLastName().toString());
        assertEquals("IndiMotherAge",           "",misc2.getIndiMotherAge().toString());
        assertEquals("IndiMotherDead",          misc.getIndiMotherDead().toString(),misc2.getIndiMotherDead().toString());
        assertEquals("IndiMotherOccupation",    "",misc2.getIndiMotherOccupation().toString());
        assertEquals("IndiMotherComment",       "indimothercomment, indimotheroccupation, indiMotherResidence, Age:72a",misc2.getIndiMotherComment().toString());

        assertEquals("WifeFirstName",           "",misc2.getWifeFirstName().toString());
        assertEquals("WifeLastName",            "",misc2.getWifeLastName().toString());
        assertEquals("WifeSex",                 "",misc2.getWifeSex().toString());
        assertEquals("WifeAge",                 "",misc2.getWifeAge().toString());
        assertEquals("WifeBirthDate",           "",misc2.getWifeBirthDate().toString());
        assertEquals("WifeBirthPlace",          "",misc2.getWifeBirthPlace().toString());
        assertEquals("WifeOccupation",          "",misc2.getWifeOccupation().toString());
        assertEquals("WifeResidence",           "",misc2.getWifeResidence().toString());
        assertEquals("WifeComment",             "",misc2.getWifeComment().toString());
        assertEquals("WifeMarriedFirstName",    "",misc2.getWifeMarriedFirstName().toString());
        assertEquals("WifeMarriedLastName",     "",misc2.getWifeMarriedLastName().toString());
        assertEquals("WifeMarriedOccupation",   "",misc2.getWifeMarriedOccupation().toString());
        assertEquals("WifeMarriedComment",      "",misc2.getWifeMarriedComment().toString());
        assertEquals("WifeMarriedDead",         "",misc2.getWifeMarriedDead().toString());
        assertEquals("WifeFatherFirstName",     "",misc2.getWifeFatherFirstName().toString());
        assertEquals("WifeFatherLastName",      "",misc2.getWifeFatherLastName().toString());
        assertEquals("WifeFatherAge",           "",misc2.getWifeFatherAge().toString());
        assertEquals("WifeFatherOccupation",    "",misc2.getWifeFatherOccupation().toString());
        assertEquals("WifeFatherComment",       "",misc2.getWifeFatherComment().toString());
        assertEquals("WifeFatherDead",          "",misc2.getWifeFatherDead().toString());
        assertEquals("WifeMotherFirstName",     "",misc2.getWifeMotherFirstName().toString());
        assertEquals("WifeMotherLastName",      "",misc2.getWifeMotherLastName().toString());
        assertEquals("WifeMotherAge",           "",misc2.getWifeMotherAge().toString());
        assertEquals("WifeMotherDead",          "",misc2.getWifeMotherDead().toString());
        assertEquals("WifeMotherOccupation",    "",misc2.getWifeMotherOccupation().toString());
        assertEquals("WifeMotherComment",       "",misc2.getWifeMotherComment().toString());

        assertEquals("Witness1FirstName",     misc.getWitness1FirstName().toString(),misc2.getWitness1FirstName().toString());
        assertEquals("Witness1LastName",      misc.getWitness1LastName().toString(),misc2.getWitness1LastName().toString());
        assertEquals("Witness1Occupation",    "",misc2.getWitness1Occupation().toString());
        assertEquals("Witness1Comment",       "w1comment, w1occupation",misc2.getWitness1Comment().toString());
        assertEquals("Witness2FirstName",     misc.getWitness2FirstName().toString(),misc2.getWitness2FirstName().toString());
        assertEquals("Witness2LastName",      misc.getWitness2LastName().toString(),misc2.getWitness2LastName().toString());
        assertEquals("Witness2Occupation",    "",misc2.getWitness2Occupation().toString());
        assertEquals("Witness2Comment",       "w2comment, w2occupation",misc2.getWitness2Comment().toString());
        assertEquals("Witness3FirstName",     "",misc2.getWitness3FirstName().toString());
        assertEquals("Witness3LastName",      "",misc2.getWitness3LastName().toString());
        assertEquals("Witness3Occupation",    "",misc2.getWitness3Occupation().toString());
        assertEquals("Witness3Comment",       "",misc2.getWitness3Comment().toString());
        assertEquals("Witness4FirstName",     "",misc2.getWitness4FirstName().toString());
        assertEquals("Witness4LastName",      "",misc2.getWitness4LastName().toString());
        assertEquals("Witness4Occupation",    "",misc2.getWitness4Occupation().toString());
        assertEquals("Witness4Comment",       "",misc2.getWitness4Comment().toString());

        assertEquals("GeneralComment", "generalcomment, témoin(s): w3firstname w3lastname w3occupation w3comment, w4firstname w4lastname w4occupation w4comment, Héritier: wifefirstname wifelastname, Age:22a, né le 02/02/1982 wifeBirthPlace, wifecomment, wifeoccupation, wifeResidence, Père de l'héritier: wifefathername wifefatherlastname Age:60a wifefatheroccupation wifeFatherResidence wifefathercomment, Mère de l'héritier: wifemothername wifemotherlastname Age:62a wifemotheroccupation wifeMotherResidence wifemothercomment, Conjoint de l'héritier: wifemarriedfirstname wifemarriedlastname wifemarriedoccupation wifeMarriedResidence wifemothercomment",misc2.getGeneralComment().toString());

        file.delete();

    }

    /**
     * Test of saveFile method, of class ReleveFileEgmt.
     */
    public void testSaveFileMisc() throws Exception {
        File file = new File(System.getProperty("user.home") + File.separator + "testsaveFile.txt");

        DataManager dataManager = new DataManager();
        dataManager.setPlace("");

        RecordMisc misc = new RecordMisc();
        misc.setEventDate("11/01/2000");
        misc.setCote("cote");
        misc.setParish("parish");
        misc.setNotary("Notary");
        misc.setEventType("eventname");
        misc.setGeneralComment("generalcomment");
        misc.setFreeComment("photo");
        misc.setIndi("indifirstname", "indilastname", "M", "24y", "01/01/1980", "indiBirthPlace", "indioccupation", "indiResidence", "indicomment");
        misc.setIndiMarried("indimarriedfirstname", "indimarriedlastname", "indimarriedoccupation", "indiMarriedResidence", "indimarriedcomment", "false");
        misc.setIndiFather("indifathername", "indifatherlastname", "indifatheroccupation", "indiFatherResidence", "indifathercomment", "false", "70y");
        misc.setIndiMother("indimothername", "indimotherlastname", "indimotheroccupation", "indiMotherResidence", "indimothercomment", "false", "72y");
        misc.setWife("wifefirstname", "wifelastname", "F", "22y", "02/02/1982", "wifeBirthPlace", "wifeoccupation", "wifeResidence", "wifecomment");
        misc.setWifeMarried("wifemarriedfirstname", "wifemarriedlastname", "wifemarriedoccupation", "wifeMarriedResidence", "wifemarriedcomment", "true");
        misc.setWifeFather("wifefathername", "wifefatherlastname", "wifefatheroccupation", "wifeFatherResidence", "wifefathercomment", "true", "60y");
        misc.setWifeMother("wifemothername", "wifemotherlastname", "wifemotheroccupation", "wifeMotherResidence", "wifemothercomment", "false", "62y");
        misc.setWitness1("w1firstname", "w1lastname", "w1occupation", "w1comment");
        misc.setWitness2("w2firstname", "w2lastname", "w2occupation", "w2comment");
        misc.setWitness3("w3firstname", "w3lastname", "w3occupation", "w3comment");
        misc.setWitness4("w4firstname", "w4lastname", "w4occupation", "w4comment");

        dataManager.addRecord(misc);
        StringBuilder sb = ReleveFileEgmt.saveFile(dataManager, dataManager.getDataModel(), DataManager.RecordType.misc, file, false);
        assertEquals("verify save error", 0, sb.length());

        FileBuffer fb = ReleveFileEgmt.loadFile(file);
        assertEquals("load result", "", fb.getError().toString());
        assertEquals("load count", 1, fb.getMiscCount());
        RecordMisc misc2 = (RecordMisc) fb.getRecords().get(0);

        assertEquals("EventDate",   misc.getEventDateProperty().toString(),misc2.getEventDateProperty().toString());
        assertEquals("EventType",   misc.getEventType().toString(),misc2.getEventType().toString());
        assertEquals("parish",      misc.getParish().toString(),misc2.getParish().toString());
        assertEquals("Notary",      misc.getNotary().toString(),misc2.getNotary().toString());
        assertEquals("Cote",        misc.getCote().toString(),misc2.getCote().toString());
        assertEquals("FreeComment", misc.getFreeComment().toString(),misc2.getFreeComment().toString());

        assertEquals("IndiFirstName",           misc.getIndiFirstName().toString(),misc2.getIndiFirstName().toString());
        assertEquals("IndiLastName",            misc.getIndiLastName().toString(),misc2.getIndiLastName().toString());
        assertEquals("IndiSex",                 misc.getIndiSex().toString(),misc2.getIndiSex().toString());
        assertEquals("IndiAge",                 misc.getIndiAge().toString(),misc2.getIndiAge().toString());
        assertEquals("IndiBirthDate",           "",misc2.getIndiBirthDate().getValue());
        assertEquals("IndiBirthPlace",          "",misc2.getIndiBirthPlace().toString());
        assertEquals("IndiPlace",               "",misc2.getIndiBirthPlace().toString());
        assertEquals("IndiOccupation",          "",misc2.getIndiOccupation().toString());
        assertEquals("IndiComment",             "indicomment, né le 01/01/1980, indiBirthPlace, indioccupation",misc2.getIndiComment().toString());

        assertEquals("IndiMarriedFirstName",    misc.getIndiMarriedFirstName().toString(),misc2.getIndiMarriedFirstName().toString());
        assertEquals("IndiMarriedLastName",     misc.getIndiMarriedLastName().toString(),misc2.getIndiMarriedLastName().toString());
        assertEquals("IndiMarriedDead",         misc.getIndiMarriedDead().toString(),misc2.getIndiMarriedDead().toString());
        assertEquals("IndiMarriedOccupation",   "",misc2.getIndiMarriedOccupation().toString());
        assertEquals("IndiMarriedResidence",    misc.getIndiMarriedResidence().toString(),misc2.getIndiMarriedResidence().toString());
        assertEquals("IndiMarriedComment",      "indimarriedoccupation, indimarriedcomment",misc2.getIndiMarriedComment().toString());

        assertEquals("IndiFatherFirstName",     misc.getIndiFatherFirstName().toString(),misc2.getIndiFatherFirstName().toString());
        assertEquals("IndiFatherLastName",      misc.getIndiLastName().toString(),misc2.getIndiFatherLastName().toString());
        assertEquals("IndiFatherAge",           "",misc2.getIndiFatherAge().toString());
        assertEquals("IndiFatherDead",          misc.getIndiFatherDead().toString(),misc2.getIndiFatherDead().toString());
        assertEquals("IndiFatherOccupation",    "",misc2.getIndiFatherOccupation().toString());
        assertEquals("IndiFatherComment",       "indifathercomment, indifatheroccupation, indiFatherResidence, Age:70a",misc2.getIndiFatherComment().toString());
        assertEquals("IndiMotherFirstName",     misc.getIndiMotherFirstName().toString(),misc2.getIndiMotherFirstName().toString());
        assertEquals("IndiMotherLastName",      misc.getIndiMotherLastName().toString(),misc2.getIndiMotherLastName().toString());
        assertEquals("IndiMotherAge",           "",misc2.getIndiMotherAge().toString());
        assertEquals("IndiMotherDead",          misc.getIndiMotherDead().toString(),misc2.getIndiMotherDead().toString());
        assertEquals("IndiMotherOccupation",    "",misc2.getIndiMotherOccupation().toString());
        assertEquals("IndiMotherComment",      "indimothercomment, indimotheroccupation, indiMotherResidence, Age:72a",misc2.getIndiMotherComment().toString());

        assertEquals("WifeFirstName",           "",misc2.getWifeFirstName().toString());
        assertEquals("WifeLastName",            "",misc2.getWifeLastName().toString());
        assertEquals("WifeSex",                 "",misc2.getWifeSex().toString());
        assertEquals("WifeAge",                 "",misc2.getWifeAge().toString());
        assertEquals("WifeBirthDate",           "",misc2.getWifeBirthDate().toString());
        assertEquals("WifeBirthPlace",          "",misc2.getWifeBirthPlace().toString());
        assertEquals("WifeOccupation",          "",misc2.getWifeOccupation().toString());
        assertEquals("WifeResidence",           "",misc2.getWifeResidence().toString());
        assertEquals("WifeComment",             "",misc2.getWifeComment().toString());
        assertEquals("WifeMarriedFirstName",    "",misc2.getWifeMarriedFirstName().toString());
        assertEquals("WifeMarriedLastName",     "",misc2.getWifeMarriedLastName().toString());
        assertEquals("WifeMarriedOccupation",   "",misc2.getWifeMarriedOccupation().toString());
        assertEquals("WifeMarriedComment",      "",misc2.getWifeMarriedComment().toString());
        assertEquals("WifeMarriedDead",         "",misc2.getWifeMarriedDead().toString());
        assertEquals("WifeFatherFirstName",     "",misc2.getWifeFatherFirstName().toString());
        assertEquals("WifeFatherLastName",      "",misc2.getWifeFatherLastName().toString());
        assertEquals("WifeFatherAge",           "",misc2.getWifeFatherAge().toString());
        assertEquals("WifeFatherOccupation",    "",misc2.getWifeFatherOccupation().toString());
        assertEquals("WifeFatherComment",       "",misc2.getWifeFatherComment().toString());
        assertEquals("WifeFatherDead",          "",misc2.getWifeFatherDead().toString());
        assertEquals("WifeMotherFirstName",     "",misc2.getWifeMotherFirstName().toString());
        assertEquals("WifeMotherLastName",      "",misc2.getWifeMotherLastName().toString());
        assertEquals("WifeMotherAge",           "",misc2.getWifeMotherAge().toString());
        assertEquals("WifeMotherDead",          "",misc2.getWifeMotherDead().toString());
        assertEquals("WifeMotherOccupation",    "",misc2.getWifeMotherOccupation().toString());
        assertEquals("WifeMotherComment",       "",misc2.getWifeMotherComment().toString());

        assertEquals("Witness1FirstName",     misc.getWitness1FirstName().toString(),misc2.getWitness1FirstName().toString());
        assertEquals("Witness1LastName",      misc.getWitness1LastName().toString(),misc2.getWitness1LastName().toString());
        assertEquals("Witness1Occupation",    "",misc2.getWitness1Occupation().toString());
        assertEquals("Witness1Comment",       "w1comment, w1occupation",misc2.getWitness1Comment().toString());
        assertEquals("Witness2FirstName",     misc.getWitness2FirstName().toString(),misc2.getWitness2FirstName().toString());
        assertEquals("Witness2LastName",      misc.getWitness2LastName().toString(),misc2.getWitness2LastName().toString());
        assertEquals("Witness2Occupation",    "",misc2.getWitness2Occupation().toString());
        assertEquals("Witness2Comment",       "w2comment, w2occupation",misc2.getWitness2Comment().toString());
        assertEquals("Witness3FirstName",     "",misc2.getWitness3FirstName().toString());
        assertEquals("Witness3LastName",      "",misc2.getWitness3LastName().toString());
        assertEquals("Witness3Occupation",    "",misc2.getWitness3Occupation().toString());
        assertEquals("Witness3Comment",       "",misc2.getWitness3Comment().toString());
        assertEquals("Witness4FirstName",     "",misc2.getWitness4FirstName().toString());
        assertEquals("Witness4LastName",      "",misc2.getWitness4LastName().toString());
        assertEquals("Witness4Occupation",    "",misc2.getWitness4Occupation().toString());
        assertEquals("Witness4Comment",       "",misc2.getWitness4Comment().toString());

        assertEquals("GeneralComment", "generalcomment, Autre intervenant: wifefirstname wifelastname, Age:22a, né le 02/02/1982 wifeBirthPlace, wifecomment, wifeoccupation, wifeResidence, Père de l'intervenant: wifefathername wifefatherlastname Age:60a Décédé wifefatheroccupation wifeFatherResidence wifefathercomment, Mère de l'intervenant: wifemothername wifemotherlastname Age:62a wifemotheroccupation wifeMotherResidence wifemothercomment, Conjoint de l'intervenant: wifemarriedfirstname wifemarriedlastname Décédé wifemarriedoccupation wifeMarriedResidence wifemothercomment, témoin(s): w3firstname w3lastname w3occupation w3comment, w4firstname w4lastname w4occupation w4comment",misc2.getGeneralComment().toString());

        file.delete();

    }


     public void testFormatAgeToField() throws Exception {

         assertEquals("formatAgeToField 76", "76y", ReleveFileEgmt.formatAgeToField("76"));
         assertEquals("formatAgeToField 76", "76y", ReleveFileEgmt.formatAgeToField("76 ans"));
         assertEquals("formatAgeToField 76", "8d", ReleveFileEgmt.formatAgeToField("8 jours"));

     }

}
