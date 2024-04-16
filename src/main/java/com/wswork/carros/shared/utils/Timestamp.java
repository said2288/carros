package com.wswork.carros.shared.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {
    public String formatarTimesTamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date dataHoraAtual = new Date();
        String data = dateFormat.format(dataHoraAtual);
        String hora = timeFormat.format(dataHoraAtual);
        String timestampCadastro = data + " " + hora;
        return timestampCadastro;
    }
}
