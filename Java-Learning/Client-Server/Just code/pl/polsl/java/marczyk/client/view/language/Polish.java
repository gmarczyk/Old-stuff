package pl.polsl.java.marczyk.client.view.language;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Concrete class of the bridge implementer, stands for Polish language (PL).
 *
 * @author MarczykLAP
 * @version 2.1.0
 */
public class Polish implements LanguageImplementer {

    /**
     * Non-parametric constructor.
     */
    public Polish() {

    }

    @Override
    public void msgEmptyValue() {
        System.out.println("Nie wpisano zadnej wartosci!");
    }

    @Override
    public void jopEmptyValue(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Nie wpisano zadnej wartosci!");
    }

    @Override
    public void showMenu() {
        System.out.println("[1] - Wykonaj test fermata");
        System.out.println("[2] - Wyjdz");
    }

    @Override
    public void msgLanguageIsSetTo(String language) {
        System.out.println("Jezyk interfejsu jest ustawiony na: " + language + ".");
    }

    @Override
    public void msgLanguageInvalid() {
        System.out.println("Wybrany jezyk jest nieprawidlowy, jezyk zostaje ustawiony na domyslny.");
    }

    @Override
    public void msgInitializationFinished() {
        System.out.println("Proces inicjalizacji parametrów startowych zakonczony.");
    }

    @Override
    public void msgInitializationFailed() {
        System.out.println("Parametry wejsciowe nieprawidlowe! Nastapi wyjscie z programu.");
    }

    @Override
    public void msgExit() {
        System.out.println("Wybrano wyjscie z programu.");
    }

    @Override
    public void msgExceptionIO() {
        System.out.println("Blad podczas odczytu danych.");
    }

    @Override
    public void msgExceptionNumberFormat() {
        System.out.println("Format danych wejsciowych jest nieprawidlowy.");
    }

    @Override
    public void jopExceptionNumberFormat(JFrame frame) {
        JOptionPane.showMessageDialog(frame,"Format danych wejsciowych jest nieprawidlowy.");
    }

    @Override
    public void msgValueOutOfRequiredIntervalException() {
        System.out.println("Liczba badz jej skladowa nie spelnia wymagan wobec przedzialu.");
    }

    @Override
    public void jopValueOutOfRequiredIntervalException(JFrame frame) {
        JOptionPane.showMessageDialog(frame,"Liczba badz jej skladowa nie spelnia wymagan wobec przedzialu.");
    }

    @Override
    public void msgWrongOption() {
        System.out.println("Wybrales nieprawidlowa opcje.");
    }

    @Override
    public void askForFermatNumber() {
        System.out.println("Prosze, wpisz liczbe ktora chcesz sprawdzic.");
        System.out.println("Liczba musi byc wieksza od 1!.");
    }

    @Override
    public void askForFermatNumberForTest() {
        System.out.println("Wpisz wartosc z przedzialu <1, testowana liczba) dla ktorej bedzie "
                + "wykonywany test Fermata.");
    }

    @Override
    @Deprecated
    public void showFermatTestOptions() {
        System.out.println("Test wymaga wartosci odniesienia dla ktorej bedzie wykonywany.");
        System.out.println("1 - Chce sam podac wartosc dla ktorej ma byc przeprowadzony test");
        System.out.println("2 - Chce aby program sam wygenerowal losowa wartosc");
        System.out.println("3 - Anuluj - powrot do menu");
    }

    @Override
    @Deprecated
    public void showGeneratedNumberToTest(String number) {
        System.out.println("Wygenerowana wartosc odniesienia dla testu to: " + number);
    }

    @Override
    public void showTestResults(String testedNumber, ArrayList<String> testedOnValue, ArrayList<Boolean> resultOfTest) {

        for (int i = 0; i < resultOfTest.size(); i++) {
            String resultMessage;
            if (resultOfTest.get(i) == true) {
                resultMessage = "Pozytywny";
            } else {
                resultMessage = "Negatywny";
            }
            System.out.println((i + 1) + ". " + resultMessage + " na liczbie: " + testedOnValue.get(i));

        }

    }

    @Override
    public void askForAmountOfFermatTests() {
        System.out.println("Prosze wpisac ilosc testow ktore bedziesz wykonanywac");
    }

    @Override
    public String textJmFile() {
        return "Plik";
    }

    @Override
    public String textJmEdit() {
        return "Edytuj";
    }

    @Override
    public String textJmHelp() {
        return "Pomoc";
    }

    @Override
    public String textJmiNewTest() {
        return "Nowy test";
    }
    
        @Override
    public String textJmiFermat() {
        return "Test Fermata";
    }

    @Override
    public String textJmiSave() {
        return "Zapisz test";
    }

    @Override
    public String textJmiOpen() {
        return "Otworz test";
    }

    @Override
    public String textJmiExit() {
        return "Wyjdz";
    }

    @Override
    public String textJlTestedNum() {
        return "Testowana liczba";
    }

    @Override
    public String textColTestedOn() {
        return "Wartosc odniesienia";
    }

    @Override
    public String textColResult() {
        return "Wynik";
    }

    @Override
    public String textResult(boolean res) {
        if (res == true) {
            return "Pozytywny";
        } else {
            return "Negatywny";
        }
    }
        @Override
    public String textJbPerformTest() {
        return "Przeprowadz test";
    }

    @Override
    public String jopAskForNumber() {
        return JOptionPane.showInputDialog("Wpisz testowana liczbe");
    }

}
