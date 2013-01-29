package ancestris.modules.releve.model;

/**
 *
 * @author Michel
 */
public class ModelMisc extends ModelAbstract {

    final String columnName[] = {
        java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.column.Id"),
        java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.column.Date"),
        java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.column.EventType"),
        java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.column.Participant1"),
        java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.column.Participant2"),
        java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.column.Picture")
    };
    final Class columnType[] = {Integer.class, FieldDate.class, String.class, String.class, String.class, FieldPicture.class};

    /**
     * Constructor
     * @param dataManager
     */
    public ModelMisc() {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Implement ModelAbstract methods
    ///////////////////////////////////////////////////////////////////////////
    /**
     * ajout un nouveau releve dans le modele
     * @return indexRecord
     */
    @Override
    public RecordMisc createRecord() {
        return new RecordMisc();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Implement AbstractTableModel methods
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnName[col];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return columnType[column];
    }
    

//    @Override
//    public Record getRowRoot(int i) {
//        return getRecord(i);
//    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        switch (col) {
            case 0:
                value = new Integer(getRecord(row).recordNo);
                break;
            case 1:
                value = getRecord(row).getEventDateProperty();
                break;
            case 2:
                value = getRecord(row).getEventType().getName();
                break;
            case 3:
                value = getRecord(row).getIndiLastName().toString() + " " + getRecord(row).getIndiFirstName().toString();
                break;
            case 4:
                value = getRecord(row).getWifeLastName().toString() + " " + getRecord(row).getWifeFirstName().toString();
                break;
            case 5:
                value = getRecord(row).getFreeComment();
                break;
            default:
                value = "";
                break;
        }
        return value;
    }

    
//    /**
//     * retourne la liste des champs affichables
//     * @return
//     */
//
//    @Override
//    public BeanField[] getFieldList( int recordIndex ) {
//
//        Record record = getRecord(recordIndex);
//
//        if( record == null)  {
//            return new BeanField[0];
//        }
//
//         return getFieldList(record);
//    }
//
//    /**
//     * retourne la liste des bean a afficher dans l'editeur
//     * @param record
//     * @return
//     */
//    protected static BeanField[] getFieldList( Record record ) {
//
//        BeanField beanField[] = {
//            //new BeanField(record, record.getCityName() + "," + record.getCityCode() + "," + record.getCountyName(), null),
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Misc"), ks1),
//            new BeanField(record, Field.FieldType.eventType),
//            new BeanField(record, Field.FieldType.eventDate),
//            new BeanField(record, Field.FieldType.parish),
//            new BeanField(record, Field.FieldType.cote),
//            new BeanField(record, Field.FieldType.freeComment),
//            new BeanField(record, Field.FieldType.notary),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Participant1"), ks2),
//            new BeanField(record, Field.FieldType.indiLastName),
//            new BeanField(record, Field.FieldType.indiFirstName),
//            new BeanField(record, Field.FieldType.indiSex),
//            new BeanField(record, Field.FieldType.indiAge),
//            new BeanField(record, Field.FieldType.indiBirthDate),
//            new BeanField(record, Field.FieldType.indiBirthPlace),
//            new BeanField(record, Field.FieldType.indiOccupation),
//            new BeanField(record, Field.FieldType.indiResidence),
//            new BeanField(record, Field.FieldType.indiComment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.ExMarried"), null),
//            new BeanField(record, Field.FieldType.indiMarriedLastName),
//            new BeanField(record, Field.FieldType.indiMarriedFirstName),
//            new BeanField(record, Field.FieldType.indiMarriedDead),
//            new BeanField(record, Field.FieldType.indiMarriedOccupation),
//            new BeanField(record, Field.FieldType.indiMarriedResidence),
//            new BeanField(record, Field.FieldType.indiMarriedComment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Father"), ks3),
//            new BeanField(record, Field.FieldType.indiFatherLastName),
//            new BeanField(record, Field.FieldType.indiFatherFirstName),
//            new BeanField(record, Field.FieldType.indiFatherAge),
//            new BeanField(record, Field.FieldType.indiFatherDead),
//            new BeanField(record, Field.FieldType.indiFatherOccupation),
//            new BeanField(record, Field.FieldType.indiFatherResidence),
//            new BeanField(record, Field.FieldType.indiFatherComment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Mother"), ks4),
//            new BeanField(record, Field.FieldType.indiMotherLastName),
//            new BeanField(record, Field.FieldType.indiMotherFirstName),
//            new BeanField(record, Field.FieldType.indiMotherAge),
//            new BeanField(record, Field.FieldType.indiMotherDead),
//            new BeanField(record, Field.FieldType.indiMotherOccupation),
//            new BeanField(record, Field.FieldType.indiMotherResidence),
//            new BeanField(record, Field.FieldType.indiMotherComment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Participant2"), ks5),
//            new BeanField(record, Field.FieldType.wifeLastName),
//            new BeanField(record, Field.FieldType.wifeFirstName),
//            new BeanField(record, Field.FieldType.wifeSex),
//            new BeanField(record, Field.FieldType.wifeAge),
//            new BeanField(record, Field.FieldType.wifeBirthDate),
//            new BeanField(record, Field.FieldType.wifePlace),
//            new BeanField(record, Field.FieldType.wifeOccupation),
//            new BeanField(record, Field.FieldType.wifeResidence),
//            new BeanField(record, Field.FieldType.wifeComment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.ExMarried"), null),
//            new BeanField(record, Field.FieldType.wifeMarriedLastName),
//            new BeanField(record, Field.FieldType.wifeMarriedFirstName),
//            new BeanField(record, Field.FieldType.wifeMarriedDead),
//            new BeanField(record, Field.FieldType.wifeMarriedOccupation),
//            new BeanField(record, Field.FieldType.wifeMarriedResidence),
//            new BeanField(record, Field.FieldType.wifeMarriedComment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Father"), ks6),
//            new BeanField(record, Field.FieldType.wifeFatherLastName),
//            new BeanField(record, Field.FieldType.wifeFatherFirstName),
//            new BeanField(record, Field.FieldType.wifeFatherAge),
//            new BeanField(record, Field.FieldType.wifeFatherDead),
//            new BeanField(record, Field.FieldType.wifeFatherOccupation),
//            new BeanField(record, Field.FieldType.wifeFatherResidence),
//            new BeanField(record, Field.FieldType.wifeFatherComment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Mother"), ks7),
//            new BeanField(record, Field.FieldType.wifeMotherLastName),
//            new BeanField(record, Field.FieldType.wifeMotherFirstName),
//            new BeanField(record, Field.FieldType.wifeMotherAge),
//            new BeanField(record, Field.FieldType.wifeMotherDead),
//            new BeanField(record, Field.FieldType.wifeMotherOccupation),
//            new BeanField(record, Field.FieldType.wifeMotherResidence),
//            new BeanField(record, Field.FieldType.wifeMotherComment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Witness1"), ks8),
//            new BeanField(record, Field.FieldType.witness1LastName),
//            new BeanField(record, Field.FieldType.witness1FirstName),
//            new BeanField(record, Field.FieldType.witness1Occupation),
//            new BeanField(record, Field.FieldType.witness1Comment),
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Witness1"), null),
//            new BeanField(record, Field.FieldType.witness2LastName),
//            new BeanField(record, Field.FieldType.witness2FirstName),
//            new BeanField(record, Field.FieldType.witness2Occupation),
//            new BeanField(record, Field.FieldType.witness2Comment),
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Witness3"), null),
//            new BeanField(record, Field.FieldType.witness3LastName),
//            new BeanField(record, Field.FieldType.witness3FirstName),
//            new BeanField(record, Field.FieldType.witness3Occupation),
//            new BeanField(record, Field.FieldType.witness3Comment),
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.Witness4"), null),
//            new BeanField(record, Field.FieldType.witness4LastName),
//            new BeanField(record, Field.FieldType.witness4FirstName),
//            new BeanField(record, Field.FieldType.witness4Occupation),
//            new BeanField(record, Field.FieldType.witness4Comment),
//
//            new BeanField(record, java.util.ResourceBundle.getBundle("ancestris/modules/releve/model/Bundle").getString("model.row.GeneralComment"), ks9),
//            new BeanField(record, Field.FieldType.generalComment)
//        };
//
//        return beanField;
//    }
//
}
