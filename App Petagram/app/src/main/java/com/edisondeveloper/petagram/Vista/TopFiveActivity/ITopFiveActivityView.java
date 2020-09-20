package com.edisondeveloper.petagram.Vista.TopFiveActivity;

import android.database.Cursor;

public interface ITopFiveActivityView {

    void updateRecyclerView(Cursor cursor);
}
