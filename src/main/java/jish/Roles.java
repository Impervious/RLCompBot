package jish;

public enum Roles {

    BRONZE(338052718633418752l, "Bronze"),
    SILVER(338052668251570176l, "Silver"),
    GOLD(338052611833856010l, "Gold"),
    PLATINUM(338052338851774476l, "Platinum"),
    DIAMOND(338052290093252608l, "Diamond"),
    CHAMPION(338052229460262912l, "Champion"),
    GRAND_CHAMPION(338051674729873419l, "Grand Champion");

    public long ID;
    public String nick;

    Roles(long role, String nick) {
        this.ID = role;
        this.nick = nick;
    }
}
