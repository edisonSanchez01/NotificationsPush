package com.edisondeveloper.petagram.Presentador.TopFiveActivity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.edisondeveloper.petagram.Modelo.ContratoTopFive;
import com.edisondeveloper.petagram.Vista.TopFiveActivity.ITopFiveActivityView;

public class TopFiveActivityPresenter implements ITopFiveActivityPresenter{

    private Context context;
    private ITopFiveActivityView iTopFiveActivityView;

    public TopFiveActivityPresenter(Context context, ITopFiveActivityView iTopFiveActivityView){
        this.context = context;
        this.iTopFiveActivityView = iTopFiveActivityView;
    }

    @Override
    public void executeThread() {
        AsynckTaskTop asynckTaskTop = new AsynckTaskTop();
        asynckTaskTop.execute(ContratoTopFive.TablaTopFive.URI_TABLA_TOP_FIVE);
    }

    public class AsynckTaskTop extends AsyncTask<Uri, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Uri... uris) {
            Cursor cursor = context.getContentResolver().query(uris[0],new String[]{ContratoTopFive.TablaTopFive.COLUMN_ID, ContratoTopFive.TablaTopFive.COLUMN_NAME, ContratoTopFive.TablaTopFive.COLUMN_IMAGE,
                    ContratoTopFive.TablaTopFive.COLUMN_RATING}, null, null, null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            iTopFiveActivityView.updateRecyclerView(cursor);
        }
    }
}
