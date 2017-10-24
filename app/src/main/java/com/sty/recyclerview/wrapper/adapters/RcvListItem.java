package com.sty.recyclerview.wrapper.adapters;

/**
 * Created by Steven.T on 2017/10/23/0023.
 */

public class RcvListItem {
    private int name;
    private int icon;

    public RcvListItem(int name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
