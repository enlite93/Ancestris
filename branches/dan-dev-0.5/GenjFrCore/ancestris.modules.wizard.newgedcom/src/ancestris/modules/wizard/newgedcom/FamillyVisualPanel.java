/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2011 Ancestris
 * 
 * Author: Daniel Andre (daniel@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.wizard.newgedcom;

import ancestris.modules.beans.ABluePrintBeans;
import ancestris.modules.beans.AFamBean;
import ancestris.modules.beans.AIndiBean;
import genj.edit.actions.CreateChild;
import genj.edit.actions.CreateParent;
import genj.edit.actions.CreateSpouse;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.GedcomException;
import genj.gedcom.Indi;
import genj.gedcom.PropertySex;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;

public final class FamillyVisualPanel extends JPanel implements NewGedcomSteps {

    private final static String EMPTY_BP = org.openide.util.NbBundle.getMessage(FamillyVisualPanel.class, "blueprint.empty");
    private final static String WIFE_EMPTY_BP = org.openide.util.NbBundle.getMessage(FamillyVisualPanel.class, "blueprint.wife.empty");
    private final static String HUSBAND_EMPTY_BP = org.openide.util.NbBundle.getMessage(FamillyVisualPanel.class, "blueprint.husband.empty");
    private final static String CHILD_EMPTY_BP = org.openide.util.NbBundle.getMessage(FamillyVisualPanel.class, "blueprint.child.empty");
    private final static String FATHER_EMPTY_BP = org.openide.util.NbBundle.getMessage(FamillyVisualPanel.class, "blueprint.father.empty");
    private final static String MOTHER_EMPTY_BP = org.openide.util.NbBundle.getMessage(FamillyVisualPanel.class, "blueprint.mother.empty");
    private final static String FAMS_EMPTY_BP = org.openide.util.NbBundle.getMessage(FamillyVisualPanel.class, "blueprint.fams.empty");
    private IndiBeans husbandBeans;
    private IndiBeans wifeBeans;
    private INewGedcomProvider gedcomProvider;

    /** Creates new form FamillyVisualPanel */
    public FamillyVisualPanel(INewGedcomProvider newGedcom) {
        gedcomProvider = newGedcom;
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        husbandBeans = new IndiBeans(husband, husbFather, husbMother);
        wifeBeans = new IndiBeans(wife, wifeFather, wifeMother);
        husband.setEmptyBluePrint(HUSBAND_EMPTY_BP);
        wife.setEmptyBluePrint(WIFE_EMPTY_BP);
        familySpouse.setEmptyBluePrint(FAMS_EMPTY_BP);

        setContext(newGedcom.getFirst(), true);
        updatechildrenPanel();
    }

    public void setContext(Entity entity, boolean getRelatives) {
        Fam f = null;
        Indi h = null;
        Indi w = null;
        if (entity instanceof Fam) {
            f = ((Fam) entity);
            if (getRelatives) {
                h = f.getHusband();
                w = f.getWife();
            }
        } else if (entity instanceof Indi) {
            Indi i = (Indi) entity;
            if (i.getSex() == PropertySex.FEMALE) {
                w = i;
            } else {
                h = i;
            }
            if (getRelatives) {
                if (i.getNoOfFams() > 0) {
                    f = i.getFamiliesWhereSpouse()[0];
                    h = f.getHusband();
                    w = f.getWife();
                }
            }
        }

        familySpouse.setContext(f);
        husbandBeans.setIndi(h, getRelatives);
        wifeBeans.setIndi(w, getRelatives);
    }

    @Override
    public String getName() {
        return "Completer le noyau familial";
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        husband = new ancestris.modules.beans.ABluePrintBeans();
        husbFather = new ancestris.modules.beans.ABluePrintBeans();
        wifeFather = new ancestris.modules.beans.ABluePrintBeans();
        familySpouse = new ancestris.modules.beans.ABluePrintBeans();
        wifeMother = new ancestris.modules.beans.ABluePrintBeans();
        husbMother = new ancestris.modules.beans.ABluePrintBeans();
        wife = new ancestris.modules.beans.ABluePrintBeans();
        jScrollPane1 = new javax.swing.JScrollPane();
        childrenPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(622, 500));
        setRequestFocusEnabled(false);

        husband.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                husbandMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout husbandLayout = new javax.swing.GroupLayout(husband);
        husband.setLayout(husbandLayout);
        husbandLayout.setHorizontalGroup(
            husbandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );
        husbandLayout.setVerticalGroup(
            husbandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        husbFather.setMinimumSize(new java.awt.Dimension(256, 80));
        husbFather.setPreferredSize(new java.awt.Dimension(256, 80));
        husbFather.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                husbFatherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout husbFatherLayout = new javax.swing.GroupLayout(husbFather);
        husbFather.setLayout(husbFatherLayout);
        husbFatherLayout.setHorizontalGroup(
            husbFatherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );
        husbFatherLayout.setVerticalGroup(
            husbFatherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        wifeFather.setMinimumSize(new java.awt.Dimension(256, 80));
        wifeFather.setPreferredSize(new java.awt.Dimension(256, 80));
        wifeFather.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wifeFatherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout wifeFatherLayout = new javax.swing.GroupLayout(wifeFather);
        wifeFather.setLayout(wifeFatherLayout);
        wifeFatherLayout.setHorizontalGroup(
            wifeFatherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        wifeFatherLayout.setVerticalGroup(
            wifeFatherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        familySpouse.setPreferredSize(new java.awt.Dimension(256, 80));
        familySpouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                familySpouseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout familySpouseLayout = new javax.swing.GroupLayout(familySpouse);
        familySpouse.setLayout(familySpouseLayout);
        familySpouseLayout.setHorizontalGroup(
            familySpouseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        familySpouseLayout.setVerticalGroup(
            familySpouseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        wifeMother.setPreferredSize(new java.awt.Dimension(256, 80));
        wifeMother.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wifeMotherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout wifeMotherLayout = new javax.swing.GroupLayout(wifeMother);
        wifeMother.setLayout(wifeMotherLayout);
        wifeMotherLayout.setHorizontalGroup(
            wifeMotherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        wifeMotherLayout.setVerticalGroup(
            wifeMotherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        husbMother.setPreferredSize(new java.awt.Dimension(256, 80));
        husbMother.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                husbMotherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout husbMotherLayout = new javax.swing.GroupLayout(husbMother);
        husbMother.setLayout(husbMotherLayout);
        husbMotherLayout.setHorizontalGroup(
            husbMotherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );
        husbMotherLayout.setVerticalGroup(
            husbMotherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        wife.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wifeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout wifeLayout = new javax.swing.GroupLayout(wife);
        wife.setLayout(wifeLayout);
        wifeLayout.setHorizontalGroup(
            wifeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        wifeLayout.setVerticalGroup(
            wifeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        childrenPanel.setLayout(new java.awt.GridLayout(0, 3, 5, 5));
        jScrollPane1.setViewportView(childrenPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(familySpouse, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(husband, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(husbFather, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(wife, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wifeFather, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(husbMother, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wifeMother, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wifeFather, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(husbFather, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wifeMother, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(husbMother, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wife, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(husband, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(familySpouse, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void husbFatherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_husbFatherMouseClicked
        createOrEditParent(husbandBeans, (Indi) husbFather.getContext(), PropertySex.MALE);
    }//GEN-LAST:event_husbFatherMouseClicked

    private void wifeFatherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wifeFatherMouseClicked
        createOrEditParent(wifeBeans, (Indi) wifeFather.getContext(), PropertySex.MALE);
    }//GEN-LAST:event_wifeFatherMouseClicked

    private void husbMotherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_husbMotherMouseClicked
        createOrEditParent(husbandBeans, (Indi) husbMother.getContext(), PropertySex.FEMALE);
    }//GEN-LAST:event_husbMotherMouseClicked

    private void wifeMotherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wifeMotherMouseClicked
        createOrEditParent(wifeBeans, (Indi) wifeMother.getContext(), PropertySex.FEMALE);
    }//GEN-LAST:event_wifeMotherMouseClicked

    private void husbandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_husbandMouseClicked
        createOrEditSpouse(husbandBeans, wife.getContext());
    }//GEN-LAST:event_husbandMouseClicked

    private void wifeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wifeMouseClicked
        createOrEditSpouse(wifeBeans, husband.getContext());
    }//GEN-LAST:event_wifeMouseClicked

    private void familySpouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_familySpouseMouseClicked
        createOrEditFam(familySpouse);
    }//GEN-LAST:event_familySpouseMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel childrenPanel;
    private ancestris.modules.beans.ABluePrintBeans familySpouse;
    private ancestris.modules.beans.ABluePrintBeans husbFather;
    private ancestris.modules.beans.ABluePrintBeans husbMother;
    private ancestris.modules.beans.ABluePrintBeans husband;
    private javax.swing.JScrollPane jScrollPane1;
    private ancestris.modules.beans.ABluePrintBeans wife;
    private ancestris.modules.beans.ABluePrintBeans wifeFather;
    private ancestris.modules.beans.ABluePrintBeans wifeMother;
    // End of variables declaration//GEN-END:variables

    private void createOrEditParent(IndiBeans destBean, Indi parent, int sex) {
        Indi indi = (Indi) destBean.getIndi();
        if (indi == null) {
            return;
        }
        if (parent != null) {
            editEntity(parent);
        } else {
            CreateParent cpAction = new CreateParent(indi, sex);
            cpAction.actionPerformed(new ActionEvent(this, 0, ""));

            if (cpAction.isNew()) {
                parent = (Indi) cpAction.getCreated();
                if (!editEntity(parent)) {
                    indi.getGedcom().undoUnitOfWork(false);
                }
            }
        }
        destBean.setIndi(indi, true);
    }

    private void createOrEditSpouse(IndiBeans destBean, Entity spouse) {
        Indi indi = (Indi) destBean.getIndi();
        if (indi != null) {
            editEntity(indi);
        } else {
            CreateSpouse csAction = new CreateSpouse((Indi) spouse);
            csAction.actionPerformed(new ActionEvent(this, 0, ""));
            indi = (Indi) csAction.getCreated();
            if (csAction.isNew()) {
                if (!editEntity(indi)) {
                    spouse.getGedcom().undoUnitOfWork(false);
                }
            }
        }
        destBean.setIndi(indi, true);
        familySpouse.setContext(getFams(indi, (Indi) spouse));
    }

    private void createOrEditChild(ABluePrintBeans destBean) {
        Indi indi = (Indi) destBean.getContext();
        if (indi != null) {
            editEntity(indi);
        } else {
            CreateChild ccAction;
            // tries to guess entity to attach new child to
            // Familly knows?
            if (familySpouse.getContext() != null) {
                ccAction = new CreateChild((Fam) (familySpouse.getContext().getEntity()), true);
                ccAction.actionPerformed(new ActionEvent(this, 0, ""));
            } else {
                Indi parent = getWifeOrHusband();
                // must not be null
                if (parent == null) {
                    throw new UnsupportedOperationException("no entity to attach new child to");
                }
                ccAction = new CreateChild(parent, true);
                ccAction.actionPerformed(new ActionEvent(this, 0, ""));
            }
            indi = (Indi) ccAction.getCreated();
            if (ccAction.isNew()) {
                if (!editEntity(indi)) {
                    gedcomProvider.getContext().getGedcom().undoUnitOfWork(false);
                    return;
                }
            }
            if (indi == null) {
                return;
            }
            familySpouse.setContext(indi.getFamilyWhereBiologicalChild());
        }
        destBean.setContext(indi);
        updatechildrenPanel();
    }

    private void createOrEditFam(ABluePrintBeans destBean) {
        Fam fam = (Fam) destBean.getContext();
        if (fam == null) {
            return;
        }

        editEntity(fam);
        destBean.setContext(fam);
        updatechildrenPanel();
    }

    private boolean editEntity(Entity entity) {
        return false;
    }

    private boolean editEntity(Fam fam) {
        AFamBean bean = new AFamBean();
        NotifyDescriptor nd = new NotifyDescriptor(bean.setRoot(fam), "create indi", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, null);
        DialogDisplayer.getDefault().notify(nd);
        if (!nd.getValue().equals(NotifyDescriptor.OK_OPTION)) {
            return false;
        }
        try {
            bean.commit();
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
            return false;
        }
        return true;
    }

    private boolean editEntity(Indi indi) {
        if (indi == null) {
            return false;
        }
        AIndiBean bean = new AIndiBean();
        NotifyDescriptor nd = new NotifyDescriptor(bean.setRoot(indi), "create indi", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, null);
        DialogDisplayer.getDefault().notify(nd);
        if (!nd.getValue().equals(NotifyDescriptor.OK_OPTION)) {
            return false;
        }
        try {
            bean.commit();
        } catch (GedcomException ex) {
            Exceptions.printStackTrace(ex);
            return false;
        }
        return true;
    }

    @Override
    public void applyNext() {
    }

    private void updatechildrenPanel() {
        childrenPanel.removeAll();
        childrenPanel.repaint();
        Fam f = familySpouse.getContext() == null ? null : (Fam) (familySpouse.getContext().getEntity());
        if (f != null) {
            for (Indi child : f.getChildren()) {
                childrenPanel.add(new ChildBean(child));
            }
        }
        childrenPanel.add(new ChildBean());
        childrenPanel.revalidate();
    }

    private static class IndiBeans {

        ABluePrintBeans indiBean;
        ABluePrintBeans fatherBean;
        ABluePrintBeans motherBean;
        Indi indi;

        public IndiBeans(ABluePrintBeans i, ABluePrintBeans f, ABluePrintBeans m) {
            indiBean = i;
            fatherBean = f;
            motherBean = m;
        }

        public ABluePrintBeans getFatherBean() {
            return fatherBean;
        }

        public ABluePrintBeans getIndiBean() {
            return indiBean;
        }

        public ABluePrintBeans getMotherBean() {
            return motherBean;
        }

        public Indi getIndi() {
            return indi;
        }

        public void setIndi(Indi indi, boolean getRelatives) {
            this.indi = indi;
            if (indiBean == null) {
                return;
            }
            indiBean.setEmptyBluePrint(EMPTY_BP);
            if (indi == null) {
                fatherBean.setEmptyBluePrint(null);
                motherBean.setEmptyBluePrint(null);
            } else {
                fatherBean.setEmptyBluePrint(FATHER_EMPTY_BP);
                motherBean.setEmptyBluePrint(MOTHER_EMPTY_BP);
            }
            indiBean.setContext(indi);

            if (indi != null && getRelatives) {
                fatherBean.setContext(indi.getBiologicalFather());
                motherBean.setContext(indi.getBiologicalMother());
            }

        }
    }

    /**
     * return wife if wifeBean's indi is not null, husband otherwise
     */
    private Indi getWifeOrHusband() {
        // return wife
        Indi parent = (Indi) wife.getContext();
        // or husband
        if (parent == null) {
            parent = (Indi) husband.getContext();
        }
        return parent;
    }

    private Fam getFams(Indi indi, Indi spouse) {
        if (indi == null) {
            return null;
        }
        if (indi.getNoOfFams() == 0) {
            return null;
        }
        Fam[] fams = indi.getFamiliesWhereSpouse();
        if (spouse == null) {
            return fams[0];
        }
        for (Fam fam : fams) {
            if (fam.getOtherSpouse(indi) == spouse) {
                return fam;
            }
        }
        return null;
    }

    private class ChildBean extends ABluePrintBeans {

        public ChildBean() {
            this(null);
        }

        public ChildBean(Indi child) {
            super();
            setEmptyBluePrint(CHILD_EMPTY_BP);
            this.setContext(child);
            addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    createOrEditChild(ChildBean.this);
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new java.awt.Dimension(150, 35);
        }

        public void addToPanel(JPanel panel) {
            panel.add(this);
        }
    }
}
