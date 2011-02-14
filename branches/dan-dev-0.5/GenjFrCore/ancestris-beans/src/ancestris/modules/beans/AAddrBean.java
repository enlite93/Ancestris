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
package ancestris.modules.beans;

import genj.gedcom.GedcomException;
import genj.gedcom.Property;
import javax.swing.JPanel;

public final class AAddrBean extends JPanel implements ABean {

    private String tag = "";

    /**
     * Get the value of tag
     *
     * @return the value of tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the value of tag
     *
     * @param tag new value of tag
     */
    public void setTag(String tag) {
        this.tag = tag;
        repaint();
    }

    /**
     * Set the value of root
     *
     * @param property new value of root
     */
    @Override
    public AAddrBean setRoot(Property property) {
        address.setContext(property, "ADDR");
        cpost.setContext(property, "ADDR:POST");
        city.setContext(property, "ADDR:CITY");
        state.setContext(property, "ADDR:STAE");
        country.setContext(property, "ADDR:CTRY");
        return this;
    }

    /** Creates new form NewGedcomVisualPanel2 */
    public AAddrBean() {
        initComponents();
    }

    /**
     * commit beans - transaction has to be running already
     */
    @Override
    public void commit() throws GedcomException {
        address.commit();
        cpost.commit();
        city.commit();
        state.commit();
        country.commit();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addressLabel = new ancestris.modules.beans.ATagBean();
        cityLabel = new ancestris.modules.beans.ATagBean();
        countryLabel = new ancestris.modules.beans.ATagBean();
        stateLabel = new ancestris.modules.beans.ATagBean();
        city = new ancestris.modules.beans.AChoiceBean();
        state = new ancestris.modules.beans.AChoiceBean();
        country = new ancestris.modules.beans.AChoiceBean();
        address = new ancestris.modules.beans.AMLEBean();
        cpostLabel = new ancestris.modules.beans.ATagBean();
        cpost = new ancestris.modules.beans.AChoiceBean();

        addressLabel.setTag(org.openide.util.NbBundle.getMessage(AAddrBean.class, "AAddrBean.addressLabel.tag")); // NOI18N

        cityLabel.setTag(org.openide.util.NbBundle.getMessage(AAddrBean.class, "AAddrBean.cityLabel.tag")); // NOI18N

        countryLabel.setTag(org.openide.util.NbBundle.getMessage(AAddrBean.class, "AAddrBean.countryLabel.tag")); // NOI18N

        stateLabel.setTag(org.openide.util.NbBundle.getMessage(AAddrBean.class, "AAddrBean.stateLabel.tag")); // NOI18N

        cpostLabel.setTag(org.openide.util.NbBundle.getMessage(AAddrBean.class, "AAddrBean.cpostLabel.tag")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(city, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(address, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(cpost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(country, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(stateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(state, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cpostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ancestris.modules.beans.AMLEBean address;
    private ancestris.modules.beans.ATagBean addressLabel;
    private ancestris.modules.beans.AChoiceBean city;
    private ancestris.modules.beans.ATagBean cityLabel;
    private ancestris.modules.beans.AChoiceBean country;
    private ancestris.modules.beans.ATagBean countryLabel;
    private ancestris.modules.beans.AChoiceBean cpost;
    private ancestris.modules.beans.ATagBean cpostLabel;
    private ancestris.modules.beans.AChoiceBean state;
    private ancestris.modules.beans.ATagBean stateLabel;
    // End of variables declaration//GEN-END:variables
}
