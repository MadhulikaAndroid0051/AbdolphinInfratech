package com.example.abdolphininfratech.ModelClass;

public class AssociateDownlineDetailModel {
    private String AffiliateID;
    private String AffiliateName;
    private String RankName;
    private String Rank;
    private String Contact;

    public AssociateDownlineDetailModel(String affiliateID, String affiliateName, String rankName, String rank,  String contact) {
        AffiliateID = affiliateID;
        AffiliateName = affiliateName;
        RankName = rankName;
        Rank = rank;
        Contact = contact;
    }

    public String getAffiliateID() {
        return AffiliateID;
    }

    public String getAffiliateName() {
        return AffiliateName;
    }

    public String getRankName() {
        return RankName;
    }

    public String getRank() {
        return Rank;
    }


    public String getContact() {
        return Contact;
    }

    public void setAffiliateID(String affiliateID) {
        AffiliateID = affiliateID;
    }

    public void setAffiliateName(String affiliateName) {
        AffiliateName = affiliateName;
    }

    public void setRankName(String rankName) {
        RankName = rankName;
    }

    public void setRank(String rank) {
        Rank = rank;
    }


    public void setContact(String contact) {
        Contact = contact;
    }
}
