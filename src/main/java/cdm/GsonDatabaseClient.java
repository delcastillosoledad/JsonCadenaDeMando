package cdm;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			//se crea la cadena de mando:
			ActiveIngredients AI = new ActiveIngredients(null);
			Inhalers I = new Inhalers(AI);
			Medicines M = new Medicines(I);
			MedicinePresentations MP = new MedicinePresentations(M);
			UserManualSteps UMS = new UserManualSteps(MP);
			Posologies Po = new Posologies(UMS);
			Physiotherapies P = new Physiotherapies(Po);
			RescueMedicinePresentations RMP = new RescueMedicinePresentations(P);
			UserManualsPhysioSteps UMPS = new UserManualsPhysioSteps(RMP);
			DatabaseJSonReader DJR = new DatabaseJSonReader(UMPS);
			try {
				System.out.println(DJR.parse("./src/main/resources/datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}
