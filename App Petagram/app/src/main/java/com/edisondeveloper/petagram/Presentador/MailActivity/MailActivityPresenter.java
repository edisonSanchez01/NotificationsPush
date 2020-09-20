package com.edisondeveloper.petagram.Presentador.MailActivity;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.edisondeveloper.petagram.Presentador.MailActivity.IMailActivityPresenter;
import com.edisondeveloper.petagram.R;
import com.edisondeveloper.petagram.Vista.MailActivity.IMailActivityView;


public class MailActivityPresenter implements IMailActivityPresenter {

    private IMailActivityView iMailActivityView;
    private Context context;

    public MailActivityPresenter(Context context, IMailActivityView iMailActivityView){
        this.iMailActivityView = iMailActivityView;
        this.context = context;
    }

    @Override
    public void validacion(EditText name, EditText email, EditText message) {
        String nombre = name.getText().toString();
        String correo = email.getText().toString();
        String mensaje = message.getText().toString();
        if (nombre.length() < 13 | nombre.isEmpty()) {
            name.setError(context.getString(R.string.nombre_incorrecto));
        } else if (correo.length() < 13 | correo.isEmpty()) {
            email.setError(context.getString(R.string.email_incorrecto));
        } else if (!correo.contains("@")) {
            email.setError(context.getString(R.string.email_incorrecto));
        } else if (mensaje.isEmpty()) {
            message.setError(context.getString(R.string.msg_incorrecto));
        } else {
            Toast.makeText(context, context.getString(R.string.successfull_msg), Toast.LENGTH_SHORT).show();
        }
    }
}
