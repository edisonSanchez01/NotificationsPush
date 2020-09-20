package com.edisondeveloper.petagram.Presentador.ConfigPerfilActivity;

import android.content.Context;
import android.widget.Toast;

import com.edisondeveloper.petagram.Modelo.Constantes;
import com.edisondeveloper.petagram.R;
import com.edisondeveloper.petagram.Vista.ConfigPerfilActivity.IConfigPerfilActivityView;

public class ConfigPerfilActivityPresenter implements IConfigPerfilActivityPresenter {

    private Context context;
    private IConfigPerfilActivityView iConfigPerfilActivityView;

    public ConfigPerfilActivityPresenter(Context contex, IConfigPerfilActivityView iConfigPerfilActivityView){
        this.context = contex;
        this.iConfigPerfilActivityView = iConfigPerfilActivityView;
    }

    @Override
    public void validarNombre(String name) {
        if(name == null || name.isEmpty()){
            iConfigPerfilActivityView.showError(context.getString(R.string.error_edit_text_empty));
        }else if(name.length() < 5){
            iConfigPerfilActivityView.showError(context.getString(R.string.error_text_edit_text));
        }else{
            Constantes.setNameUser(context, name);
            Toast.makeText(context, context.getString(R.string.notifing_changes_name), Toast.LENGTH_SHORT ).show();
        }
    }
}
