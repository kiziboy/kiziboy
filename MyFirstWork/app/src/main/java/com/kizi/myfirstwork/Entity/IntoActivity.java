package com.kizi.myfirstwork.Entity;

import com.kizi.myfirstwork.Entity.Bean.MainPage;
import com.kizi.myfirstwork.Entity.Bean.Root;

import java.io.Serializable;

/**
 * Created by ASUS on 2016/8/10.
 */
public class IntoActivity implements Serializable {
    private Root root;
    private MainPage mainPage;
    public IntoActivity(Root root) {
        this.root=root;
    }

    public IntoActivity(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }
}
