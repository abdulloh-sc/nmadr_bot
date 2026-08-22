package uz.imaan.entity;

import uz.imaan.BotState;

public class UserSession {
    private BotState state;
    private Integer tanlanganFirmaId;

    public UserSession() {
        this.state = BotState.START;
    }

    public BotState getState() {
        return state;
    }

    public void setState(BotState state) {
        this.state = state;
    }

    public Integer getTanlanganFirmaId() {
        return tanlanganFirmaId;
    }

    public void setTanlanganFirmaId(Integer tanlanganFirmaId) {
        this.tanlanganFirmaId = tanlanganFirmaId;
    }
}











