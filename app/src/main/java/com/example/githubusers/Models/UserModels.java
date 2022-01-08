package com.example.githubusers.Models;

import java.util.ArrayList;

/**
 * Author: akunp
 * Created by: ModelGenerator on 08/01/2022
 */
public class UserModels {
    public int totalCount;
    public boolean incompleteResults;
    public ArrayList<ItemsItem> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public ArrayList<ItemsItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemsItem> items) {
        this.items = items;
    }
}