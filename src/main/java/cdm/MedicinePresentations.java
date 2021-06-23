package cdm;

import java.io.*;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class MedicinePresentations extends CDM {

    private static final String MEDICINEPRESENTATIONS_TAGNAME = "medicinePresentations";
    private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
    private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
    private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
    private static final String DOSE_FIELD_TAGNAME = "dose";
    private static final String POSREF_FIELD_TAGNAME = "posologyRef";
    private static final String FIELD_SEP = ";";

    public MedicinePresentations(CDM s) {
        super(s);
    }

    public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
        if (name.equals(MEDICINEPRESENTATIONS_TAGNAME)) {
            return super.everyCategory(reader, name);
        } else {
            if (next != null) {
                return super.readCategory(reader, name);
            } else {
                reader.skipValue();
                System.err.println("La categoria: '" + name + "' no es correcta.");
                return new StringBuffer("");
            }
        }
    }

    private boolean isArray(JsonReader reader) throws IOException {
        Boolean isArray = false;
        if (reader.peek() == JsonToken.BEGIN_ARRAY) {
            isArray = true;
        }
        return isArray;
    }

    public String readEntry(JsonReader reader) throws IOException {
        String mp_ref = null;
        String aip_ref = null;
        String inhp_ref = "";
        String dose = "";
        String poso_ref = "";
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(MEDREF_FIELD_TAGNAME)) {
                mp_ref = reader.nextString();
            } else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
                aip_ref = reader.nextString();
            } else if (name.equals(INHREF_FIELD_TAGNAME)) {
                if (!isArray(reader)) {
                    inhp_ref = reader.nextString();
                } else if (isArray(reader)) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        inhp_ref = inhp_ref + reader.nextString() + ", ";
                    }
                    reader.endArray();
                    inhp_ref = inhp_ref.substring(0, inhp_ref.length() - 2);
                } else {
                    inhp_ref = "ERROR";
                }
            } else if (name.equals(DOSE_FIELD_TAGNAME)) {
                if (!isArray(reader)) {
                    dose = reader.nextString();
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        dose = dose + "(" + reader.nextString() + "), ";
                    }
                    reader.endArray();
                    dose = dose.substring(0, dose.length() - 2);
                }
            } else if (name.equals(POSREF_FIELD_TAGNAME)) {
                if (!isArray(reader)) {
                    poso_ref = reader.nextString();
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        poso_ref = poso_ref + reader.nextString() + ", ";
                    }
                    reader.endArray();
                    poso_ref = poso_ref.substring(0, poso_ref.length() - 2);
                }
            } else {
                reader.skipValue();
            }
        }
        return mp_ref + FIELD_SEP + aip_ref + FIELD_SEP + inhp_ref + FIELD_SEP + dose + FIELD_SEP + poso_ref;
    }
}


