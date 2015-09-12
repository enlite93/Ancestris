/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2015 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.treesharing;

import ancestris.modules.treesharing.communication.AncestrisMember;
import ancestris.modules.treesharing.communication.GedcomFam;
import ancestris.modules.treesharing.communication.GedcomIndi;
import ancestris.modules.treesharing.communication.GedcomNumbers;
import ancestris.modules.treesharing.communication.MemberProfile;
import ancestris.modules.treesharing.options.TreeSharingOptionsPanel;
import ancestris.modules.treesharing.panels.AncestrisFriend;
import ancestris.modules.treesharing.panels.FriendGedcomEntity;
import ancestris.modules.treesharing.panels.ProfilePanel;
import ancestris.modules.treesharing.panels.SharedGedcom;
import ancestris.util.swing.DialogManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 *
 * @author frederic
 */
public class SearchSharedTrees extends Thread {
    
    private final TreeSharingTopComponent owner;
    private volatile boolean stopRun;
    
    public SearchSharedTrees(TreeSharingTopComponent tstc) {
        this.owner = tstc;
    }
        
        
    @Override
    public void run() {
        stopRun = false;
        owner.setRotatingIcon(true);
        while (!stopRun) {
            owner.updateMembersList();
            getAllMatchingEntities(owner.getSharedGedcoms(), owner.getAncestrisMembers());
        }
    }

    public void stopGracefully() {
        stopRun = true;
        owner.setRotatingIcon(false);
        owner.displaySearchedMember("");
    }

    /**
     * Main search function
     * 
     * Scans all members shared entities for each gedcom shared entities
     * Each time a match is found, an update is sent back to owner to display update
     * 
     * @param sharedGedcoms
     * @param ancestrisMembers 
     */
    private void getAllMatchingEntities(List<SharedGedcom> sharedGedcoms, List<AncestrisMember> ancestrisMembers) {

        
//        MemberProfile mp1 = owner.getMyProfile();
//        DialogManager.create("debug1", new ProfilePanel(mp1, owner.getMyProfile())).setMessageType(DialogManager.PLAIN_MESSAGE).setOptionType(DialogManager.OK_ONLY_OPTION).show();
//        
//        Map<Integer, byte[]> packetsOfProfile = owner.getCommHandler().buildPacketsOfProfile(mp1);
//        
//        ByteArrayOutputStream memberProfile = new ByteArrayOutputStream();
//        for (int i = 0; i < packetsOfProfile.size(); i++) {
//            try {
//                byte[] set = packetsOfProfile.get(i);
//                byte[] compackedSet = owner.getCommHandler().wrapObject(set);
//                memberProfile.write((byte[])owner.getCommHandler().unwrapObject(compackedSet));
//            } catch (IOException ex) {
//                Exceptions.printStackTrace(ex);
//            }
//        }
//        MemberProfile mp2 = (MemberProfile) owner.getCommHandler().unwrapObject(memberProfile.toByteArray());
//        DialogManager.create("debug2", new ProfilePanel(mp2, owner.getMyProfile())).setMessageType(DialogManager.PLAIN_MESSAGE).setOptionType(DialogManager.OK_ONLY_OPTION).show();
//        
//        if (true) return;
        
        
        // Initialize variables
        AncestrisFriend friend = null;
        String matchType = TreeSharingOptionsPanel.getMatchType();
        List<AncestrisMember> copyOfAncestrisMembers = (List) ((ArrayList) ancestrisMembers).clone(); // Copy ancestris members to avoid concurrent access to the list while using it
        Set<String> myIndiLastnames = owner.getCommHandler().getMySharedIndiLastnames(sharedGedcoms);
        Set<String> myFamLastnames = owner.getCommHandler().getMySharedFamLastnames(sharedGedcoms);  
        
        // Loop on all members
        for (AncestrisMember member : copyOfAncestrisMembers) {
            
            // Skip if member not allowed or if it is myself
            if (!member.isAllowed() || member.getMemberName().equals(owner.getPreferredPseudo())) {
                continue;
            }

            // Indicate which member is being analysed
            owner.displaySearchedMember(member.getMemberName());
            
            
            // Ask for stats first (nb of indis and nb of families
            owner.getCommHandler().setCommunicationInProgress(false);
            GedcomNumbers gedcomNumbers = owner.getCommHandler().getNbOfEntities(member);
            if (gedcomNumbers == null) {
                continue;
            }
            owner.getCommHandler().setCommunicationInProgress(true);

            // A. Individuals
            // Phase 1 - Get all lastnames from member for all its shared gedcoms at the same time and identify commons ones with mine
            Set<String> memberIndiLastnames = owner.getCommHandler().getSharedIndiLastnamesFromMember(member);
            if (memberIndiLastnames == null || memberIndiLastnames.isEmpty()) {
                continue;
            }
            Set<String> commonIndiLastnames = getCommonItems(myIndiLastnames, new HashSet<String>(memberIndiLastnames));
            if (commonIndiLastnames == null || commonIndiLastnames.isEmpty()) {
                continue;
            }
            
            // Phase 2 - Get individual details for common lastnames from member and identify matching ones according to matching type
            Set<GedcomIndi> memberGedcomIndis = owner.getCommHandler().getGedcomIndisFromMember(member, commonIndiLastnames);
            if (memberGedcomIndis != null && !memberGedcomIndis.isEmpty()) {
                // Phase 3 - Identify and create/update matches
                Set<GedcomIndi> myGedcomIndis = owner.getCommHandler().getMySharedGedcomIndis(sharedGedcoms, commonIndiLastnames);
                if (myGedcomIndis != null && !myGedcomIndis.isEmpty()) {
                    friend = addCommonIndis(sharedGedcoms, myGedcomIndis, memberGedcomIndis, matchType, member);    // create/update matches and friends if common are found
                }
            }

            
            // B. Families
            // Phase 1 - Get all fams lastnames from member for all its shared gedcoms at the same time and identify commons ones with mine
            Set<String> memberFamLastnames = owner.getCommHandler().getSharedFamLastnamesFromMember(member);
            if (memberFamLastnames == null || memberFamLastnames.isEmpty()) {
                continue;
            }
            Set<String> commonFamLastnames = getCommonItems(myFamLastnames, new HashSet<String>(memberFamLastnames));
            if (commonFamLastnames == null || commonFamLastnames.isEmpty()) {
                continue;
            }
            
            // Phase 2 - Get individual details for common fam lastnames from member and identify matching ones according to matching type
            Set<GedcomFam> memberGedcomFams = owner.getCommHandler().getGedcomFamsFromMember(member, commonFamLastnames);
            if (memberGedcomFams != null && !memberGedcomFams.isEmpty()) {
                // Phase 3 - Identify and create/update matches
                Set<GedcomFam> myGedcomFams = owner.getCommHandler().getMySharedGedcomFams(sharedGedcoms, commonFamLastnames);
                if (myGedcomFams != null && !myGedcomFams.isEmpty()) {
                    friend = addCommonFams(sharedGedcoms, myGedcomFams, memberGedcomFams, matchType, member);    // create/update matches and friends
                }
                
            }

            // Thank you, exchange profiles and update friend
            if (friend != null) {
                friend.setTotals(gedcomNumbers.nbIndis, gedcomNumbers.nbFams);   // set numbers
                owner.getCommHandler().thankMember(member, owner.getMyProfile());   // give my profile
                friend.setProfile(owner.getCommHandler().getProfileMember(member, false), owner.getMyProfile());  // get member profile and set it for friend
                friend = null;
            }
            
            
        } // endfor members

        owner.getCommHandler().setCommunicationInProgress(false);
        stopGracefully();
    }
    
    
    
    
    
    
    
    
    
    
    private Set<String> getCommonItems(Set<String> myIndiLastnames, Set<String> memberIndiLastnames) {
        memberIndiLastnames.retainAll(myIndiLastnames); 
        return new HashSet<String>(memberIndiLastnames);
    }

    private AncestrisFriend addCommonIndis(List<SharedGedcom> sharedGedcoms, Set<GedcomIndi> myGedcomIndis, Set<GedcomIndi> memberGedcomIndis, String matchType, AncestrisMember member) {
        
        AncestrisFriend friend = null;
        int retMatch;

        // Loop on each of *my* shared gedcoms
        for (SharedGedcom sharedGedcom : sharedGedcoms) {

            // Loop on all *my* shared entities
            for (GedcomIndi myGedcomIndi : myGedcomIndis) {

                // Loop all *member* entities
                for (GedcomIndi memberGedcomIndi : memberGedcomIndis) {
                    retMatch = isSameIndividual(myGedcomIndi, memberGedcomIndi, matchType);
                    if (retMatch != TreeSharingOptionsPanel.NO_MATCH) { // we have a match
                        friend = owner.createMatch(sharedGedcom, 
                                sharedGedcom.getGedcom().getEntity(myGedcomIndi.entityID), 
                                new FriendGedcomEntity(member.getMemberName(), memberGedcomIndi.gedcomName, memberGedcomIndi.entityID), 
                                member, retMatch);
                    }
                    continue;
                } // endfor memberEntities
            } // endfor myEntities
        } // endfor myGedcoms
        
        return friend;
    }

    
    
    
    private AncestrisFriend addCommonFams(List<SharedGedcom> sharedGedcoms, Set<GedcomFam> myGedcomFams, Set<GedcomFam> memberGedcomFams, String matchType, AncestrisMember member) {
        
        AncestrisFriend friend = null;
        int retMatch;

        // Loop on each of *my* shared gedcoms
        for (SharedGedcom sharedGedcom : sharedGedcoms) {

            // Loop on all *my* shared entities
            for (GedcomFam myGedcomFam : myGedcomFams) {

                // Loop all *member* entities
                for (GedcomFam memberGedcomFam : memberGedcomFams) {
                    retMatch = isSameFamily(myGedcomFam, memberGedcomFam, matchType);
                    if (retMatch != TreeSharingOptionsPanel.NO_MATCH) { // we have a match
                        friend = owner.createMatch(sharedGedcom, 
                                sharedGedcom.getGedcom().getEntity(myGedcomFam.entityID), 
                                new FriendGedcomEntity(member.getMemberName(), memberGedcomFam.gedcomName, memberGedcomFam.entityID), 
                                member, retMatch);
                    }
                    continue;
                } // endfor memberEntities
            } // endfor myEntities
        } // endfor myGedcoms
        
        return friend;
    }

    
    


    
    
    /**
     * Match functions for indi and fam:
     * - Purpose is not to garantee a match but to suggest to the user a possible match
     * - Compared data have to rely on elements most commonly found in any genealogy : name, place, dates
     * - Exact match will be as exact as:
     *      - individuals : exact lastname, exact firstname, exact birth city/country, exact birth year, and if available, same for death
     *      - families : same for both husband and wife
     * - Flash match will be like in a flash report ("2 people have a chance to have been at the same place at the same time"):
     *      - individuals : exact lastname, any first name (not compared), across all events : one city/country in common and overlapping time period (min/max do cross)
     *      - families : same for both husband and wife
     */
    private int isSameIndividual(GedcomIndi myIndi, GedcomIndi friendIndi, String matchType) {

        // Detect exact match first
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0])) {
            String la1 = myIndi.indiLastName;
            String fi1 = friendIndi.indiFirstName;
            String ci1 = myIndi.indiBirthPlace;
            String co1 = friendIndi.indiBirthPlace;
            
        }
        
        
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0]) && !myIndi.indiLastName.equals(friendIndi.indiLastName)) {
            return TreeSharingOptionsPanel.NO_MATCH;
        }
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0]) && !myIndi.indiFirstName.equals(friendIndi.indiFirstName)) {
            return TreeSharingOptionsPanel.NO_MATCH;
        }
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0])) {
            return TreeSharingOptionsPanel.EXACT_MATCH;
        }
        return TreeSharingOptionsPanel.NO_MATCH;
    }

    private int isSameFamily(GedcomFam myFamily, GedcomFam friendFam, String matchType) {
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0]) && myFamily.husbLastName != null && !myFamily.husbLastName.equals(friendFam.husbLastName)) {
            return TreeSharingOptionsPanel.NO_MATCH;
        }
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0]) && myFamily.husbFirstName != null && !myFamily.husbFirstName.equals(friendFam.husbFirstName)) {
            return TreeSharingOptionsPanel.NO_MATCH;
        }
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0]) && myFamily.wifeLastName != null && !myFamily.wifeLastName.equals(friendFam.wifeLastName)) {
            return TreeSharingOptionsPanel.NO_MATCH;
        }
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0]) && myFamily.wifeFirstName != null && !myFamily.wifeFirstName.equals(friendFam.wifeFirstName)) {
            return TreeSharingOptionsPanel.NO_MATCH;
        }
        if (matchType.equals(TreeSharingOptionsPanel.MATCHING_MENU[0])) {
            return TreeSharingOptionsPanel.EXACT_MATCH;
        }
        return TreeSharingOptionsPanel.NO_MATCH;
    }
    
    
}

