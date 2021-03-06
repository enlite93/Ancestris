package ancestris.modules.releve.dnd;

import ancestris.modules.releve.dnd.MergeRecord.MergeParticipantType;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.PropertyDate;
import genj.gedcom.Source;
import genj.gedcom.TagPath;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.NbBundle;

/**
 *
 */
class MergeModelBirth extends MergeModel {

    private Indi currentIndi;

    /**
     * model factory
     * cree un liste contenant un modele comparant le releve et l'entité
     * selectionnée dans le gedcom.
     * Si selectedEntity = null, la liste contient les modeles comparant le relevé
     * avec les entités du gedcom dont les noms, prenoms, et dates de naissance,
     * mariage et décès sont compatibles avec le relevé.
     * @param mergeRecord   releve
     * @param gedcom
     * @param selectedEntity entité sélectionnée dans le gedcom
     * @return
     */
    static protected List<MergeModel> createMergeModelBirth (MergeRecord mergeRecord, Gedcom gedcom, Entity selectedEntity, boolean showNewParents) throws Exception {
        List<MergeModel> models = new ArrayList<MergeModel>();
        if (selectedEntity instanceof Fam) {
            // 1.1) Record Birth : l'entité selectionnée est une famille
            Fam family = (Fam) selectedEntity;
            // j'ajoute un nouvel individu
            models.add(new MergeModelBirth(mergeRecord, gedcom, null, family));
            // je recherche les enfants de la famille sélectionnée compatibles avec le releve
            List<Indi> sameChildren = MergeQuery.findSameChild(mergeRecord, gedcom, family);
            // j'ajoute les enfants compatibles
            for (Indi samedIndi : sameChildren) {
                models.add(new MergeModelBirth(mergeRecord, gedcom, samedIndi, samedIndi.getFamilyWhereBiologicalChild()));
            }
        } else if (selectedEntity instanceof Indi) {
            // 1.2) Record Birth : l'entité selectionnée est un individu
            Indi selectedIndi = (Indi) selectedEntity;

            // je cherche les familles compatibles avec le releve de naissance
            List<Fam> families = MergeQuery.findFamilyCompatibleWithParticipantParents(mergeRecord, MergeParticipantType.participant1, gedcom);

            // j'ajoute l'individu selectionné par dnd
            if (selectedIndi.getFamilyWhereBiologicalChild() != null) {
                // j'ajoute l'individu selectionné par dnd
                models.add(new MergeModelBirth(mergeRecord, gedcom, selectedIndi, selectedIndi.getFamilyWhereBiologicalChild()));
            } else {
                models.add(new MergeModelBirth(mergeRecord, gedcom, selectedIndi, (Fam) null));
                // j'ajoute l'individu selectionné par dnd avec les familles compatibles
                for (Fam family : families) {
                    models.add(new MergeModelBirth(mergeRecord, gedcom, selectedIndi, family));
                }
            }

            // je recupere les individus compatibles avec le relevé (qui portent le meme nom que le nom qui est dans le
            // releve et avec les dates de naissance compatibles et les parents compatibles)
            // en excluant l'individu selectionne s'il a deja une famille
            List<Indi> sameIndis;
            if (selectedIndi.getFamilyWhereBiologicalChild() != null) {
                // l'individu est lié a une famille précise, je l'exclue de la recherche
                sameIndis = MergeQuery.findIndiCompatibleWithParticipant(mergeRecord, MergeParticipantType.participant1, gedcom, selectedIndi);
            } else {
                // l'individu n'est pas lié a une famille précise, je l'inclue dans la recherche
                sameIndis = MergeQuery.findIndiCompatibleWithParticipant(mergeRecord, MergeParticipantType.participant1, gedcom, null);
            }
            // j'ajoute les individus compatibles
            for (Indi samedIndi : sameIndis) {
                // j'ajoute les familles compatibles
                Fam sameIndiFamily = samedIndi.getFamilyWhereBiologicalChild();
                if (sameIndiFamily != null) {
                    models.add(new MergeModelBirth(mergeRecord, gedcom, samedIndi, sameIndiFamily));
                } else {
                    for (Fam family : families) {
                        models.add(new MergeModelBirth(mergeRecord, gedcom, samedIndi, family));
                    }
                }
            }

        } else {
            // 1.3) Record Birth : pas d'entité selectionnee

            // j'ajoute un nouvel individu , sans famille associée
            models.add(new MergeModelBirth(mergeRecord, gedcom));

            // je recupere les individus compatibles avec le relevé (qui portent le meme nom que le nom qui est dans le
            // releve et avec les dates de naissance compatibles et les parents compatibles)
            List<Indi> sameIndis = MergeQuery.findIndiCompatibleWithParticipant(mergeRecord, MergeParticipantType.participant1, gedcom, null);

            // je cherche les familles des parents compatibles avec le releve de naissance
            List<Fam> families = MergeQuery.findFamilyCompatibleWithParticipantParents(mergeRecord, MergeParticipantType.participant1, gedcom);

            // j'ajoute un nouvel individu avec les familles compatibles
            for (Fam family : families) {
                models.add(new MergeModelBirth(mergeRecord, gedcom, null, family));
            }

            // j'ajoute les individus compatibles avec la famille de chacun
            for (Indi samedIndi : sameIndis) {
                Fam sameIndiFamily = samedIndi.getFamilyWhereBiologicalChild();
                if (sameIndiFamily != null) {
                    // j'ajoute l'individus compatible avec sa famille
                    models.add(new MergeModelBirth(mergeRecord, gedcom, samedIndi, sameIndiFamily));
                } else {
                    // j'ajoute l'individus compatible sans famille
                    models.add(new MergeModelBirth(mergeRecord, gedcom, samedIndi, (Fam) null));
                    // j'ajoute l'individus compatible avec les familles compatibles
                    for (Fam family : families) {
                        models.add(new MergeModelBirth(mergeRecord, gedcom, samedIndi, family));
                    }
                }
            }

            if (showNewParents) {
                // j'ajoute un nouvel individu avec les couples qui ne sont pas des familles
                // mais qui pourraient être ses parents
                List<Indi> fathers = new ArrayList<Indi>();
                List<Indi> mothers = new ArrayList<Indi>();
                MergeQuery.findFatherMotherCompatibleWithBirthParticipant(mergeRecord, gedcom, families, fathers, mothers);
                for (Indi father : fathers) {
                    for (Indi mother : mothers) {
                        models.add(new MergeModelBirth(mergeRecord, gedcom, father, mother));
                    }
                }
            }
        }
        return models;
    }

    /**
     * le constucteur initialise les données du modele
     * en comparant les champs du releve et les
     *
     * @param indi
     * @param record
     */
    protected MergeModelBirth(MergeRecord record, Gedcom gedcom) throws Exception {
        this(record, gedcom, null, (Fam) null);        
    }

    /**
     * le constucteur initialise les données du modele
     * en comparant les champs du releve et les
     *
     * @param indi
     * @param record
     */
    protected MergeModelBirth(MergeRecord record, Gedcom gedcom, Indi indi, Fam fam) throws Exception {
        super(record, indi, gedcom);
        this.currentIndi = indi;
        addRowSource();        
        addRowIndi();
        addRowParents(fam);
    }
    
    /**
     * le constucteur initialise les données du modele
     * en comparant les champs du releve et les
     *
     * @param indi
     * @param record
     */
    protected MergeModelBirth(MergeRecord record, Gedcom gedcom, Indi father, Indi mother ) throws Exception {
        super(record, null, gedcom);
        this.currentIndi = null;

        addRowSource();        
        addRowIndi();

        // j'affiche la famille de l'enfant
        addRow(RowType.IndiParentFamily, null);
        addRow(RowType.IndiParentMarriageDate, record.getIndi().getParentMarriageDate(), null);

        // j'affiche les parents
        addRowFather(father);
        addRowMother(mother);
    }

    private void addRowIndi() throws Exception {
       if (currentIndi != null) {            
            // j'affiche le nom
            addRow(RowType.IndiLastName, record.getIndi().getLastName(), currentIndi.getLastName(), currentIndi);
            addRow(RowType.IndiFirstName, record.getIndi().getFirstName(), currentIndi.getFirstName());
            addRow(RowType.IndiSex, record.getIndi().getSexString(), currentIndi.getPropertyValue("SEX"));
            addRow(RowType.IndiBirthDate, record.getIndi().getBirthDate() , currentIndi.getBirthDate());
            addRow(RowType.IndiBirthPlace, record.getIndi().getBirthPlace(), currentIndi.getValue(new TagPath("INDI:BIRT:PLAC"), ""));
            addRow(RowType.EventComment, record.getEventComment(showFrenchCalendarDate), currentIndi.getValue(new TagPath("INDI:BIRT:NOTE"), ""));
            // j'affiche un separateur
            addRowSeparator();

        } else {
            // selectedIndi est nul
            // j'affiche le nom
            addRow(RowType.IndiLastName, record.getIndi().getLastName(), "");
            addRow(RowType.IndiFirstName, record.getIndi().getFirstName(), "");
            addRow(RowType.IndiSex, record.getIndi().getSexString(), "");
            addRow(RowType.IndiBirthDate, record.getIndi().getBirthDate() , null);
            addRow(RowType.IndiBirthPlace, record.getIndi().getBirthPlace(), "");
            addRow(RowType.EventComment, record.getEventComment(showFrenchCalendarDate), "");
            // j'affiche un separateur
            addRowSeparator();
        }
    }

    private void addRowParents( Fam fam) throws Exception {
        addRow(RowType.IndiParentFamily, fam);
        if (fam != null) {
            addRow(RowType.IndiParentMarriageDate, record.getIndi().getParentMarriageDate(), fam.getMarriageDate());
            addRowFather(fam.getHusband());
            addRowMother(fam.getWife());
        } else {
            addRow(RowType.IndiParentMarriageDate, record.getIndi().getParentMarriageDate(), null);
            addRowFather(null);
            addRowMother(null);
        }
    }

    private void addRowFather( Indi father ) throws Exception {
        if (father != null) {
            addRow(RowType.IndiFatherLastName, record.getIndi().getFatherLastName(), father.getLastName(), father);
            addRow(RowType.IndiFatherFirstName, record.getIndi().getFatherFirstName(), father.getFirstName());
            addRow(RowType.IndiFatherBirthDate, record.getIndi().getFatherBirthDate(), father.getBirthDate());
            addRow(RowType.IndiFatherDeathDate, record.getIndi().getFatherDeathDate(), father.getDeathDate());
            addRow(RowType.IndiFatherOccupation, record.getIndi().getFatherOccupationWithDate(), MergeQuery.findOccupation(father, record.getEventDate()));

        } else {
            addRow(RowType.IndiFatherLastName, record.getIndi().getFatherLastName(), "");
            addRow(RowType.IndiFatherFirstName, record.getIndi().getFatherFirstName(), "");
            addRow(RowType.IndiFatherBirthDate, record.getIndi().getFatherBirthDate(), null);
            addRow(RowType.IndiFatherDeathDate, record.getIndi().getFatherDeathDate(), null);
            addRow(RowType.IndiFatherOccupation, record.getIndi().getFatherOccupationWithDate(), "");
        }
    }

    private void addRowMother( Indi mother ) throws Exception {
        if (mother != null) {
            addRow(RowType.IndiMotherLastName, record.getIndi().getMotherLastName(), mother.getLastName(), mother);
            addRow(RowType.IndiMotherFirstName, record.getIndi().getMotherFirstName(), mother.getFirstName());
            addRow(RowType.IndiMotherBirthDate, record.getIndi().getMotherBirthDate(), mother.getBirthDate());
            addRow(RowType.IndiMotherDeathDate, record.getIndi().getMotherDeathDate(), mother.getDeathDate());
            addRow(RowType.IndiMotherOccupation, record.getIndi().getMotherOccupationWithDate(), MergeQuery.findOccupation(mother, record.getEventDate()));
        } else {
            addRow(RowType.IndiMotherLastName, record.getIndi().getMotherLastName(), "");
            addRow(RowType.IndiMotherFirstName, record.getIndi().getMotherFirstName(), "");
            addRow(RowType.IndiMotherBirthDate, record.getIndi().getMotherBirthDate(), null);
            addRow(RowType.IndiMotherDeathDate, record.getIndi().getMotherDeathDate(), null);
            addRow(RowType.IndiMotherOccupation, record.getIndi().getMotherOccupationWithDate(), "");
        }
    }

    /**
     * retourne l'individu selectionné 
     * @return individu selectionné ou null si c'est un nouvel individu
     */
    @Override
    protected Entity getSelectedEntity() {
        return currentIndi;
    }

    /**
     * retourne l'individu proposé dans le modele
     * @return 
     */
    @Override
    protected Entity getProposedEntity() {
        if (currentIndi != null)  {
            return currentIndi;
        } else {
            Entity family = getEntityObject(MergeModel.RowType.IndiParentFamily);
            return family; 
        }
    }

    /**
     * copie les données du relevé dans l'entité
     */
    @Override
    protected Property copyRecordToEntity() throws Exception {
        Property resultProperty;
        
        if (currentIndi == null) {
            currentIndi = (Indi) gedcom.createEntity(Gedcom.INDI);
            currentIndi.setName(record.getIndi().getFirstName(), record.getIndi().getLastName());
            currentIndi.setSex(record.getIndi().getSex());
        } else {
            // je copie le nom du releve dans l'individu
            if (isChecked(RowType.IndiLastName)) {
                currentIndi.setName(currentIndi.getFirstName(), record.getIndi().getLastName());
            }

            // je copie le prénom du releve dans l'individu
            if (isChecked(RowType.IndiFirstName)) {
                currentIndi.setName(record.getIndi().getFirstName(), currentIndi.getLastName());
            }

            // je copie le sexe du releve dans l'individu
            if (isChecked(RowType.IndiSex)) {
                currentIndi.setSex(record.getIndi().getSex());
            }
        }
        
        // je cree la propriete de naissance si elle n'existait pas
        Property birthProperty = currentIndi.getProperty("BIRT");
        if (isChecked(RowType.IndiBirthDate) || isChecked(RowType.EventSource) || isChecked(RowType.EventComment)) {
            if (birthProperty == null) {
                birthProperty = currentIndi.addProperty("BIRT", "");
            }
        }
        
        resultProperty = birthProperty;

        // je copie la date de naissance du releve dans l'individu
        if (isChecked(RowType.IndiBirthDate)) {
            // j'ajoute (ou remplace) la date de la naissance
            PropertyDate propertyDate = currentIndi.getBirthDate(true);
            propertyDate.setValue(record.getIndi().getBirthDate().getValue());
        }

        // je copie la source de la naissance du releve dans l'individu
        if (isChecked(RowType.EventSource) || isChecked(RowType.EventPage)) {
            copySource((Source) getEntityObject(RowType.EventSource), birthProperty, isChecked(RowType.EventPage), record);
        }
        
        // je copie le lieu de la naissance .
        if (isChecked(RowType.IndiBirthPlace)) {
            copyPlace(record.getIndi().getBirthPlace(),  birthProperty);
        }

        // je copie le commentaire de la naissance .
        if (isChecked(RowType.EventComment)) {
            Property propertyNote = birthProperty.getProperty("NOTE");
            if (propertyNote == null) {
                // je cree une note .
                propertyNote = birthProperty.addProperty("NOTE", "");
            }

            // j'ajoute le commentaire de la naissance au debut de la note existante.
            String value = propertyNote.getValue();
            String comment = record.getEventComment(showFrenchCalendarDate);
            if (!comment.isEmpty()) {
                if (!value.isEmpty()) {
                    comment += "\n";
                }
                comment += value;
                propertyNote.setValue(comment);
            }
        }

        // je copie les données des parents
        if (isChecked(RowType.IndiParentFamily)) {
            // je copie la famille des parents
            Fam family = (Fam) getEntityObject(RowType.IndiParentFamily);
            if (family == null) {
                // je cree la famille
                family = (Fam) gedcom.createEntity(Gedcom.FAM);
            }

            // j'ajoute l'enfant dans la famille si ce n'est pas déja le cas
            if (!currentIndi.isDescendantOf(family)) {
                family.addChild(currentIndi);
            }

            // je copie la date du mariage et une note indiquant l'origine de cette date
            if (isChecked(RowType.IndiParentMarriageDate)) {                
                copyMarriageDate(family, getRow(RowType.IndiParentMarriageDate), record );
            }

            copyIndiFather(record.getIndi(), family.getHusband(), family);
//            // je copie le nom et le prenom du pere
//            Indi father = family.getHusband();
//            if (father == null) {
//                // je cree le pere
//                father = (Indi) gedcom.createEntity(Gedcom.INDI);
//                father.setName(record.getIndi().getFatherFirstName(), record.getIndi().getFatherLastName());
//                father.setSex(PropertySex.MALE);
//                family.setHusband(father);
//            } else {
//                if (isChecked(RowType.IndiFatherFirstName)) {
//                    father.setName(record.getIndi().getFatherFirstName(), father.getLastName());
//                }
//                if (isChecked(RowType.IndiFatherLastName)) {
//                    father.setName(father.getFirstName(), record.getIndi().getFatherLastName());
//                }
//            }
//
//            // je copie la date de naissance du pere
//            if (isChecked(RowType.IndiFatherBirthDate)) {
//                copyBirthDate(father, getRow(RowType.IndiFatherBirthDate), "", record);
//            }
//
//            // je copie la date de décès du pere
//            if (isChecked(RowType.IndiFatherDeathDate)) {
//                copyDeathDate(father, getRow(RowType.IndiFatherDeathDate), "", record);
//            }
//
//            // je copie la profession du pere
//            if (isChecked(RowType.IndiFatherOccupation)) {
//                copyOccupation(father, record.getIndi().getFatherOccupation(), record.getIndi().getFatherResidence(), true, record);
//            }            

            copyIndiMother(record.getIndi(), family.getWife(), family);
            // je copie le nom et le prenom de la mere
//            Indi mother = family.getWife();
//            if (mother == null) {
//                // je cree la mere
//                mother = (Indi) gedcom.createEntity(Gedcom.INDI);
//                mother.setName(record.getIndi().getMotherFirstName(), record.getIndi().getMotherLastName());
//                mother.setSex(PropertySex.FEMALE);
//                family.setWife(mother);
//            } else {
//                if (isChecked(RowType.IndiMotherFirstName)) {
//                    mother.setName(record.getIndi().getMotherFirstName(), mother.getLastName());
//                }
//                if (isChecked(RowType.IndiMotherLastName)) {
//                    mother.setName(mother.getFirstName(), record.getIndi().getMotherLastName());
//                }
//            }
//
//            // je copie la date de naissance de la mere
//            if (isChecked(RowType.IndiMotherBirthDate)) {
//                copyBirthDate(mother, getRow(RowType.IndiMotherBirthDate), "", record);
//            }
//
//            // je copie la date de décès de la mere
//            if (isChecked(RowType.IndiMotherDeathDate)) {
//                copyDeathDate(mother, getRow(RowType.IndiMotherDeathDate), "", record);
//            }
//
//            // je met à jour la profession de la mere
//            if (isChecked(RowType.IndiMotherOccupation)) {
//                copyOccupation(mother, record.getIndi().getMotherOccupation(), record.getIndi().getMotherResidence(), true, record);
//            }
//            
        }
        return resultProperty;
    }

    /**
     * retourne une chaine de caracteres contenant le nom, prénom et la date du relevé
     * Cette chaine sert de titre a la fenetre principale
     * @return
     */
    @Override
    protected String getTitle() {
        String message = record.getIndi().getFirstName() + " "+ record.getIndi().getLastName()+ " " + record.getEventDate().getDisplayValue();
        return MessageFormat.format(NbBundle.getMessage(MergeDialog.class, "MergeModel.title.birth"), message);
    }

    /**
     * retourne un resumé du modele
     * Cette chaine sert de commentaire dans la liste des modeles
     * @return
     */
    @Override
    public String getSummary(Entity selectedEntity) {
        String summary;
        if ( currentIndi == null ) {
                summary = "Nouvel enfant" + " - ";
                if (getEntityObject(MergeModel.RowType.IndiParentFamily) != null) {
                    summary += getEntityObject(MergeModel.RowType.IndiParentFamily).toString(false);
                } else {
                    if (getEntityObject(MergeModel.RowType.IndiFatherLastName) != null
                            || getEntityObject(MergeModel.RowType.IndiMotherLastName) != null) {
                        summary += "Nouveau couple:" + " ";
                        if (getEntityObject(MergeModel.RowType.IndiFatherLastName) != null) {
                            summary += getEntityObject(MergeModel.RowType.IndiFatherLastName).toString(true);
                        } else {
                            summary += "Nouveau père";
                        }
                        summary += " , ";
                        if (getEntityObject(MergeModel.RowType.IndiMotherLastName) != null) {
                            summary += getEntityObject(MergeModel.RowType.IndiMotherLastName).toString(true);
                        } else {
                            summary += "Nouvelle mère";
                        }

                    } else {
                        if( isChecked(MergeModel.RowType.IndiParentFamily)) {
                            summary += "Nouveaux parents";
                        } else {
                            summary += "Sans parents";
                        }

                    }
                }
        } else {
            if (selectedEntity instanceof Fam) {
                //summary = "Nouvel enfant de la famille sélectionnée";
                // l'enfant est déjà descendant la famille dans le gedcom
                summary = "Modifier "+ currentIndi.toString(true);
            } else {
                if ( getEntityObject(RowType.IndiParentFamily) == null ) {
                    // l'enfant n'a pas de famille dans le gedcom
                    summary = "Modifier "+ currentIndi.toString(true) + " - nouvelle famille" ;
                } else {
                    // l'enfant a une famille dans le gedcom
                    if( currentIndi.isDescendantOf((Fam)getEntityObject(RowType.IndiParentFamily))) {
                        // l'enfant est déjà descendant la famille dans le gedcom
                        summary = "Modifier "+ currentIndi.toString(true);
                    } else {
                        // l'enfant n'est pas encore descendant de la famille dans le gedcom
                        summary = "Modifier "+ currentIndi.toString(true) + " - ajout filiation avec " + (Fam)getEntityObject(RowType.IndiParentFamily);
                    }
                }
            }
        }
        return summary;
    }
}
