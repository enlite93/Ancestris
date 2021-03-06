 /**
 * GenJ - GenealogyJ
 *
 * Copyright (C) 1997 - 2002 Nils Meier <nils@meiers.net>
 *
 * This piece of code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package ancestris.gedcom;

import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.PropertyDate;
import genj.gedcom.PropertyFile;
import genj.gedcom.TagPath;
import genj.gedcom.time.PointInTime;
import genj.io.Filter;
import genj.util.Resources;
import genj.util.swing.ChoiceWidget;
import genj.util.swing.DateWidget;
import genj.util.swing.TextFieldWidget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * A widget for setting save options (export)
 */
/*package*/ public class SaveOptionsWidget extends JTabbedPane {
  
  /** components */
  private JCheckBox[] checkEntities = new JCheckBox[Gedcom.ENTITIES.length];
  private JCheckBox[] checkFilters;
  private JTextField textTags, textValues;
  private TextFieldWidget textPassword;
  private JComboBox comboEncodings;
  private JCheckBox checkFilterEmpties, checkFilterLiving, checkMediaDirectory;
  private Resources resources = Resources.get(this);
  private DateWidget dateEventsAfter, dateBirthsAfter;
  
  /** filters */
  private Filter[] filters;

  /**
   * Constructor
   */    
  /*package*/ public  SaveOptionsWidget(Gedcom gedcom) {
      this(gedcom,(Filter[])null);
  }
  /*package*/ public  SaveOptionsWidget(Gedcom gedcom, Collection<? extends Filter> filters) {
      this(gedcom,filters.toArray(new Filter[]{}));
    }
  /*package*/ public SaveOptionsWidget(Filter[] filters) {
      this (null, filters);
  }
  /*package*/ public SaveOptionsWidget(Gedcom gedcom, Filter[] filters) {
     
      // Allow for files moves to another directory
      int nbFiles = 0;
      Box directories = new Box(BoxLayout.Y_AXIS);
      if (gedcom != null) {
          List<PropertyFile> files = (List<PropertyFile>) gedcom.getPropertiesByClass(PropertyFile.class);
          nbFiles = files.size();
          directories.add(new JLabel(" "));
          checkMediaDirectory = new JCheckBox(resources.getString("save.options.files.check"));
          directories.add(checkMediaDirectory);
          directories.add(new JLabel(" "));
          directories.add(new JLabel(resources.getString("save.options.files.label")));
          directories.add(new JLabel(" "));
      }

    
    // Entity filter    
    Box types = new Box(BoxLayout.Y_AXIS);
    for (int t=0; t<Gedcom.ENTITIES.length; t++) {
      checkEntities[t] = new JCheckBox(Gedcom.getName(Gedcom.ENTITIES[t], true), true);
      types.add(checkEntities[t]);
    }
    
    // Property filter
    Box props = new Box(BoxLayout.Y_AXIS);
    props.add(new JLabel(resources.getString("save.options.exclude.tags")));
    textTags = new TextFieldWidget(resources.getString("save.options.exclude.tags.eg"), 10).setTemplate(true);
    props.add(textTags);
    props.add(new JLabel(resources.getString("save.options.exclude.values")));
    textValues = new TextFieldWidget(resources.getString("save.options.exclude.values.eg"), 10).setTemplate(true);
    props.add(textValues);
    props.add(new JLabel(resources.getString("save.options.exclude.events")));
    dateEventsAfter = new DateWidget();
    props.add(dateEventsAfter);
    props.add(new JLabel(resources.getString("save.options.exclude.indis")));
    dateBirthsAfter = new DateWidget();
    props.add(dateBirthsAfter);
    checkFilterLiving = new JCheckBox(resources.getString("save.options.exclude.living"));
    props.add(checkFilterLiving);
    checkFilterEmpties = new JCheckBox(resources.getString("save.options.exclude.empties"));
    props.add(checkFilterEmpties);
       
    // View filter
    Box others = new Box(BoxLayout.Y_AXIS);
    this.filters = filters;
    if (filters!=null){
    this.checkFilters = new JCheckBox[filters.length];
        for (int i = 0; i < checkFilters.length; i++) {
            checkFilters[i] = new JCheckBox(filters[i].getFilterName(), false);
            others.add(checkFilters[i]);
        }
    }

    // Hide options tab if gedcom is null (used for other types of file)
    Box options = new Box(BoxLayout.Y_AXIS);
    if (gedcom != null) {
      // Options
      options.add(new JLabel(" "));
      options.add(new JLabel(resources.getString("save.options.encoding")));
      comboEncodings = new ChoiceWidget(Gedcom.ENCODINGS, Gedcom.ANSEL);
      comboEncodings.setEditable(false);
      comboEncodings.setSelectedItem(gedcom.getEncoding());
      options.add(comboEncodings);
      options.add(new JLabel(" "));
      options.add(new JLabel(resources.getString("save.options.password")));
      textPassword = new TextFieldWidget(gedcom.hasPassword() ? gedcom.getPassword() : "", 10);
      textPassword.setEditable(gedcom.getPassword()!=Gedcom.PASSWORD_UNKNOWN);
      options.add(textPassword);
    }
    
    // layout
    if (gedcom != null && nbFiles > 0) {
        add(resources.getString("save.options.files"), directories);
    }
    add(resources.getString("save.options.filter.entities"  ), types);
    add(resources.getString("save.options.filter.properties"), props);
    add(resources.getString("save.options.filter.views"     ), new JScrollPane(others));
    if (gedcom != null) {
        add(resources.getString("save.options"                  ), options);
      }
    
    // done
  }

  /**
   * The selected media and source directory
   */
  public boolean areMediaToBeCopied() {
    return checkMediaDirectory.isSelected();
  }

  
  /**
   * The choosen password
   */
  public String getPassword() {
    return textPassword.getText();
  }

  /**
   * The choosen encoding
   */
  public String getEncoding() {
    return comboEncodings.getSelectedItem().toString();
  }
  
  /**
   * The choosen filters
   */
  public Collection<Filter> getFilters() {
    
    // Result
    List<Filter> result = new ArrayList<Filter>(10);
    
    // create one for the types
    FilterByType fbt = FilterByType.get(checkEntities);
    if (fbt!=null) result.add(fbt);
    
    // create one for the properties
    FilterProperties fp = FilterProperties.get(textTags.getText(), textValues.getText());
    if (fp!=null) result.add(fp);
    
    // create one for events
    PointInTime eventsAfter = dateEventsAfter.getValue();
    if (eventsAfter!=null&&eventsAfter.isValid())
      result.add(new FilterEventsAfter(eventsAfter));
    
    // create one for births
    PointInTime birthsAfter = dateBirthsAfter.getValue();
    if (birthsAfter!=null&&birthsAfter.isValid())
      result.add(new FilterIndividualsBornAfter(birthsAfter));
    
    // create one for living
    if (checkFilterLiving.isSelected())
      result.add(new FilterLivingIndividuals());
        
    // create one for empties
    if (checkFilterEmpties.isSelected())
      result.add(new FilterEmpties());
    
    // create one for every other
    if (filters != null)
    for (int f=0; f<filters.length; f++) {
      if (checkFilters[f].isSelected())
    	 result.add(filters[f]);
    }
    
    // done
    return result;
  }
  
  /**
   * Filtering out empty properties
   */
  private static class FilterEmpties implements Filter {
   
        @Override
    public boolean veto(Property property) {
      for (int i = 0; i < property.getNoOfProperties(); i++) {
        if (!veto(property.getProperty(i)))
            return false;
      }
      return property.getValue().trim().length()==0;
    }
    
        @Override
    public String getFilterName() {
      return toString();
    }

        @Override
        public boolean veto(Entity entity) {
            return false;
        }

        @Override
        public boolean canApplyTo(Gedcom gedcom) {
            return true;
        }
  }

  /**
   * Filter individuals if born after pit
   */
  private static class FilterIndividualsBornAfter implements Filter {
    
    private PointInTime after;
    
    /** constructor */
    private FilterIndividualsBornAfter(PointInTime after) {
      this.after = after;
    }
    
    /** callback */
        @Override
    public boolean veto(Entity entity) {
      if (entity instanceof Indi) {
        Indi indi = (Indi)entity;
        PropertyDate birth = indi.getBirthDate();
        if (birth!=null) return birth.getStart().compareTo(after)>=0;
      }
        
      // fine
      return false;
    }
    
        @Override
    public String getFilterName() {
      return toString();
    }

        @Override
        public boolean veto(Property property) {
            return false;
        }
        @Override
        public boolean canApplyTo(Gedcom gedcom) {
            return true;
        }
  }
  
  /**
   * Filter not deceased individuals
   */
  private static class FilterLivingIndividuals implements Filter {

    private FilterLivingIndividuals() {
    }

    /** callback */
        @Override
    public boolean veto(Entity indi) {
      if (indi instanceof Indi) {
        return !((Indi)indi).isDeceased();
      }

     // fine
     return false;
   }

   public String getFilterName() {
     return toString();
   }

        @Override
        public boolean veto(Property property) {
            return false;
        }
        @Override
        public boolean canApplyTo(Gedcom gedcom) {
            return true;
        }
 }

  /**
   * Filter properties if concerning events after pit
   */
  private static class FilterEventsAfter implements Filter {
    
    private PointInTime after;
    
    /** constructor */
    private FilterEventsAfter(PointInTime after) {
      this.after = after;
    }
    
    /** callback */
        @Override
    public boolean veto(Property property) {
      PropertyDate when = property.getWhen();
      return !(when==null || when.getStart().compareTo(after)<=0);
    }
        @Override
    public String getFilterName() {
      return toString();
    }

        @Override
        public boolean veto(Entity entity) {
            return false;
        }
        @Override
        public boolean canApplyTo(Gedcom gedcom) {
            return true;
        }
  }
  
  /**
   * Filter property by tag/value
   */
  private static class FilterProperties implements Filter {
    
    /** filter tags */
    private Set tags;
    
    /** filter paths */
    private Set paths;
    
    /** filter values */
    private String[] values;
    
    /**
     * Constructor
     */
    private FilterProperties(Set tags, Set paths, List<String> values) {
      this.tags = tags;
      this.paths = paths;
      this.values = values.toArray(new String[0]);
      // done
    }
    
    /**
     * Get instance
     */
    protected static FilterProperties get(String sTags, String sValues) {
      
      // calculate tags
      Set<String> tags = new HashSet<String>();
      Set<TagPath> paths = new HashSet<TagPath>();
      
      StringTokenizer tokens = new StringTokenizer(sTags, ",");
      while (tokens.hasMoreTokens()) {
        String s = tokens.nextToken().trim();
        if (s.indexOf(':')>0) {
          try {
            paths.add(new TagPath(s));
          } catch (IllegalArgumentException e) { 
          }
        } else {
          tags.add(s);
        }
      }
      // calculate values
      List<String> values = new ArrayList<String>();
      tokens = new StringTokenizer(sValues, ",");
      while (tokens.hasMoreTokens()) {
        values.add(tokens.nextToken().trim());
      }
     
      // done
      return (tags.isEmpty() && paths.isEmpty() && values.isEmpty()) ? null : new FilterProperties(tags, paths, values);
    }
    
    /**
     * @see genj.io.Filter#accept(genj.gedcom.Property)
     */
    public boolean veto(Property property) {
      // check if tag is applying
      if (tags.contains(property.getTag())) return true;
      // check if path is applying
      if (paths.contains(property.getPath())) return true;
      // simple
      return !accept(property.getValue());
    }
        @Override
        public boolean veto(Entity entity) {
            return false;
        }
    
    /**
     * Whether we accept a value
     */
    private boolean accept(String value) {
      if (value==null) return true;
      for (int i=0; i<values.length; i++) {
        if (value.indexOf(values[i])>=0) return false;
      }
      return true;
    }

        @Override
    public String getFilterName() {
      return toString();
    }

        @Override
        public boolean canApplyTo(Gedcom gedcom) {
            return true;
        }
  } //FilterProperty
  
  /**
   * Filter by type
   */
  private static class FilterByType implements Filter {
    
    /** the enabled types */
    private Set<String> types = new HashSet<String>();
    
    /**
     * Create an instance
     */
    protected static FilterByType get(JCheckBox[] checks) {
      
      FilterByType result = new FilterByType();
      
      for (int t=0; t<checks.length; t++) {
      	if (checks[t].isSelected())
          result.types.add(Gedcom.ENTITIES[t]);
      }
      return result.types.size()<Gedcom.ENTITIES.length ? result : null;
    }
    /**
     * accepting all properties, limit to entities of parameterized types
     * @see genj.io.Filter#accept(genj.gedcom.Property)
     */
        @Override
    public boolean veto(Entity  entity) {
      if (!types.contains(entity.getTag()))
          return true;
      return false;
    }
        @Override
    public String getFilterName() {
      return toString();
    }

        @Override
        public boolean veto(Property property) {
            return false;
        }

        @Override
        public boolean canApplyTo(Gedcom gedcom) {
            return true;
        }
  } //FilterByType

} //SaveOptionsWidget
